package com.tricentis.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EnterVehicleDataPage extends BasePage {

    @FindBy(id = "make")
    private WebElement makeSelect;

    @FindBy(id = "model")
    private WebElement modelSelect;

    @FindBy(id = "cylindercapacity")
    private WebElement cylinderCapacityInput;

    @FindBy(id = "engineperformance")
    private WebElement enginePerformanceInput;

    @FindBy(id = "dateofmanufacture")
    private WebElement dateOfManufactureInput;

    @FindBy(id = "numberofseats")
    private WebElement numberOfSeatsSelect;

    @FindBy(id = "righthanddriveyes")
    private WebElement rightHandDriveYesRadio;

    @FindBy(id = "righthanddriveno")
    private WebElement rightHandDriveNoRadio;

    @FindBy(id = "numberofseatsmotorcycle")
    private WebElement numberOfSeatsMotorcycleSelect;

    @FindBy(id = "fuel")
    private WebElement fuelTypeSelect;

    @FindBy(id = "payload")
    private WebElement payloadInput;

    @FindBy(id = "totalweight")
    private WebElement totalWeightInput;

    @FindBy(id = "listprice")
    private WebElement listPriceInput;

    @FindBy(id = "licenseplatenumber")
    private WebElement licensePlateNumberInput;

    @FindBy(id = "annualmileage")
    private WebElement annualMileageInput;

    @FindBy(id = "nextenterinsurantdata")
    private WebElement nextButton;

    @FindBy(xpath = "//a[contains(text(), 'Enter Vehicle Data')]")
    private WebElement enterVehicleDataTab;

    // Mensagens de erro
    @FindBy(xpath = "//div[contains(@class, 'error')]")
    private WebElement errorMessage;

    public void selectMake(String make) {
        waitForElement(makeSelect);
        Select select = new Select(makeSelect);
        select.selectByVisibleText(make);
    }

    public void selectModel(String model) {
        // Aguarda o campo model aparecer (pode demorar após selecionar o Make)
        // O campo model só aparece para alguns tipos de veículo
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Verifica se o campo está presente antes de tentar selecionar
        if (isElementPresent(modelSelect)) {
            waitForElement(modelSelect);
            Select select = new Select(modelSelect);
            select.selectByVisibleText(model);
        }
    }

    public void enterCylinderCapacity(String capacity) {
        // Verifica se o campo está presente antes de tentar preencher
        if (isElementPresent(cylinderCapacityInput)) {
            waitForElement(cylinderCapacityInput);
            sendKeys(cylinderCapacityInput, capacity);
        }
    }

    public void enterEnginePerformance(String performance) {
        sendKeys(enginePerformanceInput, performance);
    }

    public void enterDateOfManufacture(String date) {
        sendKeys(dateOfManufactureInput, date);
    }

    public void selectNumberOfSeats(String seats) {
        waitForElement(numberOfSeatsSelect);
        Select select = new Select(numberOfSeatsSelect);
        select.selectByVisibleText(seats);
    }

    public void selectRightHandDrive(boolean yes) {
        if (yes) {
            waitForElementClickable(rightHandDriveYesRadio);
            if (!rightHandDriveYesRadio.isSelected()) {
                rightHandDriveYesRadio.click();
            }
        } else {
            waitForElementClickable(rightHandDriveNoRadio);
            if (!rightHandDriveNoRadio.isSelected()) {
                rightHandDriveNoRadio.click();
            }
        }
    }

    public void selectNumberOfSeatsMotorcycle(String seats) {
        waitForElement(numberOfSeatsMotorcycleSelect);
        Select select = new Select(numberOfSeatsMotorcycleSelect);
        select.selectByVisibleText(seats);
    }

    public void selectFuelType(String fuelType) {
        waitForElement(fuelTypeSelect);
        Select select = new Select(fuelTypeSelect);
        select.selectByVisibleText(fuelType);
    }

    public void enterPayload(String payload) {
        sendKeys(payloadInput, payload);
    }

    public void enterTotalWeight(String weight) {
        sendKeys(totalWeightInput, weight);
    }

    public void enterListPrice(String price) {
        sendKeys(listPriceInput, price);
    }

    public void enterLicensePlateNumber(String plateNumber) {
        sendKeys(licensePlateNumberInput, plateNumber);
    }

    public void enterAnnualMileage(String mileage) {
        sendKeys(annualMileageInput, mileage);
    }

    public void clickNext() {
        click(nextButton);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return getText(errorMessage);
        }
        return "";
    }

    // Validações
    public boolean validateCylinderCapacity(String capacity) {
        try {
            int value = Integer.parseInt(capacity);
            return value > 0 && value <= 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateEnginePerformance(String performance) {
        try {
            int value = Integer.parseInt(performance);
            return value > 0 && value <= 2000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isFieldRequired(WebElement element) {
        String required = element.getAttribute("required");
        return required != null && required.equals("true");
    }

    public boolean isCylinderCapacityRequired() {
        return isFieldRequired(cylinderCapacityInput);
    }

    public boolean isEnginePerformanceRequired() {
        return isFieldRequired(enginePerformanceInput);
    }
}
