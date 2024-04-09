import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.fetch.Fetch;

import java.util.Optional;

public class NetworkMockingTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            if (requestPaused.getRequest().getUrl().contains("shetty")) {
                String mockedUrl = requestPaused.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(mockedUrl);

                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockedUrl),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
            }
            devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
    }
}
