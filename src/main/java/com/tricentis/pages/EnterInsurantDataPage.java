package com.tricentis.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EnterInsurantDataPage extends BasePage {

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "birthdate")
    private WebElement birthDateInput;

    @FindBy(id = "gendermale")
    private WebElement genderMaleRadio;

    @FindBy(id = "genderfemale")
    private WebElement genderFemaleRadio;

    // Spans que cobrem os radio buttons
    @FindBy(xpath = "//input[@id='gendermale']/following-sibling::span[@class='ideal-radio']")
    private WebElement genderMaleSpan;

    @FindBy(xpath = "//input[@id='genderfemale']/following-sibling::span[@class='ideal-radio']")
    private WebElement genderFemaleSpan;

    @FindBy(id = "streetaddress")
    private WebElement streetAddressInput;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "zipcode")
    private WebElement zipCodeInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "occupation")
    private WebElement occupationSelect;

    @FindBy(id = "speeding")
    private WebElement speedingHobbyCheckbox;

    @FindBy(id = "bungeejumping")
    private WebElement bungeeJumpingHobbyCheckbox;

    @FindBy(id = "cliffdiving")
    private WebElement cliffDivingHobbyCheckbox;

    @FindBy(id = "skydiving")
    private WebElement skydivingHobbyCheckbox;

    @FindBy(id = "other")
    private WebElement otherHobbyCheckbox;

    // Spans que cobrem os checkboxes de hobbies
    @FindBy(xpath = "//input[@id='speeding']/following-sibling::span[@class='ideal-check']")
    private WebElement speedingHobbySpan;

    @FindBy(xpath = "//input[@id='bungeejumping']/following-sibling::span[@class='ideal-check']")
    private WebElement bungeeJumpingHobbySpan;

    @FindBy(xpath = "//input[@id='cliffdiving']/following-sibling::span[@class='ideal-check']")
    private WebElement cliffDivingHobbySpan;

    @FindBy(xpath = "//input[@id='skydiving']/following-sibling::span[@class='ideal-check']")
    private WebElement skydivingHobbySpan;

    @FindBy(xpath = "//input[@id='other']/following-sibling::span[@class='ideal-check']")
    private WebElement otherHobbySpan;

    @FindBy(id = "website")
    private WebElement websiteInput;

    @FindBy(id = "nextenterproductdata")
    private WebElement nextButton;

    @FindBy(xpath = "//a[contains(text(), 'Enter Insurant Data')]")
    private WebElement enterInsurantDataTab;

    public void enterFirstName(String firstName) {
        sendKeys(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameInput, lastName);
    }

    public void enterBirthDate(String birthDate) {
        sendKeys(birthDateInput, birthDate);
    }

    public void selectGender(boolean isMale) {
        WebElement radioElement = isMale ? genderMaleRadio : genderFemaleRadio;
        WebElement spanElement = isMale ? genderMaleSpan : genderFemaleSpan;
        
        try {
            // Tenta clicar no span primeiro (elemento visível)
            if (isElementPresent(spanElement)) {
                waitForElementClickable(spanElement);
                spanElement.click();
            } else {
                // Se o span não estiver presente, usa JavaScript para clicar no radio oculto
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", radioElement);
            }
        } catch (Exception e) {
            // Fallback: sempre funciona com JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", radioElement);
        }
    }

    public void enterStreetAddress(String address) {
        sendKeys(streetAddressInput, address);
    }

    public void selectCountry(String country) {
        waitForElement(countrySelect);
        Select select = new Select(countrySelect);
        select.selectByVisibleText(country);
    }

    public void enterZipCode(String zipCode) {
        sendKeys(zipCodeInput, zipCode);
    }

    public void enterCity(String city) {
        sendKeys(cityInput, city);
    }

    public void selectOccupation(String occupation) {
        waitForElement(occupationSelect);
        Select select = new Select(occupationSelect);
        select.selectByVisibleText(occupation);
    }

    public void selectHobby(String hobby) {
        WebElement hobbyCheckbox = null;
        WebElement hobbySpan = null;
        
        switch (hobby.toLowerCase()) {
            case "speeding":
                hobbyCheckbox = speedingHobbyCheckbox;
                hobbySpan = speedingHobbySpan;
                break;
            case "bungee jumping":
                hobbyCheckbox = bungeeJumpingHobbyCheckbox;
                hobbySpan = bungeeJumpingHobbySpan;
                break;
            case "cliff diving":
                hobbyCheckbox = cliffDivingHobbyCheckbox;
                hobbySpan = cliffDivingHobbySpan;
                break;
            case "skydiving":
                hobbyCheckbox = skydivingHobbyCheckbox;
                hobbySpan = skydivingHobbySpan;
                break;
            case "other":
                hobbyCheckbox = otherHobbyCheckbox;
                hobbySpan = otherHobbySpan;
                break;
        }
        
        if (hobbyCheckbox != null) {
            try {
                // Tenta clicar no span primeiro (elemento visível)
                if (isElementPresent(hobbySpan)) {
                    waitForElementClickable(hobbySpan);
                    hobbySpan.click();
                } else {
                    // Se o span não estiver presente, usa JavaScript para clicar no checkbox oculto
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", hobbyCheckbox);
                }
            } catch (Exception e) {
                // Fallback: sempre funciona com JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", hobbyCheckbox);
            }
        }
    }

    public void enterWebsite(String website) {
        sendKeys(websiteInput, website);
    }

    public void clickNext() {
        click(nextButton);
    }

    public boolean isOnInsurantDataPage() {
        return isElementDisplayed(enterInsurantDataTab);
    }
}
