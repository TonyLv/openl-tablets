@setlocal
@set _CP=.;^
lib\acegi-security-1.0.6.jar;^
lib\acegi-security-tiger-1.0.6.jar;^
lib\ant-1.7.0.jar;^
lib\ant-launcher-1.7.0.jar;^
lib\antlr-2.7.6.jar;^
lib\aopalliance-1.0.jar;^
lib\asm-1.5.3.jar;^
lib\asm-2.2.3.jar;^
lib\asm-attrs-1.5.3.jar;^
lib\avalon-framework-4.1.3.jar;^
lib\bcprov-jdk14-136.jar;^
lib\bootstrap-5.5.12.jar;^
lib\BrowserLauncher-2-1_3.jar;^
lib\cglib-2.1_3.jar;^
lib\cglib-nodep-2.1_3.jar;^
lib\commons-beanutils-1.7.0.jar;^
lib\commons-codec-1.3.jar;^
lib\commons-collections-3.2.jar;^
lib\commons-dbcp-1.2.2.jar;^
lib\commons-digester-1.6.jar;^
lib\commons-el-1.0.jar;^
lib\commons-fileupload-1.2.jar;^
lib\commons-io-1.3.2.jar;^
lib\commons-lang-2.3.jar;^
lib\commons-logging-1.1.jar;^
lib\commons-logging-api-1.0.4.jar;^
lib\commons-pool-1.3.jar;^
lib\commons-validator-1.3.1.jar;^
lib\concurrent-1.3.4.jar;^
lib\cxf-api-2.0.3-incubator.jar;^
lib\cxf-bundle-2.0.3-incubator.jar;^
lib\cxf-common-utilities-2.0.3-incubator.jar;^
lib\cxf-distribution-manifest-2.0.3-incubator.jar;^
lib\cxf-rt-core-2.0.3-incubator.jar;^
lib\cxf-rt-databinding-aegis-2.0.3-incubator.jar^
lib\cxf-rt-frontend-jaxws-2.0.3-incubator.jar;^
lib\cxf-rt-frontend-js-2.0.3-incubator.jar;^
lib\cxf-rt-transports-http-2.0.3-incubator.jar;^
lib\cxf-rt-transports-http-jetty-2.0.3-incubator.jar;^
lib\cxf-rt-transports-jms-2.0.3-incubator.jar;^
lib\cxf-rt-ws-security-2.0.3-incubator.jar;^
lib\cxf-tools-common-2.0.3-incubator.jar;^
lib\cxf-tools-wsdlto-core-2.0.3-incubator.jar;^
lib\dbunit-2.2.jar;^
lib\derby-10.2.1.6.jar;^
lib\dom4j-1.6.1.jar;^
lib\ehcache-1.2.3.jar;^
lib\ejb3-persistence-3.0.jar;^
lib\el-api-1.0.jar;^
lib\el-ri-1.0.jar;^
lib\geronimo-activation_1.1_spec-1.0-M1.jar;^
lib\geronimo-annotation_1.0_spec-1.1.jar;^
lib\geronimo-javamail_1.4_spec-1.0-M1.jar;^
lib\geronimo-jms_1.1_spec-1.1.jar;^
lib\geronimo-servlet_2.5_spec-1.1-M1.jar;^
lib\geronimo-ws-metadata_2.0_spec-1.1.1.jar;^
lib\hibernate-3.2.5.ga.jar;^
lib\hibernate-annotations-3.3.0.ga.jar;^
lib\hibernate-commons-annotations-3.3.0.ga.jar;^
lib\hsqldb-1.8.0.7.jar;^
lib\jackrabbit-api-1.3.3.jar;^
lib\jackrabbit-core-1.3.3.jar;^
lib\jackrabbit-jcr-commons-1.3.3.jar;^
lib\jackrabbit-jcr-rmi-1.3.3.jar;^
lib\jackrabbit-text-extractors-1.3.3.jar;^
lib\jaxb-api-2.0.jar;^
lib\jaxb-impl-2.0.5.jar;^
lib\jaxb-xjc-2.0.jar;^
lib\jaxen-1.1.jar;^
lib\jaxws-api-2.0.jar;^
lib\jcr-1.0.jar;^
lib\jdom-1.0.jar;^
lib\jettison-1.0-RC2.jar;^
lib\jetty-6.1.5.jar;^
lib\jetty-util-6.1.5.jar;^
lib\js-1.6R5.jar;^
lib\jsf-facelets-1.1.13.jar;^
lib\jsontools-core-1.5.jar;^
lib\jstl-1.1.0.jar;^
lib\jstl-1.1.2.jar;^
lib\jta-1.0.1B.jar;^
lib\junit-addons-1.4.jar;^
lib\log4j-1.2.13.jar;^
lib\logkit-1.0.1.jar;^
lib\lucene-core-2.0.0.jar;^
lib\myfaces-api-1.1.5.jar;^
lib\myfaces-impl-1.1.5.jar;^
lib\neethi-2.0.2.jar;^
lib\ooxml-schemas-1.0.jar;^
lib\org.openl.commons_5.3.0.jar;^
lib\org.openl.core_5.3.0.jar;^
lib\org.openl.j_5.3.0.jar;^
lib\org.openl.rules.diff_5.3.0.jar;^
lib\org.openl.rules.eclipse.ui_5.3.0.jar;^
lib\org.openl.rules.helpers_5.3.0.jar;^
lib\org.openl.rules.repository.jcr.jackrabbit_5.3.0.jar;^
lib\org.openl.rules.repository.jcr_5.3.0.jar;^
lib\org.openl.rules.repository_5.3.0.jar;^
lib\org.openl.rules.ruleservice.ws_5.3.0.jar;^
lib\org.openl.rules.ruleservice_5.3.0.jar;^
lib\org.openl.rules.security.standalone_5.3.0.jar;^
lib\org.openl.rules.security_5.3.0.jar;^
lib\org.openl.rules.tableeditor_5.3.0.jar;^
lib\org.openl.rules.util_5.3.0.jar;^
lib\org.openl.rules.validator_5.3.0.jar;^
lib\org.openl.rules.webstudio.java_5.3.0.jar;^
lib\org.openl.rules.webtools_5.3.0.jar;^
lib\org.openl.rules.workspace_5.3.0.jar;^
lib\org.openl.rules_5.3.0.jar;^
lib\oro-2.0.8.jar;^
lib\persistence-api-1.0.jar;^
lib\poi-ooxml-3.5-beta5.jar;^
lib\org.openl.lib.poi35.dev.modified-5.3.0.jar;^
lib\richfaces-api-3.1.2.GA.jar;^
lib\richfaces-impl-3.1.2.GA.jar;^
lib\richfaces-ui-3.1.2.GA.jar;^
lib\saaj-api-1.3.jar;^
lib\saaj-impl-1.3.jar;^
lib\servlet-api-2.3.jar;^
lib\slf4j-api-1.3.0.jar;^
lib\slf4j-jdk14-1.3.1.jar;^
lib\slf4j-log4j12-1.3.0.jar;^
lib\spring-2.5.1.jar;^
lib\spring-beans-2.0.6.jar;^
lib\spring-context-2.0.6.jar;^
lib\spring-core-2.0.6.jar;^
lib\spring-web-2.0.6.jar;^
lib\standard-1.1.2.jar;^
lib\stax-api-1.0.1.jar;^
lib\stax-utils-20060502.jar;^
lib\tomahawk-1.1.6.jar;^
lib\velocity-1.4.jar;^
lib\velocity-dep-1.4.jar;^
lib\wsdl4j-1.6.1.jar;^
lib\wss4j-1.5.1.jar;^
lib\wstx-asl-3.2.1.jar;^
lib\xalan-2.7.0.jar;^
lib\xbean-2.2.0.jar;^
lib\xercesImpl-2.8.1.jar;^
lib\xml-apis-1.3.03.jar;^
lib\xml-resolver-1.2.jar;^
lib\xmlbeans-2.3.0.jar;^
lib\xmlParserAPIs-2.6.2.jar;^
lib\XmlSchema-1.3.2.jar;^
lib\xmlsec-1.3.0.jar
@
start java -classpath "%_CP%" org.openl.rules.ruleservice.RuleServiceMain
@
@endlocal
