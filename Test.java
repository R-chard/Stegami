import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Utils.ImageSteganography;

public class Test {
    public static void main(String[] args) {
        BufferedImage container = null;
        BufferedImage secret = null;
        ImageSteganography is = new ImageSteganography();
        
        try {
            // container = ImageIO.read(new File("container.jpg"));
            // secret = ImageIO.read(new File("secret.jpg"));
            
            // is.to_grayscale(secret);

            // is.encode(container, secret);
            
            // File outputfile = new File("saved.png");
            // ImageIO.write(container, "png", outputfile);

            container = ImageIO.read(new File("saved.png"));
            secret = is.decode(container);
            File outputfile = new File("new.png");
            ImageIO.write(secret, "png", outputfile);


        }
        catch (IOException ioe) {

        }
    }
}