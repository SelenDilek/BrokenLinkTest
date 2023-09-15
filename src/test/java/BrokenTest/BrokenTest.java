package BrokenTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import java.net.*;
import java.util.List;

@Test
public class BrokenTest {
    WebDriver driver ;
    @BeforeTest
    public void main() throws MalformedURLException, IOException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(1000);
        driver.get("https://pict.edu/extracurricular/");
        Thread.sleep(2000);

       
    }



    public static boolean isUrlValid(String url) throws MalformedURLException, IOException{
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Test
    public void  broken_Test() throws IOException, InterruptedException {



        List<WebElement> links = driver.findElements(By.xpath("//a[contains(@href ,'a')]"));
        for (WebElement link : links){

            String url = link.getAttribute("href");
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            con.connect();
            Thread.sleep(1000);

            int respcode = con.getResponseCode();
            Thread.sleep(1000);

            if(respcode>=400){

                System.out.println("Link text" + link.getText() + "Responce code "+ respcode);


            }
            System.out.println("---------------BROKEN LINK TEST------------");

            Thread.sleep(10000);
        }

        driver.quit();
    }
}
