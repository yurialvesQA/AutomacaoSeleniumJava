package com.tricentis.hooks;

import com.tricentis.config.WebDriverConfig;
import com.tricentis.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Classe de Hooks do Cucumber para configurar e limpar o ambiente de teste
 * Captura screenshots em caso de falha
 */
public class Hooks {

    /**
     * Método executado antes de cada cenário
     * Inicializa o WebDriver se necessário
     */
    @Before
    public void setUp(Scenario scenario) {
        // O WebDriver será inicializado quando necessário
        // através do WebDriverConfig.getDriver()
        System.out.println("Iniciando cenário: " + scenario.getName());
    }

    /**
     * Método executado após cada cenário
     * Captura screenshot em caso de falha e fecha o driver
     * @param scenario Cenário atual sendo executado
     */
    @After
    public void tearDown(Scenario scenario) {
        // Captura screenshot se o cenário falhou
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtil.captureScreenshot("FAILED_" + scenario.getName());
            if (screenshotPath != null) {
                // Anexa o screenshot ao relatório do Cucumber
                byte[] screenshot = ScreenshotUtil.getScreenshotAsBytes();
                if (screenshot != null) {
                    scenario.attach(screenshot, "image/png", "Screenshot da Falha");
                }
                System.out.println("Screenshot capturado: " + screenshotPath);
            }
        } else {
            // Captura screenshot de sucesso também (opcional)
            String screenshotPath = ScreenshotUtil.captureScreenshot("SUCCESS_" + scenario.getName());
            System.out.println("Cenário concluído com sucesso: " + scenario.getName());
        }
        
        // Fecha o driver após cada cenário
        WebDriverConfig.closeDriver();
    }
}
