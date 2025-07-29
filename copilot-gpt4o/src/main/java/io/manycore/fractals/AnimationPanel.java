package io.manycore.fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationPanel extends JPanel {

    private BufferedImage bufferedImage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bufferedImage != null) {
            g.drawImage(bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}
