package io.manycore.fractals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

public class MandelbrotSet {

    private final int width;
    private final int height;
    private final int maxIter;
    private final double zoom;
    private final BufferedImage image;

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
        IntStream.range(0, height).parallel().forEach(y -> {
            for (int x = 0; x < width; x++) {
                double cX = (x - (width / 2.0)) / zoom;
                double cY = (y - (height / 2.0)) / zoom;
                double zx = 0;
                double zy = 0;
                int iter = maxIter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                int color = iter | (iter << 8);
                image.setRGB(x, y, color);
            }
        });
    }

    public void saveImage(String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "bmp", outputfile);
            System.out.println("Image successfully saved: " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error: Could not save image.");
        }
    }

}
