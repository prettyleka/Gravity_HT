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

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static java.awt.Toolkit.getDefaultToolkit;
import static javax.imageio.ImageIO.write;
import static org.apache.commons.io.FileUtils.copyFile;


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
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }


    /** Find all elements using given locator */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }


    /** Click on element with given locator when its visible */
    public void click(By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).click();
    }


    /** Type given text into element with given locator */
    public void type(String text, By locator) {
        waitForVisibilityOf(locator, Duration.ofSeconds(5));
        find(locator).sendKeys(text);
    }


    /** Get URL of current page from browser */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    /** Get title of current page */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }


    /** Get source of current page */
    public String getCurrentPageSource() {
        return driver.getPageSource();
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

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        Set<String> handles = driver.getWindowHandles();
        int newTab = handles.size() - 1;
        driver.switchTo().window(handles.toArray()[newTab].toString());
    }

    public void switchTab(int num) {
        Set<String> handles = driver.getWindowHandles();
        driver.switchTo().window(handles.toArray()[num].toString());
    }

    public void closeTab(int num) {
        Set<String> handles = driver.getWindowHandles();
        driver.switchTo().window(handles.toArray()[num].toString()).close();
    }

    public int getNumberOfTabs() {
        Set<String> handles = driver.getWindowHandles();
        return handles.size();
    }

}
