package io.manycore.fractals;

import io.manycore.magicator.Debug;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotAnim extends JFrame {

    private final int width;
    private final int height;
    public static int currentFps = 1;
    public static int frameToAnimate = -1;

    public static int frameCount = 0;
    public static long lastTime = System.nanoTime();
    public final static long firstTime = System.nanoTime();

    private final AnimationPanel animationPanel;

    public MandelbrotAnim(int width, int height) {
        this.width = width;
        this.height = height;

        this.animationPanel = new AnimationPanel();

        setTitle("Mandelbrot Fractal Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        add(animationPanel);
        setVisible(true);

        // Enable hardware acceleration
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.d3d", "true");
    }

    @Debug
    public static void computeFps() {
        long now = System.nanoTime();
        frameCount++;

        // 1 second = 1_000_000_000 nanoseconds
        if ((now - lastTime) >= 1_000_000_000) {
            currentFps = frameCount;
            frameCount = 0;
            lastTime = now;

        }
    }


    public void animate(int numberFramesToAnimate) {
        frameToAnimate = numberFramesToAnimate;

        double zoom = 100;  // org example: 2500
        int maxIter = 570;  // org example: 570

        // Pre-allocate font for better performance
        Font frameFont = new Font("Arial", Font.BOLD, 30);

        for (int i = 0; i < numberFramesToAnimate; i++) {
            MandelbrotSet m = new MandelbrotSet(width, height, zoom, maxIter);

            // Start timing in nanoseconds
            long start = System.nanoTime();
            m.drawFractal(); // Ensure this blocks until fully done
            long end = System.nanoTime();

            // Convert the elapsed time from ns to ms
            double tpfMs = (end - start) / 1_000_000.0;

            // Get the rendered image and draw info text
            BufferedImage bi = m.getImage();
            setFrameText(bi, i, tpfMs, currentFps, frameFont);
            computeFps();

            // Update the panel - use invokeLater for better UI responsiveness
            final BufferedImage finalBi = bi;
            javax.swing.SwingUtilities.invokeLater(() -> {
                this.animationPanel.setBufferedImage(finalBi);
                this.animationPanel.repaint();
            });

            zoom++;
        }
    }

    /**
     * Optimized text rendering with reused font object
     */
    public static void setFrameText(BufferedImage bi, int frameIndex, double tpfMs, int currentFps, Font font) {
        Graphics2D g2d = bi.createGraphics();

        // Enable text antialiasing for better quality
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(font);

        // Format the time-per-frame nicely to 2 decimal places
        String tpfString = String.format("%.2f", tpfMs);

        double avg = frameToAnimate/ ((lastTime - firstTime)/1_000_000_000.0) ; // Average time in ms
        String avgString = String.format("%.1f", avg);

        String text =  "Frame:" + frameIndex + "; Avg FPS: " + avgString + "; TPF: " + tpfString + "ms; Current FPS: " + currentFps;

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, 10, 40);
        g2d.dispose();  // Clean up graphics context
    }

}
