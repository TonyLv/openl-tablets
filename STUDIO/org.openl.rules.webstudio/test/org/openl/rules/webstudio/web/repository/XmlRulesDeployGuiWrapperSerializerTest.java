package org.openl.rules.webstudio.web.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openl.rules.project.model.RulesDeploy;

public class XmlRulesDeployGuiWrapperSerializerTest {

    @Test
    public void testSerialize() {
        RulesDeploy rulesDeploy = new RulesDeploy();
        rulesDeploy.setProvideRuntimeContext(false);

        RulesDeployGuiWrapper wrapper = new RulesDeployGuiWrapper(rulesDeploy);
        String config = "    <configuration>\n" + 
                "    <entry>\n" + 
                "      <string>key2</string>\n" + 
                "      <rules-deploy>\n" + 
                "        <serviceClass>s</serviceClass>\n" + 
                "      </rules-deploy>\n" + 
                "    </entry>\n" + 
                "    <entry>\n" + 
                "      <string>key1</string>\n" + 
                "      <string>value2</string>\n" + 
                "    </entry>\n" + 
                "  </configuration>";
        wrapper.setConfiguration(config);
        
        String serialized = new XmlRulesDeployGuiWrapperSerializer().serialize(wrapper);

        String expected = "<rules-deploy>\n" +
                "  <isProvideRuntimeContext>false</isProvideRuntimeContext>\n" + 
                "    <configuration>\n" + 
                "    <entry>\n" + 
                "      <string>key2</string>\n" + 
                "      <rules-deploy>\n" + 
                "        <serviceClass>s</serviceClass>\n" + 
                "      </rules-deploy>\n" + 
                "    </entry>\n" + 
                "    <entry>\n" + 
                "      <string>key1</string>\n" + 
                "      <string>value2</string>\n" + 
                "    </entry>\n" + 
                "  </configuration>\n" + 
                "</rules-deploy>";
        assertEquals(expected, serialized);
    }

    @Test
    public void testDeserialize() {
        String value = "<rules-deploy>\n" +
                "  <isProvideRuntimeContext>false</isProvideRuntimeContext>\n" + 
                "  <isProvideVariations>false</isProvideVariations>\n" + 
                "  <serviceName>a</serviceName>\n" + 
                "  <serviceClass>b</serviceClass>\n" + 
                "  <url>c</url>\n" + 
                "    <configuration>\n" + 
                "    <entry>\n" + 
                "      <string>key2</string>\n" + 
                "      <rules-deploy>\n" + 
                "        <serviceClass>s</serviceClass>\n" + 
                "      </rules-deploy>\n" + 
                "    </entry>\n" + 
                "    <entry>\n" + 
                "      <string>key1</string>\n" + 
                "      <string>value2</string>\n" + 
                "    </entry>\n" + 
                "  </configuration>\n" + 
                "\n" + 
                "</rules-deploy>";
        
        RulesDeployGuiWrapper wrapper = new XmlRulesDeployGuiWrapperSerializer().deserialize(value);
        String expected = "<configuration>\n" + 
                "    <entry>\n" + 
                "      <string>key2</string>\n" + 
                "      <rules-deploy>\n" + 
                "        <serviceClass>s</serviceClass>\n" + 
                "      </rules-deploy>\n" + 
                "    </entry>\n" + 
                "    <entry>\n" + 
                "      <string>key1</string>\n" + 
                "      <string>value2</string>\n" + 
                "    </entry>\n" + 
                "  </configuration>"; 

        assertEquals(expected, wrapper.getConfiguration());
    }
}
