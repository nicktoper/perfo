package io.manycore;


import io.manycore.fractals.MandelbrotSet;

public class Main {
    public static void main(String[] args) {
        //MandelbrotSet m = new MandelbrotSet(3840, 2160, 2500, 570);
        MandelbrotSet m = new MandelbrotSet(1920, 1080, 100, 570);
        long start = System.currentTimeMillis();
        m.drawFractal();
        long end = System.currentTimeMillis();
        m.saveImage("mandelbrot.bmp");
        System.out.println("Time taken: " + (end - start) + "ms");
    }
}