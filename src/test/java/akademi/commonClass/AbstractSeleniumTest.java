package akademi.commonClass;

import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


public abstract class  AbstractSeleniumTest {


    protected String type;
    public AbstractSeleniumTest(String type){
        this.type = type;
    }

    public WebDriver getDriver(String endPoint){
        return WebDriverFactory.getEndPoint(endPoint,type);
    }

    public abstract void run();

    protected void sleep(int timeout){
        try {
            Thread.sleep(timeout * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement updateFrameSendKeys(String endpoint,String xpath,boolean click, String keys){
        WebDriver driver = getDriver(endpoint);
        WebElement element = driver.findElement(By.xpath(xpath));
        driver.switchTo().frame(0);
        driver.findElement(By.id("tinymce")).sendKeys();
        driver.switchTo().defaultContent();
        return updateElement(endpoint,xpath,true,false,null,false);
    }

    public void changeURL(String endpoint,String value){
        WebDriver driver = getDriver(endpoint);
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(value);
    }

    public WebElement updateElement(String endpoint,String xpath,boolean clear, String keys){
        return updateElement(endpoint,xpath,false,true,null,false);
    }

    public WebElement updateClickableElement(String endpoint,String xpath,boolean click){
        sleep(1);
        return updateElement(endpoint,xpath,true,false,null,false);
    }
   /* public WebElement clearElement(String endpoint,String xpath, String keys){
        return updateElement(endpoint,xpath,true,false,"(Keys.DELETE)");

    }*/

    public WebElement updateElement(String endpoint,String xpath,boolean click,boolean clear, String keys,boolean backspace){
        WebDriver driver = getDriver(endpoint);
        WebElement element = driver.findElement(By.xpath(xpath));
       // String a= element.getText();
        //Actions actions = new Actions(driver);
        if(clear){
            element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        }
        if(backspace){
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(Keys.BACK_SPACE);


        }
        if(click){
            element.click();
        }
        if(keys != null){
            element.sendKeys(keys);
        }


        return element;
    }

    public void executeScript(String endpoint, String script, Object...args){
        ((JavascriptExecutor)getDriver(endpoint)).executeScript(script,args);
       // executeScript(endpoint,script,args);
    }


}
