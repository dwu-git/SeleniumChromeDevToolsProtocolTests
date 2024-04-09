import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.ConnectionType;

import java.util.Optional;

public class NetworkSpeedTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20_000, 100_000, Optional.of(ConnectionType.ETHERNET)));

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }
}
