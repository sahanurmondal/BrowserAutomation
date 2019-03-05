package com.tala;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import sun.net.www.content.image.png;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
//import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TakeFormScreenshot {
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
                    List<WebElement> allFormElements = driver.findElements(By.tagName("form"));
                    int i=0;

                    allFormElements.forEach(formElement -> {
                        //added for taking screenshot of login form
                        try {
                            //String tmpDirForScreenshot="D:\\Tala docs\\screenshots\\";
                            String tmpDirForScreenshot = "D:\\Tala docs\\JSscreenshots\\Formscreenshot\\";
                            byte[]  screenshot = formElement.getScreenshotAs(OutputType.BYTES);
                            byte[]  screenshot1 = driver.getScreenshotAs(OutputType.BYTES);
                            File scrFile = formElement.getScreenshotAs(OutputType.FILE);
                            //  if (!new File(tmpDirForScreenshot).exists()) {
                            new File(tmpDirForScreenshot).mkdirs();
                            //}
                            FileUtils.copyFile(scrFile, new File(tmpDirForScreenshot+ "Form"+ ".png"));
                            System.out.println("Saving Form screenshot in "+ tmpDirForScreenshot);
                            System.out.println(formElement.getAttribute("id"));
                            ByteArrayInputStream bis = new ByteArrayInputStream(screenshot1);
                            BufferedImage bImage2 = ImageIO.read(bis);
                            ImageIO.write(bImage2, "png", new File("D:\\Tala docs\\output3.png") );
                            System.out.println("image created");

                            File screenshot2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                            BufferedImage  fullImg = ImageIO.read(screenshot2);

// Get the location of element on the page
                            Point point = formElement.getLocation();

// Get width and height of the element
                            int eleWidth = formElement.getSize().getWidth();
                            int eleHeight = formElement.getSize().getHeight();

// Crop the entire page screenshot to get only element screenshot
                            BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                                    eleWidth, eleHeight);
                            ImageIO.write(eleScreenshot, "png", screenshot2);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write( eleScreenshot, "png", baos );
                            baos.flush();
                            byte[] imageInByte = baos.toByteArray();
                            baos.close();
                            System.out.println(" image byte array length "+imageInByte.length);
// Copy the element screenshot to disk
                            File screenshotLocation = new File("D:\\Tala docs\\output4.png");
                            FileUtils.copyFile(screenshot2, screenshotLocation);
                            System.out.println("image created by cropping ");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });

                    /*driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

                    InputStream in = getClass().getResourceAsStream("/screenshot.js");
                    String js = IOUtils.toString(in, "utf-8");
                   // for (int i = 0; i <10 ; i++) {
                         json = String.valueOf(jsExecutor.executeAsyncScript(js));
                    //}


                    FormScreenshot resources = (FormScreenshot) new Gson().fromJson(json, FormScreenshot.class);
                    String tmpDirForScreenshot = "D:\\Tala docs\\JSscreenshots\\";
                    new File(tmpDirForScreenshot).mkdirs();
                    for (String formScreenshot :
                            resources.formScreenshotsMapper) {

                        try (FileOutputStream imageOutFile = new FileOutputStream(tmpDirForScreenshot)) {
                            // Converting a Base64 String into Image byte array
                            byte[] imageByteArray = Base64.getDecoder().decode(formScreenshot);
                            imageOutFile.write(imageByteArray);
                        } catch (FileNotFoundException e) {
                            System.out.println("Image not found" + e);
                        } catch (IOException ioe) {
                            System.out.println("Exception while reading the Image " + ioe);
                        }


                     }*/
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    driver.quit();
                }

    }
        });
}
    }
