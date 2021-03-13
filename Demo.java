import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Utils.ImageSteganography;

public class Demo {
    public static void main(String[] args) {
        BufferedImage container = null;
        BufferedImage secret = null;
        ImageSteganography is = new ImageSteganography();
        
        try {
            container = ImageIO.read(new File("data/container.png"));
            secret = ImageIO.read(new File("data/secret.png"));
            
            // is.to_grayscale(secret);

            is.encode(container, secret);
            
            File outputfile = new File("data/encoded.png");
            ImageIO.write(container, "png", outputfile);

            container = ImageIO.read(new File("data/encoded.png"));
            secret = is.decode(container);
            outputfile = new File("decoded.png");
            ImageIO.write(secret, "png", outputfile);

        }
        catch (Exception e) {

        }
    }
}