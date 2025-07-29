package io.manycore.fractals;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.logging.Logger;

public class MandelbrotSet {

    private static final Logger logger = Logger.getLogger(MandelbrotSet.class.getName());

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
        // Use parallel streams to compute fractal rows in parallel
        IntStream.range(0, height).parallel().forEach(y -> {
            for (int x = 0; x < width; x++) {
                double zx = 0;
                double zy = 0;
                double cX = (x - (width * 0.5)) / zoom;
                double cY = (y - (height * 0.5)) / zoom;
                int iter = maxIter;

                // Optimized iteration with early bailout
                while (iter > 0) {
                    double zx2 = zx * zx;
                    double zy2 = zy * zy;
                    if (zx2 + zy2 >= 4.0) break;

                    zy = 2.0 * zx * zy + cY;
                    zx = zx2 - zy2 + cX;
                    iter--;
                }

                // Set pixel color (thread-safe since each thread works on different y)
                image.setRGB(x, y, iter | (iter << 8));
            }
        });
    }

    public void saveImage(String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "bmp", outputfile);
            System.out.println("Image successfully saved: " + outputfile.getAbsolutePath());
        } catch (IOException e) {
            logger.severe("Error: Could not save image: " + e.getMessage());
        }
    }

}
