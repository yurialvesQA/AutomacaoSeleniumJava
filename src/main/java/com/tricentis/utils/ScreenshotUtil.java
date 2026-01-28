package com.tricentis.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.tricentis.config.WebDriverConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe utilitária para captura de screenshots durante a execução dos testes
 * Fornece métodos para capturar evidências visuais dos cenários executados
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "target/screenshots";

    /**
     * Captura um screenshot e salva com um nome baseado no timestamp
     * @param stepName Nome do step para identificar o screenshot
     * @return Caminho completo do arquivo de screenshot salvo
     */
    public static String captureScreenshot(String stepName) {
        WebDriver driver = WebDriverConfig.getDriver();
        
        if (driver == null) {
            return null;
        }

        try {
            // Cria o diretório de screenshots se não existir
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotPath)) {
                Files.createDirectories(screenshotPath);
            }

            // Gera nome do arquivo com timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = stepName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + File.separator + fileName;

            // Captura o screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);
            
            // Copia o arquivo para o destino
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), 
                      java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            return filePath;
        } catch (IOException e) {
            System.err.println("Erro ao capturar screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captura um screenshot com nome baseado no timestamp
     * @return Caminho completo do arquivo de screenshot salvo
     */
    public static String captureScreenshot() {
        return captureScreenshot("screenshot");
    }

    /**
     * Captura screenshot e retorna como array de bytes
     * Útil para anexar ao relatório do Cucumber
     * @return Array de bytes do screenshot
     */
    public static byte[] getScreenshotAsBytes() {
        WebDriver driver = WebDriverConfig.getDriver();
        
        if (driver == null) {
            return null;
        }

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Erro ao capturar screenshot como bytes: " + e.getMessage());
            return null;
        }
    }
}
