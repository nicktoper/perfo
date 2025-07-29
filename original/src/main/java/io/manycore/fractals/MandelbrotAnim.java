package io.manycore.fractals;

import io.manycore.magicator.Debug;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serial;

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

        setTitle("BufferedImage Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        add(animationPanel);
        setVisible(true);

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

        for (int i = 0; i < numberFramesToAnimate; i++) {



            MandelbrotSet m = new MandelbrotSet(width, height, zoom, maxIter);

            // Start timing in nanoseconds
            long start = System.nanoTime();
            m.drawFractal(); // Ensure this blocks until fully done
            long end = System.nanoTime();

            // Convert the elapsed time from ns to ms
            double tpfMs = (end - start) / 1_000_000.0;

            // Rolling FPS over one second

            // Get the rendered image and draw info text
            BufferedImage bi = m.getImage();
            setFrameText(bi, i, (int) zoom, tpfMs, currentFps);
            computeFps();
            // Part un//(

            // Update the panel
            this.animationPanel.setBufferedImage(bi);
            this.animationPanel.repaint();
            zoom++;
        }
    }


    /**
     * Renders the time-per-frame (ms) and FPS onto the image.
     */
    public static void setFrameText(BufferedImage bi, int frameIndex, int zoom, double tpfMs, int currentFps) {
        Graphics2D g2d = bi.createGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 30));

        // Format the time-per-frame nicely to 2 decimal places
        String tpfString = String.format("%.2f", tpfMs);

        double avg = frameToAnimate/ ((lastTime - firstTime)/1_000_000_000.0) ; // Average time in ms
        String avgString = String.format("%.1f", avg);

        //String text = STR."Frame: \{frameIndex}  Zoom: \{zoom}  Time per frame (ms): \{tpfString}  FPS : \{currentFps}";
        String text =  "Frame:" + frameIndex + "; average FPS: " + avgString;

        g2d.drawString(text, 10, 40);
        g2d.dispose();  // Clean up graphics context
    }

}
