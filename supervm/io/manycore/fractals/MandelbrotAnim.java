/*     */ package io.manycore.fractals;
/*     */ 
/*     */ import io.manycore.AnimationMain;
/*     */ import io.manycore.magicator.Debug;
/*     */ import io.manycore.magicator.loop.LoopRunner;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JFrame;
/*     */ 
/*     */ public class MandelbrotAnim extends JFrame {
/*     */   private final int width;
/*  14 */   public static int currentFps = 1; private final int height;
/*  15 */   public static int frameToAnimate = -1;
/*     */   
/*  17 */   public static int frameCount = 0;
/*  18 */   public static long lastTime = System.nanoTime();
/*  19 */   public static final long firstTime = System.nanoTime();
/*     */   
/*     */   private final AnimationPanel animationPanel;
/*     */ 
/*     */   
/*     */   public MandelbrotAnim(int width, int height) {
/*  25 */     this.width = width;
/*  26 */     this.height = height;
/*     */     
/*  28 */     this.animationPanel = new AnimationPanel();
/*     */     
/*  30 */     setTitle("BufferedImage Display");
/*  31 */     setDefaultCloseOperation(3);
/*  32 */     setSize(width, height);
/*  33 */     setLocationRelativeTo((Component)null);
/*  34 */     add(this.animationPanel);
/*  35 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @Debug
/*     */   public static void computeFps() {
/*  41 */     long now = System.nanoTime();
/*  42 */     frameCount++;
/*     */ 
/*     */     
/*  45 */     if (now - lastTime >= 1000000000L) {
/*  46 */       currentFps = frameCount;
/*  47 */       frameCount = 0;
/*  48 */       lastTime = now;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setFrameText(BufferedImage bi, int frameIndex, int zoom, double tpfMs, int currentFps) {
/*  94 */     Graphics2D g2d = bi.createGraphics();
/*  95 */     g2d.setFont(new Font("Arial", 1, 30));
/*     */ 
/*     */     
/*  98 */     String tpfString = String.format("%.2f", new Object[] { Double.valueOf(tpfMs) });
/*     */     
/* 100 */     double avg = frameToAnimate / (lastTime - firstTime) / 1.0E9D;
/* 101 */     String avgString = String.format("%.1f", new Object[] { Double.valueOf(avg) });
/*     */ 
/*     */     
/* 104 */     String text = "Frame:" + frameIndex + "; average FPS: " + avgString;
/*     */     
/* 106 */     g2d.drawString(text, 10, 40);
/* 107 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public void animate(int arg0) {
/*     */     frameToAnimate = arg0;
/*     */     double d = 100.0D;
/*     */     char c = 'Ⱥ';
/*     */     byte b = 0;
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 0));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 1));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 2));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 3));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 4));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 5));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 6));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 7));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 8));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 9));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 10));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 11));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 12));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 13));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 14));
/*     */     AnimationMain.completion.execute((Runnable)new LoopRunner(arg0, d, c, b, this.width, this.height, 15));
/*     */     while (b < arg0) {
/*     */       while (AnimationMain.sequencedResults.containsKey(Integer.valueOf(b))) {
/*     */         Object object = AnimationMain.sequencedResults.remove(Integer.valueOf(b));
/*     */         this.animationPanel.setBufferedImage((BufferedImage)object);
/*     */         this.animationPanel.repaint();
/*     */         b++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/n/temp/optim.jar!/io/manycore/fractals/MandelbrotAnim.class
 * Java compiler version: 19 (63.0)
 * JD-Core Version:       1.1.3
 */