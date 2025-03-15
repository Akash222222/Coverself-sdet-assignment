package org.coverself.assignement.stepDefination;

import org.coverself.assignement.utils.ApiGenratorPage;
import org.coverself.assignement.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Api_GenratorStep {
    private WebDriver driver;
    private ApiGenratorPage apiPage;
    public static String generatedApiUrl;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        apiPage = new ApiGenratorPage(driver);
    }

    @Given("I navigate to the API generator page")
    public void i_navigate_to_the_api_generator_page() {
        apiPage.open();
    }

    @When("I fill in the dataset form with required columns and values")
    public void i_fill_in_the_dataset_form_with_required_columns_and_values() throws InterruptedException {
        apiPage.addColumn("Name", "People", "Full Name");
        apiPage.addColumn("OrderCount", "Number", "Random");
        apiPage.addColumn("Email", "People", "Email Address");
        apiPage.addColumn("ProductId", "Number", "Product ID");
        apiPage.setApiName("order");
        apiPage.setRowCount("5");
    }

    @Then("I should see 5 rows in the API preview table")
    public void i_should_see_5_rows_in_the_api_preview_table() {
        int rows = apiPage.getPreviewRowCount();
        Assert.assertEquals(rows, 5, "Expected 5 rows in API preview table");
    }

    @And("I generate the API and capture the endpoint")
    public void i_generate_the_api_and_capture_the_endpoint() {
        apiPage.clickGenerateApi();
        generatedApiUrl = apiPage.getApiEndpoint();
        Assert.assertTrue(generatedApiUrl.startsWith("https://"), "Expected a valid API endpoint");
        System.out.println("Generated API URL: " + generatedApiUrl);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}