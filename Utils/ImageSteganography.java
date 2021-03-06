package Utils;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImageSteganography {
    public ImageSteganography() {}

    private int rgb_to_grayscale(Color c) {
        return  (int)(c.getRed() * 0.299) + 
                (int)(c.getGreen() * 0.587) + 
                (int)(c.getBlue() * 0.114);
    }

    private int[] partition_grayscale(int g) {
        int[] parts = new int[4];
        for (int i = 0; i < 4; i++) {
            parts[3 - i] = (g >> (i * 2)) & 3;
        }
        return parts;
    }

    private int[] partition_color(Color c) {
        return new int[]{c.getRed() >> 4, c.getGreen() >> 4, c.getBlue() >> 4, c.getAlpha() >> 4};
    }

    private int get_grayscale(Color c) {
        return ((c.getRed() & 3) << 6) + ((c.getGreen() & 3) << 4) + ((c.getBlue() & 3) << 2) + (c.getAlpha() & 3);
    }

    private int get_color(Color c) {
        return new Color((c.getRed() & 15) << 4, (c.getGreen() & 15) << 4, (c.getBlue() & 15) << 4, (c.getAlpha() & 15) << 4).getRGB();
    }

    private int encode_pixel(int val, int grayscaleColor) {
        // Remove 2 last bits
        val >>= 2;
        val <<= 2;

        return val + grayscaleColor;
    }

    private Color encode_pixel(Color c, int[] g) {
        return new Color(   (c.getRed() & (15 << 4)) + g[0], 
                            (c.getGreen() & (15 << 4))  + g[1], 
                            (c.getBlue() & (15 << 4)) + g[2], 
                            (c.getAlpha() & (15 << 4))  + g[3]);
    }

    /**
     * Convert a colored image to grayscale image
     * @param coloredImage to be convert to grayscale
     */
    private void to_grayscale(BufferedImage coloredImage) {
        int w = coloredImage.getWidth();
        int h = coloredImage.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int c = rgb_to_grayscale(new Color(coloredImage.getRGB(i, j)));
                
                coloredImage.setRGB(i, j, new Color(c, c, c).getRGB());
            }
        }
    }

    public void encode_grayscale(BufferedImage container, BufferedImage secret) throws SteganographyException {
        int container_w = container.getWidth();
        int container_h = container.getHeight();
        int secret_w = secret.getWidth();
        int secret_h = secret.getHeight();

        if (secret_w > container_w || secret_h > container_h) {
            throw new SteganographyException("Container image must larger than Secret image");
        }
        
        this.to_grayscale(secret);

        for (int i = 0; i < container_w; i++) {
            for (int j = 0; j < container_h; j++) {
                int[] g;
                
                if (i < secret_w && j < secret_h)
                    g = partition_grayscale(new Color(secret.getRGB(i, j)).getRed());
                else
                    g = new int[]{0, 0, 0, 0};

                Color c = new Color(container.getRGB(i, j), true);
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

    public void encode_color(BufferedImage container, BufferedImage secret) throws SteganographyException {
        
        
        int container_w = container.getWidth();
        int container_h = container.getHeight();
        int secret_w = secret.getWidth();
        int secret_h = secret.getHeight();

        if (secret_w > container_w || secret_h > container_h) {
            throw new SteganographyException("Container image must larger than Secret image");
        }

        for (int i = 0; i < container_w; i++) {
            for (int j = 0; j < container_h; j++) {
                int[] g;
                
                if (i < secret_w && j < secret_h)
                    g = partition_color(new Color(secret.getRGB(i, j)));
                else
                    g = new int[]{0, 0, 0, 0};

                Color c = new Color(container.getRGB(i, j), true);

                Color newColor = encode_pixel(c, g);
                

                container.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public BufferedImage decode_grayscale(BufferedImage container) {
        int w = container.getWidth();
        int h = container.getHeight();
        
        BufferedImage secret = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int g = get_grayscale(new Color(container.getRGB(i, j), true));
                secret.setRGB(i, j, new Color(g, g, g).getRGB());
            }
        }
        return secret;
    }

    public BufferedImage decode_color(BufferedImage container) {
        int w = container.getWidth();
        int h = container.getHeight();
        
        BufferedImage secret = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                // int g = get_grayscale(new Color(container.getRGB(i, j), true));
                // secret.setRGB(i, j, new Color(g, g, g).getRGB());
                secret.setRGB(i, j, get_color(new Color(container.getRGB(i, j))));
            }
        }
        return secret;
    }
}
