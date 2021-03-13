package Utils;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImageSteganography {
    public ImageSteganography() {
    }

    /**
     * Convert rgb color to grayscale
     * @param c
     * @return Integer value of grayscale pixel translated from rgb pixel
     */
    private int rgb_to_grayscale(Color c) {
        return  (int)(c.getRed() * 0.299) + 
                (int)(c.getGreen() * 0.587) + 
                (int)(c.getBlue() *0.114);
    }

    private int[] partition_grayscale(int g) {
        int[] parts = new int[4];
        for (int i = 0; i < 4; i++) {
            parts[3 - i] = (g >> (i * 2)) & 3;
        }
        return parts;
    }

    private int get_grayscale(Color c) {
        return ((c.getRed() & 3) << 6) + ((c.getGreen() & 3) << 4) + ((c.getBlue() & 3) << 2) + (c.getAlpha() & 3);
    }

    private int encode_pixel(int val, int grayscaleColor) {
        // Remove 2 last bits
        val >>= 2;
        val <<= 2;

        return val + grayscaleColor;
    }

    /**
     * Convert a colored image to grayscale image
     * @param coloredImage to be convert to grayscale
     */
    public void to_grayscale(BufferedImage coloredImage) {
        int w = coloredImage.getWidth();
        int h = coloredImage.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int c = rgb_to_grayscale(new Color(coloredImage.getRGB(i, j)));

                Color newColor = new Color(c, c, c);
                
                coloredImage.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void encode(BufferedImage container, BufferedImage secret) {
        int w = Math.min(container.getWidth(), secret.getWidth());
        int h = Math.min(container.getHeight(), secret.getHeight());

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color c = new Color(container.getRGB(i, j), true);
                int[] g = partition_grayscale(new Color(secret.getRGB(i, j)).getRed());
                Color newColor = new Color(
                    encode_pixel(c.getRed(), g[0]),
                    encode_pixel(c.getGreen(), g[1]),
                    encode_pixel(c.getBlue(), g[2]),
                    encode_pixel(c.getAlpha(), g[3])
                );

                container.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public BufferedImage decode(BufferedImage container) {
        int w = container.getWidth();
        int h = container.getHeight();
        
        BufferedImage secret = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color c = new Color(container.getRGB(i, j), true);
                int g = get_grayscale(c);
                secret.setRGB(i, j, new Color(g, g, g).getRGB());
            }
        }
        return secret;
    }


}
