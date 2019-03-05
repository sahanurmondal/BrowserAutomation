package com.tala;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrowserAutomation {
    public static void main(String[] args) throws Exception {

        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(coreCount);
        String xp2 = "//*[@id=\"city\"]/table/tbody/tr/td/fieldset[1]/div/a[2]/div/p";
        String xp1 = "/html/body/div[4]/div[4]/div/div[3]/a/div";
        String url1 = "https://www.tutorialspoint.com/";
        String url2 = "https://www.javatpoint.com/";
        HashMap<Integer,String> url=new HashMap<Integer, String>();
        HashMap<Integer,String> xp=new HashMap<Integer, String>();
        url.put(0,url1);
        url.put(1,url2);
        xp.put(0,xp1);
        xp.put(1,xp2);

       // for (int i = 1; i <= 2; i++) {
            executor.execute(new MyRunnable(url.get(0), xp.get(0)));
            executor.execute(new MyRunnable(url.get(1), xp.get(1)));
      //  }

    }

}

class MyRunnable implements Runnable {
    private String url, xpath;

    MyRunnable(String url, String xpath) {
        this.url = url;
        this.xpath = xpath;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        try {
            driver.get(url);
            System.out.println(driver.findElement(By.xpath(xpath)).getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}