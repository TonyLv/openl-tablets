package org.openl.rules.method;

import org.openl.exception.OpenLRuntimeException;
import org.openl.rules.table.ATableTracerNode;
import org.openl.syntax.exception.SyntaxNodeException;
import org.openl.vm.IRuntimeEnv;
import org.openl.vm.trace.Tracer;

/**
 * Default implementation for invokers supporting tracing.
 *
 * @author Yury Molchan
 */
public abstract class RulesMethodInvoker<T extends ExecutableRulesMethod> implements InvokerWithTrace {

    private T invokableMethod;

    protected RulesMethodInvoker(T invokableMethod) {
        this.invokableMethod = invokableMethod;
    }

    public final Object invoke(Object target, Object[] params, IRuntimeEnv env) {
        // check if the object can be invoked
        //
        if (canInvoke()) {
            if (Tracer.isTracerOn()) {
                // invoke in trace
                return invokeTraced(target, params, env);
            } else {
                // simple run invoke
                return invokeSimple(target, params, env);
            }
        } else {
            // object can`t be invoked, inform user about the problem.
            SyntaxNodeException cause = getInvokableMethod().getSyntaxNode().getErrors()[0];
            OpenLRuntimeException error = new OpenLRuntimeException(cause);
            Tracer.put(invokableMethod, "error", error, params);
            throw error;
        }
    }

    @Override
    public Object invokeTraced(Object target, Object[] params, IRuntimeEnv env) {

        ATableTracerNode traceObject = getTraceObject(params);
        Tracer.begin(traceObject);
        try {
            Object result = invokeSimpleTraced(target, params, env);
            traceObject.setResult(result);
            return result;
        } catch (RuntimeException e) {
            traceObject.setError(e); 
            throw e;
        } finally {
            Tracer.end();
        }
    }

    /**
     * This method can be overridden instead of {@link #invokeTraced} for cases when no needs to write additional trace functionality.
     */
    protected Object invokeSimpleTraced(Object target, Object[] params, IRuntimeEnv env) {
        return invokeSimple(target, params, env);
    }

    /**
     * Creates traceable node for current invokable object.
     *
     * @param params
     * @return {@link ATableTracerNode} for current invokable object.
     */
    protected ATableTracerNode getTraceObject(Object[] params) {
        return TracedObjectFactory.getTracedObject(invokableMethod, params);
    }

    public T getInvokableMethod() {
        return invokableMethod;
    }
}
