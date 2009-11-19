/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hssf.record.formula.functions;

import org.apache.poi.hssf.record.formula.eval.AreaEval;
import org.apache.poi.hssf.record.formula.eval.ErrorEval;
import org.apache.poi.hssf.record.formula.eval.EvaluationException;
import org.apache.poi.hssf.record.formula.eval.NumberEval;
import org.apache.poi.hssf.record.formula.eval.RefEval;
import org.apache.poi.hssf.record.formula.eval.ValueEval;
import org.apache.poi.hssf.record.formula.functions.LookupUtils.ValueVector;
// ZS
import org.apache.poi.ss.formula.ArrayEval;
// end changes ZS

/**
 * @author Amol S. Deshmukh &lt; amolweb at ya hoo dot com &gt;
 * @author zsulkins(ZS)- array support
 *
 */
// ZS 
public abstract class XYNumericFunction implements FunctionWithArraySupport {
// end changes ZS

	private static abstract class ValueArray implements ValueVector {
		private final int _size;
		protected ValueArray(int size) {
			_size = size;
		}
		public ValueEval getItem(int index) {
			if (index < 0 || index > _size) {
				throw new IllegalArgumentException("Specified index " + index
						+ " is outside range (0.." + (_size - 1) + ")");
			}
			return getItemInternal(index);
		}
		protected abstract ValueEval getItemInternal(int index);
		public final int getSize() {
			return _size;
		}
	}

	private static final class SingleCellValueArray extends ValueArray {
		private final ValueEval _value;
		public SingleCellValueArray(ValueEval value) {
			super(1);
			_value = value;
		}
		protected ValueEval getItemInternal(int index) {
			return _value;
		}
	}

	private static final class RefValueArray extends ValueArray {
		private final RefEval _ref;
		public RefValueArray(RefEval ref) {
			super(1);
			_ref = ref;
		}
		protected ValueEval getItemInternal(int index) {
			return _ref.getInnerValueEval();
		}
	}

	private static final class AreaValueArray extends ValueArray {
		private final AreaEval _ae;
		private final int _width;

		public AreaValueArray(AreaEval ae) {
			super(ae.getWidth() * ae.getHeight());
			_ae = ae;
			_width = ae.getWidth();
		}
		protected ValueEval getItemInternal(int index) {
			int rowIx = index / _width;
			int colIx = index % _width;
			return _ae.getRelativeValue(rowIx, colIx);
		}
	}
	// !!changed ZS
	private static final class ArrayEvalValueArray extends ValueArray {
		private final ArrayEval _ae;
		private final int _width;
		
		public ArrayEvalValueArray(ArrayEval ae){
			super( ae.getRowCounter()*ae.getColCounter() );
			_ae = ae;
			_width = ae.getColCounter();
		}
		
		protected ValueEval getItemInternal(int index){
			int rowIx = index / _width;
			int colIx = index % _width;
			return _ae.getArrayElementAsEval(rowIx, colIx);
		}
	}
    // end change	
	

	protected static interface Accumulator {
		double accumulate(double x, double y);
	}

	/**
	 * Constructs a new instance of the Accumulator used to calculated this function
	 */
	protected abstract Accumulator createAccumulator();

	public final ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
		if (args.length != 2) {
			return ErrorEval.VALUE_INVALID;
		}

		double result;
		try {
			ValueVector vvX = createValueVector(args[0]);
			ValueVector vvY = createValueVector(args[1]);
			int size = vvX.getSize();
			if (size == 0 || vvY.getSize() != size) {
				return ErrorEval.NA;
			}
			result = evaluateInternal(vvX, vvY, size);
		} catch (EvaluationException e) {
			return e.getErrorEval();
		}
		if (Double.isNaN(result) || Double.isInfinite(result)) {
			return ErrorEval.NUM_ERROR;
		}
		return new NumberEval(result);
	}
// ZS	
	/* (non-Javadoc)
	 * @see org.apache.poi.hssf.record.formula.functions.FunctionWithArraySupport#supportArray(int)
	 */
	public boolean supportArray(int paramIndex){
		return true;
	}
//	end cahnges ZS

	private double evaluateInternal(ValueVector x, ValueVector y, int size)
			throws EvaluationException {
		Accumulator acc = createAccumulator();

		// error handling is as if the x is fully evaluated before y
		ErrorEval firstXerr = null;
		ErrorEval firstYerr = null;
		boolean accumlatedSome = false;
		double result = 0.0;

		for (int i = 0; i < size; i++) {
			ValueEval vx = x.getItem(i);
			ValueEval vy = y.getItem(i);
			if (vx instanceof ErrorEval) {
				if (firstXerr == null) {
					firstXerr = (ErrorEval) vx;
					continue;
				}
			}
			if (vy instanceof ErrorEval) {
				if (firstYerr == null) {
					firstYerr = (ErrorEval) vy;
					continue;
				}
			}
			// only count pairs if both elements are numbers
			if (vx instanceof NumberEval && vy instanceof NumberEval) {
				accumlatedSome = true;
				NumberEval nx = (NumberEval) vx;
				NumberEval ny = (NumberEval) vy;
				result += acc.accumulate(nx.getNumberValue(), ny.getNumberValue());
			} else {
				// all other combinations of value types are silently ignored
			}
		}
		if (firstXerr != null) {
			throw new EvaluationException(firstXerr);
		}
		if (firstYerr != null) {
			throw new EvaluationException(firstYerr);
		}
		if (!accumlatedSome) {
			throw new EvaluationException(ErrorEval.DIV_ZERO);
		}
		return result;
	}

	private static ValueVector createValueVector(ValueEval arg) throws EvaluationException {
		if (arg instanceof ErrorEval) {
			throw new EvaluationException((ErrorEval) arg);
		}
		if (arg instanceof AreaEval) {
			return new AreaValueArray((AreaEval) arg);
		}
		if (arg instanceof RefEval) {
			return new RefValueArray((RefEval) arg);
		}
		// !! changed ZS
		if (arg instanceof ArrayEval){
			if ( ((ArrayEval)arg).isIllegalForAggregation())
				throw new EvaluationException(ErrorEval.NA);
			return new ArrayEvalValueArray((ArrayEval) arg);
		}
		
		
		if (arg instanceof ValueEval) {
			return new SingleCellValueArray((ValueEval) arg);
		}
		throw new RuntimeException("Unexpected eval class (" + arg.getClass().getName() + ")");
		// end change
	}
}
