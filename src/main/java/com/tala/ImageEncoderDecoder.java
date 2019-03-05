package com.tala;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageEncoderDecoder {
    public static void main(String[] args) {
        String tmpDirForScreenshot = "D:\\Tala docs\\";
       // new File(tmpDirForScreenshot).getParentFile().mkdirs();
        FileInputStream fin = null;
        File file = new File("D:\\Tala docs\\pluralsight_encoded image.txt");
        try  {
           // FileOutputStream imageOutFile = new FileOutputStream(tmpDirForScreenshot);
           // FileInputStream inputStream = new FileInputStream();
            // Converting a Base64 String into Image byte array

            String content = FileUtils.readFileToString(file, "UTF-8");
            byte[] imageByteArray = Hex.decodeHex(content.toCharArray());
           // fin = new FileInputStream(file);
           // byte fileContent[] = new byte[(int)file.length()];
            //fin.read(fileContent);
            //String s = new String(fileContent);
          //  byte[] bytearray =Base64.getEncoder().encode(fileContent);
           // baos.close();

           // byte[] bytearrayd = Base64.getDecoder().decode(fileContent);
           // byte[] imageByteArray = Base64.getDecoder().decode(fileContent);
           // imageOutFile.write(imageByteArray);
           // BufferedImage imag= ImageIO.read(new ByteArrayInputStream(bytearrayd));
          //  ImageIO.write(imag, "jpg", new File(tmpDirForScreenshot,"snap.jpg"));

           /* BufferedImage bImage = ImageIO.read(new File("D:\\Tala docs\\Form23.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos );
            byte [] data = bos.toByteArray();*/
            //byte[] imageByteArray = Base64.getDecoder().decode(fileContent);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByteArray);
           /*
            ImageInputStream stream = ImageIO.createImageInputStream(bis);
            ImageReader reader = (ImageReader)stream;
            ImageReadParam param = reader.getDefaultReadParam();
            reader.setInput(stream, true, true);
            BufferedImage bi;
            try {
                bi = reader.read(0, param);
            } finally {
                reader.dispose();
                stream.close();
            }*/
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File("D:\\Tala docs\\output123.png") );
            System.out.println("image created");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }catch (Exception e){
            System.out.println("Exception while reading the Image in try " + e);
        }
    }

}
