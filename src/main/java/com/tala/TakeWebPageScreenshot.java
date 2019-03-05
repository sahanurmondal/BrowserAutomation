package com.tala;

import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TakeWebPageScreenshot {
    private static Logger logger = LoggerFactory.getLogger(TakeWebPageScreenshot.class);
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");

        // And now use this to visit Google
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ChromeDriver driver = new ChromeDriver();
                String json="";
                try {
                    driver.get("https://www.talasecurity.io/");
                    //Thread.sleep(2000);
                    List<WebElement> allFormElements = driver.findElements(By.tagName("form"));
                    int i=0;
                    String tmpDirForScreenshot = "D:\\Tala docs\\JSscreenshots\\Formscreenshot\\";
                    //File screenshot2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                   new File(tmpDirForScreenshot).mkdirs();
                    //FileUtils.copyFile(screenshot2, new File(tmpDirForScreenshot+ "Webpage"+ ".png"));
                    //System.out.println("Saving webpage screenshot in "+ tmpDirForScreenshot);
                   // Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
                   // ImageIO.write(screenshot.getImage(),"PNG",new File(tmpDirForScreenshot+ "CompleteWebpage"+ ".png"));
                   // System.out.println("full web page image created");
                    Stopwatch loggerStopWatch = Stopwatch.createStarted();
                    allFormElements.forEach(formElement -> {
                        //added for taking screenshot of login form
                        try {
                            //String tmpDirForScreenshot="D:\\Tala docs\\screenshots\\";

                           // File scrFile = formElement.getScreenshotAs(OutputType.FILE);
                            //  if (!new File(tmpDirForScreenshot).exists()) {
                           // new File(tmpDirForScreenshot).mkdirs();
                            //}
                            //FileUtils.copyFile(scrFile, new File(tmpDirForScreenshot+ "Form1"+ ".png"));
                           // System.out.println("Saving Form screenshot in "+ tmpDirForScreenshot);
                           // System.out.println("image created");
                            Screenshot formScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
                                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                                    .takeScreenshot(driver, formElement);
                            ImageIO.write(formScreenshot.getImage(),"PNG",new File(tmpDirForScreenshot+ "Form2"+ ".png"));
                            System.out.println("form image created");
                            /*BufferedImage  fullImg = ImageIO.read(screenshot2);

// Get the location of element on the page
                            Point point = formElement.getLocation();

// Get width and height of the element
                            int eleWidth = formElement.getSize().getWidth();
                            int eleHeight = formElement.getSize().getHeight();

// Crop the entire page screenshot to get only element screenshot
                            BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                                    eleWidth, eleHeight);
                            File scrFile2 = new File(tmpDirForScreenshot+ "cropImg"+ ".png");
                            ImageIO.write(eleScreenshot, "png", scrFile2);
                            File screenshotLocation = new File("D:\\Tala docs\\JSscreenshots\\Formscreenshot\\");
                            FileUtils.copyFile(screenshot2, screenshotLocation);
                           // System.out.println("image created by cropping ");*/
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                    loggerStopWatch.stop();
                    logger.info("Saving screenshot for form  took: {} secs", loggerStopWatch.elapsed(TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    driver.quit();
                }

            }
        });
    }
}
