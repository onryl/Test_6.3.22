package akademi;

import akademi.commonClass.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Net {
    public static void main(String[] args) {
        //Driverın yerini belirleriz
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gobito\\Desktop\\chromedriver.exe");


        //driverımızı  oluştururuz.
        WebDriver driver = WebDriverFactory.create("https://gobito.akademi.ws/page/90/login", "chrome");
        //get ile bir url ye gideriz.


        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/form/div[1]/input")).sendKeys("sevda");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/form/div[2]/input")); //email için element oluşturuldu
        password.click();
        password.sendKeys("Rsy1992-");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();


        }
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/form/button"));//new By.ByCssSelector("buttonn.btn") //(new By.ByCssSelector(".btn['id=submit']"))
        submitButton.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement egitimListesi = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div/div[1]/a"));
        egitimListesi.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement detayliArama = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div[1]/div/span/a"));
        detayliArama.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement egitimEkle = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/div[2]/form/div[2]/div/button[2]"));
        egitimEkle.click();

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/div/div/div[3]/a/div")).click();
    }
}