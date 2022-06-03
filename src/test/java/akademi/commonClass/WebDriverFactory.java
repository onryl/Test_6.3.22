package akademi.commonClass;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static final Map<String,WebDriver> endpoints = new HashMap<>();

    public static WebDriver getEndPoint(String endpoint, String type){
        String key = endpoint+":"+type;
        WebDriver webDriver = endpoints.get(key);

        if(webDriver !=null){
            return webDriver;
        }
        String url = null;
        if(endpoint.equals("gobitoAkademi")){
            url = "https://gobito.akademi.ws/page/90/login";
        }else if(endpoint.equals("newPortal")){
            url = "https://newportal.akademi.ws/portalv2";
        }
        webDriver = create(url,type);
        endpoints.put(key,webDriver);
        return webDriver;
    }

    /**
     * Create Factory
     * @param type
     * @return
     */
    public static WebDriver create(String url,String type){

        WebDriver driver = null;
        switch(type){
            case "chrome":
                if(SystemUtils.IS_OS_LINUX){
                    System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
                }else if(SystemUtils.IS_OS_WINDOWS){
                    System.setProperty("webdriver.chrome.driver","C:\\Users\\mirackasap\\Desktop\\Otomasyon\\Selenium\\executables\\chromedriver.exe");
                }
                ChromeOptions options = new ChromeOptions();
                options.addArguments("user-data-dir","/tmp/selenium-tmp-cd");
                options.setHeadless(false);
                driver = new ChromeDriver();

            //    driverNewPortal=new ChromeDriver();
                driver.get(url);
                driver.manage().window().maximize();
                driver.findElement(By.name("username")).sendKeys("onur(oto)");
                driver.findElement(By.name("password")).sendKeys("Onr_090807");
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                break;
            case "chromium":
                if(SystemUtils.IS_OS_LINUX){
                    System.setProperty("webdriver.chrome.driver","/snap/bin/chromium.chromedriver");
                }else if(SystemUtils.IS_OS_WINDOWS){
                    System.setProperty("webdriver.chrome.driver","ChromeDriver/chromedriver.exe");
                }
                driver = new ChromeDriver();
                //    driverNewPortal=new ChromeDriver();
                driver.get(url);
                driver.manage().window().maximize();
                driver.findElement(By.name("username")).sendKeys("onur(oto)");
                driver.findElement(By.name("password")).sendKeys("Onr_090807");
                driver.findElement(By.xpath("//button[@type='submit']")).click();


                break;
            case "edge":
                if(SystemUtils.IS_OS_LINUX){
                    System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
                }else if(SystemUtils.IS_OS_WINDOWS){
                    System.setProperty("webdriver.chrome.driver","ChromeDriver/chromedriver.exe");
                }

                driver = new EdgeDriver();
                driver.get(url);
                driver.manage().window().maximize();
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;

    }
}
