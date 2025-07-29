/*    */ package io.manycore.fractals;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class MandelbrotSet
/*    */ {
/*    */   private final int width;
/*    */   private final int height;
/* 12 */   private int maxIter = 570;
/* 13 */   private double zoom = 2500.0D;
/*    */   private BufferedImage image;
/*    */   
/*    */   public MandelbrotSet(int width, int height, double zoom, int max_iter) {
/* 17 */     this.width = width;
/* 18 */     this.height = height;
/* 19 */     this.zoom = zoom;
/* 20 */     this.maxIter = max_iter;
/* 21 */     this.image = new BufferedImage(width, height, 1);
/*    */   }
/*    */   
/*    */   public BufferedImage getImage() {
/* 25 */     return this.image;
/*    */   }
/*    */   
/*    */   public void drawFractal() {
/* 29 */     for (int y = 0; y < this.height; y++) {
/* 30 */       for (int x = 0; x < this.width; x++) {
/* 31 */         double zx = 0.0D;
/* 32 */         double zy = 0.0D;
/* 33 */         double cX = (x - this.width / 2) / this.zoom;
/* 34 */         double cY = (y - this.height / 2) / this.zoom;
/* 35 */         int iter = this.maxIter;
/* 36 */         while (zx * zx + zy * zy < 4.0D && iter > 0) {
/* 37 */           double tmp = zx * zx - zy * zy + cX;
/* 38 */           zy = 2.0D * zx * zy + cY;
/* 39 */           zx = tmp;
/* 40 */           iter--;
/*    */         } 
/* 42 */         this.image.setRGB(x, y, iter | iter << 8);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void saveImage(String filename) {
/*    */     try {
/* 49 */       File outputfile = new File(filename);
/* 50 */       ImageIO.write(this.image, "bmp", outputfile);
/* 51 */       System.out.println("Image successfully saved: " + outputfile.getAbsolutePath());
/* 52 */     } catch (IOException e) {
/* 53 */       System.out.println("Error: Could not save image.");
/* 54 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/n/temp/optim.jar!/io/manycore/fractals/MandelbrotSet.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */