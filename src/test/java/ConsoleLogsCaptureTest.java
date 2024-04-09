import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class ConsoleLogsCaptureTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();

        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = logEntries.getAll();

        for (LogEntry log : logs)
            System.out.println(log.getMessage());
    }
}
