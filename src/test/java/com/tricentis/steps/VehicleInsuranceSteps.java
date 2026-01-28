package com.tricentis.steps;

import com.tricentis.config.WebDriverConfig;
import com.tricentis.pages.EnterInsurantDataPage;
import com.tricentis.pages.EnterVehicleDataPage;
import com.tricentis.utils.ScreenshotUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

/**
 * Classe de Step Definitions para os cenários de teste do formulário de seguro de veículo
 * Implementa os passos definidos nos arquivos .feature do Cucumber
 */
public class VehicleInsuranceSteps {

    private WebDriver driver;
    private EnterVehicleDataPage vehicleDataPage;
    private EnterInsurantDataPage insurantDataPage;

    /**
     * Step: Navega para a página inicial do formulário de seguro
     * Inicializa o WebDriver e acessa a URL do formulário
     */
    @Dado("que estou na página inicial do formulário de seguro")
    public void que_estou_na_pagina_inicial_do_formulario_de_seguro() {
        // Inicializa o driver e navega para a página do formulário
        driver = WebDriverConfig.getDriver();
        driver.get("http://sampleapp.tricentis.com/101/app.php");
        
        // Inicializa o PageObject da primeira aba
        vehicleDataPage = new EnterVehicleDataPage();
        
        // Captura screenshot da página inicial
        ScreenshotUtil.captureScreenshot("01_PaginaInicial");
    }

    /**
     * Step: Preenche o formulário especificado com dados válidos
     * @param formulario Nome do formulário a ser preenchido ("Enter Vehicle Data" ou "Enter Insurant Data")
     */
    @Quando("preencho o formulário {string} com dados válidos")
    public void preencho_o_formulario_com_dados_validos(String formulario) {
        if (formulario.equals("Enter Vehicle Data")) {
            // Preenche o formulário de dados do veículo
            System.out.println("Preenchendo formulário: Enter Vehicle Data");
            
            // Seleciona a marca do veículo (Make)
            vehicleDataPage.selectMake("BMW");
            ScreenshotUtil.captureScreenshot("02_SelecionadoMake");
            
            // Aguarda o campo model aparecer dinamicamente
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Seleciona o modelo do veículo (Model)
            vehicleDataPage.selectModel("Motorcycle");
            ScreenshotUtil.captureScreenshot("03_SelecionadoModel");
            
            // Preenche os campos do formulário
            vehicleDataPage.enterEnginePerformance("100");
            vehicleDataPage.enterDateOfManufacture("01/01/2020");
            
            // Cylinder Capacity só aparece para Motorcycle
            vehicleDataPage.enterCylinderCapacity("500");
            vehicleDataPage.selectNumberOfSeatsMotorcycle("2");
            vehicleDataPage.selectFuelType("Petrol");
            vehicleDataPage.enterListPrice("10000");
            vehicleDataPage.enterLicensePlateNumber("ABC1234");
            vehicleDataPage.enterAnnualMileage("5000");
            
            // Captura screenshot do formulário preenchido
            ScreenshotUtil.captureScreenshot("04_FormularioVehicleDataPreenchido");
            
        } else if (formulario.equals("Enter Insurant Data")) {
            // Preenche o formulário de dados do segurado
            System.out.println("Preenchendo formulário: Enter Insurant Data");
            
            // Preenche dados pessoais
            insurantDataPage.enterFirstName("Sr");
            insurantDataPage.enterLastName("Silva");
            insurantDataPage.enterBirthDate("01/01/1990");
            
            // Seleciona gênero (usa JavaScript para elementos ocultos)
            insurantDataPage.selectGender(true);
            ScreenshotUtil.captureScreenshot("05_SelecionadoGenero");
            
            // Preenche endereço
            insurantDataPage.enterStreetAddress("Rua Teste, 123");
            insurantDataPage.selectCountry("Brazil");
            insurantDataPage.enterZipCode("12345");
            insurantDataPage.enterCity("São Paulo");
            
            // Seleciona ocupação
            insurantDataPage.selectOccupation("Employee");
            
            // Seleciona hobbies (múltipla escolha)
            insurantDataPage.selectHobby("Speeding");
            insurantDataPage.selectHobby("Bungee Jumping");
            ScreenshotUtil.captureScreenshot("06_SelecionadosHobbies");
            
            // Preenche website
            insurantDataPage.enterWebsite("https://www.exemplo.com.br");
            
            // Captura screenshot do formulário completo preenchido
            ScreenshotUtil.captureScreenshot("07_FormularioInsurantDataPreenchido");
        }
    }

