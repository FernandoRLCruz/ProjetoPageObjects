package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {

    public static final String USERNAME = "fernandocruz7";
    public static final String AUTOMATE_KEY = "hpVgYyeBPRR7JyAvQyQg";
    public static final String URLBrowserStack = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        System.setProperty("webdriver.chrome.driver", "/home/crazydogretro/IdeaProjects/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;

    }

    public static WebDriver createBrowserStack() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "8.1");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "68.0");
        caps.setCapability("resolution", "2048x1536");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.debug", "true");



        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URLBrowserStack), caps);
        } catch (MalformedURLException e) {
            System.out.println("Erro com URL: " + e.getMessage());
        }

        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.get("http://www.juliodelima.com.br/taskit/");

        return navegador;


    }

}
