import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.Request;
import org.openqa.selenium.devtools.v121.network.model.Response;

import java.util.Optional;

public class NetworkLogActivityTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

       devTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
           Request request = requestWillBeSent.getRequest();
           System.out.println(request.getUrl());
       });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            System.out.println(response.getUrl());
            System.out.println(response.getStatus());

            if (response.getStatus().toString().startsWith("4"))
                System.out.println(response.getUrl() + " is failing with status code " + response.getStatus());
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }
}
