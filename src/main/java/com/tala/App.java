package com.tala;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Throwable
    {


        //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        // And now use this to visit Google
        driver.get("http://www.facebook.com");
        try {
            WebElement element1 = driver.findElement(By.id("email"));
            element1.sendKeys("sahanurmondal@gmail.com");

            WebElement element2 = driver.findElement(By.id("pass"));
            element2.sendKeys("saha1234");

            WebElement element3 = driver.findElement(By.id("loginbutton"));
            element3.click();
            System.out.println("Login");

            //WebElement selectionBtn = driver.findElement(By.id("userNavigationLabel"));
            driver.findElement(By.xpath("//*[@id=\"pageLoginAnchor\"]")).click();
            WebElement signOut = driver.findElement(By.xpath("//span[@class='_54nh'][contains(.,'Log Out')]"));
           // signOut.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }



    }
}
