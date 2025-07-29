/*    */ package io.manycore;
/*    */ 
/*    */ import io.manycore.fractals.MandelbrotSet;
/*    */ 
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] args) {
/*  9 */     MandelbrotSet m = new MandelbrotSet(1920, 1080, 100.0D, 570);
/* 10 */     long start = System.currentTimeMillis();
/* 11 */     m.drawFractal();
/* 12 */     long end = System.currentTimeMillis();
/* 13 */     m.saveImage("mandelbrot.bmp");
/* 14 */     System.out.println("Time taken: " + end - start + "ms");
/*    */   }
/*    */ }


/* Location:              /home/n/temp/optim.jar!/io/manycore/Main.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */