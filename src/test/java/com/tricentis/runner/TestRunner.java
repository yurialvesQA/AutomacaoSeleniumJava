package com.tricentis.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Classe principal para execução dos testes Cucumber
 * Configura os plugins de relatório e localiza os arquivos de feature
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
    key = Constants.PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json"
)
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.tricentis.steps")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "")
public class TestRunner {
    // Esta classe é usada pelo JUnit Platform para executar os testes Cucumber
    // Os relatórios serão gerados em:
    // - HTML: target/cucumber-reports.html (Relatório HTML padrão do Cucumber)
    // - JSON: target/cucumber-reports.json (Relatório JSON para integração)
    // O Extent Reports será gerado automaticamente se a dependência estiver presente
    // e o arquivo extent.properties estiver configurado corretamente
}
