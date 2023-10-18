package Infrastructure.UI.Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class InfraStructureSelenium {
    private static final Configuration configuration = new Configuration();
    private static final Logger log = LoggerFactory.getLogger(InfraStructureSelenium.class);
    private static WebDriver driver;

    static {
        try {
            driver = createDriver();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InfraStructureSelenium(){
    }


    private static WebDriver createDriver() throws Exception {
        String browser = String.valueOf(configuration.getDriverManagerType());
        log.info("Create local driver: " + browser);
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
                break;

            default:
                ChromeOptions optionsCH = new ChromeOptions();
                optionsCH.addArguments("--incognito");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(optionsCH);
                driver.manage().window().maximize();
                break;
            }
        java.util.logging.Logger.getLogger("org.openqa.selenium");
        return driver;
    }

    public void closeWindow() {
        driver.quit();
    }

    /** Open page with given URL */
    public void moveToUrl(String url) {
        driver.get(url);
        log.info(String.format("Opened page with ",url));
    }

    /** Find element using given locator */
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }


    /** Find all elements using given locator */
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }


    /** Click on element with given locator when its visible */
    public void click(By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(10));
        find(locator).click();
    }


    /** Type given text into element with given locator */
    public void enterText(String text, By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).sendKeys(text);
    }

    /** Get given text from element with given locator */
    public String getText(By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        return find(locator).getText();
    }

    /** Get given attribute from element with given locator */
    public String getAttribute(By locator, String attribute) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        return find(locator).getAttribute(attribute);
    }
    public Boolean getAttributeBoolean(By locator, String attribute) {
        waitForVisibilityOf(locator, Duration.ofSeconds(10));
        return Boolean.valueOf(find(locator).getAttribute(attribute));
    }


    /** Wait for specific ExpectedCondition for the given Duration */
    private void waitFor(ExpectedCondition<WebElement> condition, Duration timeOut) {
        timeOut = timeOut != null ? timeOut : Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(condition);
    }


    /**
     * Wait for given number of seconds for element with given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Duration... timeOut) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOut.length > 0 ? timeOut[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }
}
