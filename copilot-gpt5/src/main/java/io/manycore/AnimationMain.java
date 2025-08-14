package io.manycore;

import io.manycore.fractals.MandelbrotAnim;

public class AnimationMain {
    public static void main(String[] args) throws InterruptedException {
        if (java.awt.GraphicsEnvironment.isHeadless()) {
            throw new RuntimeException("This program can't run on headless Java.");
        }

        MandelbrotAnim m = new MandelbrotAnim(1920, 1080);
        m.animate(100);
        Thread.sleep(5000);
    }
}