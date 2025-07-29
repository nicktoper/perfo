package io.manycore.fractals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MandelbrotSet {

    private final int width;
    private final int height;
    private int maxIter = 570;
    private double zoom = 2500;
    private BufferedImage image;

    public MandelbrotSet(int width, int height, double zoom, int max_iter) {
        this.width = width;
        this.height = height;
        this.zoom = zoom;
        this.maxIter = max_iter;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void drawFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - (width / 2)) / zoom;
                double cY = (y - (height / 2)) / zoom;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                image.setRGB(x, y, iter | (iter << 8));
            }
        }
    }

    public void saveImage(String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "bmp", outputfile);
            System.out.println("Image successfully saved: " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: Could not save image.");
            e.printStackTrace();
        }
    }

}
