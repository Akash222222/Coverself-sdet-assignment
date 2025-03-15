package org.coverself.assignement.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ApiGenratorPage {
    private WebDriver driver;

    public ApiGenratorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://retool.com/api-generator/");
        // System.out.println(driver.getPageSource());
    }

    public void addColumn(String columnTitle, String dataTypeCategory, String dataType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='w-full']")));
        driver.switchTo().frame(iframe);
        WebElement addColumnButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[text()='Add Column']]")));
        addColumnButton.click();


        WebElement columnTitleField = driver.findElement(By.xpath("//div[@class='_input_idoc5_105']"));
        columnTitleField.sendKeys(columnTitle);

        WebElement dataTypeDropdown = driver.findElement(By.xpath("//span[contains(@class,'ant-cascader-picker-label')and contains(text(),'People / Full Name')]"));
        dataTypeDropdown.click();

        driver.switchTo().defaultContent();
    }

    public void setApiName(String apiName) {
        WebElement apiNameField = driver.findElement(By.xpath("//input[@id='input_api_name--0']"));
        apiNameField.sendKeys(apiName);
    }

    public void setRowCount(String rowCount) {
        WebElement rowCountField = driver.findElement(By.xpath("//input[@id='input_rows--0']"));
        rowCountField.sendKeys(rowCount);
    }

    public int getPreviewRowCount() {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        return rows.size();

    }

    public void clickGenerateApi() {
        WebElement generateApiButton = driver.findElement(By.xpath("//p[text()='Generate API']"));
        generateApiButton.click();

        // Wait for the API to be generated
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'API generated successfully')]")));
    }

    public String getApiEndpoint() {
        WebElement endpointUrlField = driver.findElement(By.xpath("//a[contains(@href,'https://retoolapi.dev/BO7igH/order')]"));
        return endpointUrlField.getText();
    }
}
