import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

public class CdpGeolocationTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 17);
        coordinates.put("longitude", 78);
        coordinates.put("accuracy", 1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        driver.findElements(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']")).get(0).click();
        String title = driver.findElement(By.cssSelector("h1[class='default-ltr-cache-jpuyb8 e9eyrqp8']")).getText();

        System.out.println(title);
    }
}
