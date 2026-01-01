package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"sanity", "regression", "master", "dataDriven"})
    @Parameters({"os", "browser"})   // ✅ FIXED HERE
    public void setUp(String os, String br) throws IOException {

        logger = LogManager.getLogger(this.getClass());

        FileReader fil = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(fil);

        ChromeOptions chromeOptions = new ChromeOptions();
        EdgeOptions edgeOptions = new EdgeOptions();

        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        
        if(p.getProperty("execution_env"). equalsIgnoreCase("remote"))
        {
        	DesiredCapabilities capabilities=new DesiredCapabilities();

        	//os
        	if(os.equalsIgnoreCase("windows"))
        	{
        	capabilities.setPlatform(Platform.WIN11);
        	}
        	else if (os.equalsIgnoreCase("mac"))
        	{
        	capabilities.setPlatform(Platform.MAC);
        	}
        	else if (os.equalsIgnoreCase("Linux"))
        	{
        	capabilities.setPlatform(Platform.LINUX);
        	}
        	else
        	{
        	System.out.println("No matching os");
        	return;
        	}
        	//browser
        	switch(br.toLowerCase())
        	{
        	case "chrome": capabilities.setBrowserName("chrome"); break;
        	case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
        	default: System.out.println("No matching browser"); return;
        	}
        	driver=new RemoteWebDriver(new URL("http://172.20.10.7:4444/wd/hub"), capabilities);

        	}
        
        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {

        	switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.out.println("Invalid browser");
                return;
        } 

        }

     /*  switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                System.out.println("Invalid browser");
                return;
        } */

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));
    }

    @AfterClass(groups = {"sanity", "regression", "master", "dataDriven"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomNewString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNewNumber() {
        return RandomStringUtils.randomNumeric(8);
    }

    public String randomNewalphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")
                + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
