package Utils;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ImageUtils {
    public static BufferedImage resize(int target_w, int target_h, BufferedImage originalImage) {
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();

        double ratio = Math.max(target_h * 1.0 / h, target_w * 1.0 / w);

        BufferedImage resizedImage = new BufferedImage(target_w, target_h, BufferedImage.TYPE_4BYTE_ABGR);

        int new_w = (int)(w * ratio);
        int new_h = (int)(h * ratio);
        
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, -(new_w - target_w)/2, -(new_h - target_h)/2, new_w, (int)(h * ratio), null);
        graphics2D.dispose();

        return resizedImage;
    }
}