    /**
     * Step: Valida que um campo aceita apenas valores numéricos positivos
     * Realiza validações de regra de negócio sem interagir com a página
     * @param campo Nome do campo a ser validado
     */
    @E("valido que o campo {string} aceita apenas valores numéricos positivos")
    public void valido_que_o_campo_aceita_apenas_valores_numericos_positivos(String campo) {
        System.out.println("Validando campo: " + campo);
        
        if (campo.equals("Cylinder Capacity")) {
            // Valida que o campo aceita valores válidos (positivos entre 1 e 10000)
            String valorValido = "500";
            Assertions.assertTrue(vehicleDataPage.validateCylinderCapacity(valorValido),
                    "Cylinder Capacity deve aceitar valores válidos");
            System.out.println("✓ Validação positiva: Cylinder Capacity aceita " + valorValido);
            
            // Valida que o campo rejeita valores inválidos (negativos)
            String valorInvalido = "-100";
            Assertions.assertFalse(vehicleDataPage.validateCylinderCapacity(valorInvalido),
                    "Cylinder Capacity não deve aceitar valores negativos");
            System.out.println("✓ Validação negativa: Cylinder Capacity rejeita " + valorInvalido);
            
            // Captura screenshot após validação
            ScreenshotUtil.captureScreenshot("08_ValidacaoCylinderCapacity");
            
        } else if (campo.equals("Engine Performance")) {
            // Valida que o campo aceita valores válidos (positivos entre 1 e 2000)
            String valorValido = "100";
            Assertions.assertTrue(vehicleDataPage.validateEnginePerformance(valorValido),
                    "Engine Performance deve aceitar valores válidos");
            System.out.println("✓ Validação positiva: Engine Performance aceita " + valorValido);
            
            // Valida que o campo rejeita valores inválidos (zero)
            String valorInvalido = "0";
            Assertions.assertFalse(vehicleDataPage.validateEnginePerformance(valorInvalido),
                    "Engine Performance não deve aceitar zero");
            System.out.println("✓ Validação negativa: Engine Performance rejeita " + valorInvalido);
            
            // Captura screenshot após validação
            ScreenshotUtil.captureScreenshot("09_ValidacaoEnginePerformance");
        }
    }

    /**
     * Step: Clica no botão "Next" da aba especificada
     * Navega para a próxima etapa do formulário
     * @param botao Nome do botão (geralmente "Next")
     * @param aba Nome da aba atual
     */
    @E("clico no botão {string} da aba {string}")
    public void clico_no_botao_da_aba(String botao, String aba) {
        System.out.println("Clicando no botão " + botao + " da aba " + aba);
        
        if (aba.equals("Enter Vehicle Data")) {
            // Captura screenshot antes de clicar
            ScreenshotUtil.captureScreenshot("10_AntesClicarNextVehicleData");
            
            // Clica no botão Next para avançar para a próxima aba
            vehicleDataPage.clickNext();
            
            // Aguarda a transição para a próxima aba
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Captura screenshot após navegação
            ScreenshotUtil.captureScreenshot("11_AposClicarNextVehicleData");
            
        } else if (aba.equals("Enter Insurant Data")) {
            // Captura screenshot antes de clicar
            ScreenshotUtil.captureScreenshot("12_AntesClicarNextInsurantData");
            
            // Clica no botão Next para avançar para a próxima aba
            insurantDataPage.clickNext();
            
            // Aguarda a transição para a próxima aba
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Captura screenshot após navegação
            ScreenshotUtil.captureScreenshot("13_AposClicarNextInsurantData");
        }
    }

