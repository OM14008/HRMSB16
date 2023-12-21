package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;



public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndNavigateToURL() {
        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //this method will initialize all the objects available inside the method
        initializePageObjects();
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void sendText(String text, WebElement element) {
        element.clear();        // here we have a clear method because sometimes there can be text present
        //in the text box that we need to clear before inserting new text,
        //reason why use own method
        element.sendKeys((text));
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public static void waitForClickAbility(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickAbility(element);
        element.click();
    }

    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        //we write this line because cucumber accepts array of byte for screenshot
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        //in case if it doesn't find file name or path it will throw an exception

        try {
            FileUtils.copyFile(screenShot, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        //it returns current date and time
        Date date = new Date();
        //function sdf used to format the date as per the pattern we are passing
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static void selectFromDropDown(WebElement dropDown, String selectionCriteria, String value) {
        Select select = new Select(dropDown);
        switch (selectionCriteria.toLowerCase()) {
            case "text":
                select.selectByVisibleText(value);
                break;
            case "index":
                int index = Integer.parseInt(value);
                select.selectByIndex(index);
                break;
            case "value":
                select.selectByValue(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid selection criteria. " +
                                                   "Use 'text', 'index', 'value'.");
        }
    }


    public static String formatFullDate(String fullDate) {
        String[] monthNames = {
                "",     // Index 0 is not used, as months are 1-based
                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        String[] dateParts = fullDate.split("/");
        if (dateParts.length == 3) {
            int monthNumber = Integer.parseInt(dateParts[0]);
            if (monthNumber >= 1 && monthNumber <= 12) {
                String formattedMonth = monthNames[monthNumber];
                return formattedMonth + "/" + dateParts[1] + "/" + dateParts[2];
            }
        }

        return "Invalid";
    }

    public static void selectFromCalenderWithDropDowns(WebElement datePickerMonth, WebElement datePickerYear,
                                                       WebElement datePickerTable, String month, String year, String day) {
        click(datePickerMonth);
        selectFromDropDown(datePickerMonth,"text",month);
        click(datePickerYear);
        selectFromDropDown(datePickerYear,"text",year);
        boolean dateFound = false;
        List<WebElement> rows = datePickerTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String cellText = cell.getText();
                if (cellText.equals(day)) {
                    cell.click();
                    dateFound=true;
                    break;
                }
            }
            if(dateFound){
                break;
            }
        }
    }
}

