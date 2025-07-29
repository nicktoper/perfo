package io.manycore.fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationPanel extends JPanel {

    private BufferedImage bufferedImage;

    public AnimationPanel() {
        // Enable double buffering and hardware acceleration
        setDoubleBuffered(true);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bufferedImage != null) {
            Graphics2D g2d = (Graphics2D) g;

            // Enable hardware acceleration and optimized rendering
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

            // Draw the image scaled to panel size
            g2d.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}
