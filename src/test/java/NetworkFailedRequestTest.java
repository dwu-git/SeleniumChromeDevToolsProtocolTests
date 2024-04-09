import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.fetch.Fetch;
import org.openqa.selenium.devtools.v121.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v121.network.model.ErrorReason;

import java.util.List;
import java.util.Optional;

public class NetworkFailedRequestTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> requestPattern = Optional.of(List.of(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));

        devTools.send(Fetch.enable(requestPattern, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }
}
