import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;

import java.util.List;
import java.util.Optional;

public class BlockNetworkRequestsTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(List.of("*.jpg", "*.css")));

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.xpath("//a[@class='btn btn-lg btn-success']")).click();
        driver.findElement(By.linkText("Selenium")).click();

    }
}