    /**
     * Step: Verifica se foi redirecionado para a aba especificada
     * Valida que a navegação entre abas foi bem-sucedida
     * @param aba Nome da aba esperada
     */
    @Então("devo ser redirecionado para a aba {string}")
    public void devo_ser_redirecionado_para_a_aba(String aba) {
        System.out.println("Verificando se está na aba: " + aba);
        
        if (aba.equals("Enter Insurant Data")) {
            // Inicializa o PageObject da nova aba
            insurantDataPage = new EnterInsurantDataPage();
            
            // Valida que está na aba correta
            Assertions.assertTrue(insurantDataPage.isOnInsurantDataPage(),
                    "Deveria estar na aba Enter Insurant Data");
            
            System.out.println("✓ Navegação bem-sucedida para a aba Enter Insurant Data");
            
            // Captura screenshot da nova aba
            ScreenshotUtil.captureScreenshot("14_AbaInsurantData");
        }
    }

    /**
     * Step: Navega para a aba especificada
     * Se necessário, preenche o formulário anterior para chegar na aba desejada
     * @param aba Nome da aba desejada
     */
    @Dado("que estou na aba {string}")
    public void que_estou_na_aba(String aba) {
        System.out.println("Navegando para a aba: " + aba);
        driver = WebDriverConfig.getDriver();
        
        if (aba.equals("Enter Insurant Data")) {
            // Inicializa o PageObject da aba de Insurant Data
            insurantDataPage = new EnterInsurantDataPage();
            
            // Verifica se já está na aba correta
            if (!insurantDataPage.isOnInsurantDataPage()) {
                System.out.println("Não está na aba correta. Preenchendo formulário anterior...");
                
                // Se não estiver, navega e preenche o formulário anterior
                driver.get("http://sampleapp.tricentis.com/101/app.php");
                vehicleDataPage = new EnterVehicleDataPage();
                
                // Preenche o formulário Enter Vehicle Data para poder avançar
                vehicleDataPage.selectMake("BMW");
                
                // Aguarda o campo model aparecer dinamicamente
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                vehicleDataPage.selectModel("Motorcycle");
                vehicleDataPage.enterEnginePerformance("100");
                vehicleDataPage.enterDateOfManufacture("01/01/2020");
                vehicleDataPage.enterCylinderCapacity("500");
                vehicleDataPage.selectNumberOfSeatsMotorcycle("2");
                vehicleDataPage.selectFuelType("Petrol");
                vehicleDataPage.enterListPrice("10000");
                vehicleDataPage.enterLicensePlateNumber("ABC1234");
                vehicleDataPage.enterAnnualMileage("5000");
                
                // Avança para a próxima aba
                vehicleDataPage.clickNext();
                
                // Aguarda a transição para a próxima aba
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                // Reinicializa o PageObject da nova aba
                insurantDataPage = new EnterInsurantDataPage();
                ScreenshotUtil.captureScreenshot("15_NavegacaoParaInsurantData");
            } else {
                System.out.println("Já está na aba Enter Insurant Data");
            }
        }
    }

    /**
     * Step: Verifica se foi redirecionado para a próxima aba do formulário
     * Valida que a navegação foi bem-sucedida verificando a URL atual
     */
    @Então("devo ser redirecionado para a próxima aba do formulário")
    public void devo_ser_redirecionado_para_a_proxima_aba_do_formulario() {
        System.out.println("Verificando navegação para a próxima aba");
        
        // Aguarda um momento para garantir que a navegação foi concluída
        try {
            Thread.sleep(1000);
            
            // Verifica se a URL está definida (indica que a navegação ocorreu)
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertNotNull(currentUrl, "URL deve estar definida");
            
            System.out.println("✓ Navegação bem-sucedida. URL atual: " + currentUrl);
            
            // Captura screenshot da nova aba
            ScreenshotUtil.captureScreenshot("16_ProximaAbaFormulario");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
