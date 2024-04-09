import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthenticationTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/users/chromedriver-mac-x64/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
        ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));

        driver.get("https://httpbin.org/basic-auth/foo/bar");
    }
}
