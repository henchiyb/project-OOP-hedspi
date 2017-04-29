package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Nhan on 3/6/2017.
 */
public class Utils {

    public static BufferedImage loadImage(String url){
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Vector<BufferedImage> loadSheetAnimation(String url, int width, int height, int border, int imageCount){
        Vector<BufferedImage> imageVector = new Vector<>();
        BufferedImage image = loadImage(url);
        for(int i =0; i < imageCount; i ++){
            int x = i * width + border * (i + 1);
            int y = border;
            BufferedImage subImage = image.getSubimage(x, y, width, height);
            imageVector.add(subImage);
        }
        return imageVector;
    }

    public static BufferedImage imageFlip(BufferedImage image) {
        for (int i = 0; i < image.getWidth() / 2; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int tmp = image.getRGB(i, j);
                image.setRGB(i, j, image.getRGB(image.getWidth() - i - 1, j));
                image.setRGB(image.getWidth() - i - 1, j, tmp);
            }
        }
        return image;
    }
    
    public static Vector<BufferedImage> flipImages(String[] imagesName){
        Vector<BufferedImage> imageVector = new Vector<>();
        for (int i = 0; i < imagesName.length; i++){
            BufferedImage image = loadImage(imagesName[i]);
            imageVector.add(imageFlip(image));
        }
        return imageVector;
    }
}
