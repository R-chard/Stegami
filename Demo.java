import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Utils.ImageSteganography;
import Utils.ImageUtils;

public class Demo {
    public static void main(String[] args) {
        BufferedImage container = null;
        BufferedImage secret = null;
        ImageSteganography is = new ImageSteganography();
        
        try {
            container = ImageIO.read(new File("data/container.jpg"));
            secret = ImageUtils.resize(1000, 500, container);
            File outputfile = new File("./data/resized.png");
            ImageIO.write(secret, "png", outputfile);
            // secret = ImageIO.read(new File("data/secret.png"));

            // is.encode(container, secret);
            
            // File outputfile = new File("data/encoded.png");
            // ImageIO.write(container, "png", outputfile);

            // container = ImageIO.read(new File("data/encoded.png"));
            // secret = is.decode(container);
            // File outputfile = new File("./data/decoded.png");
            // ImageIO.write(secret, "png", outputfile);



        }
        catch (Exception e) {

        }
    }
}