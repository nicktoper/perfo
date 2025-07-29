/*    */ package io.manycore.fractals;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class AnimationPanel
/*    */   extends JPanel
/*    */ {
/*    */   private BufferedImage bufferedImage;
/*    */   
/*    */   protected void paintComponent(Graphics g) {
/* 13 */     super.paintComponent(g);
/* 14 */     if (this.bufferedImage != null) {
/* 15 */       g.drawImage(this.bufferedImage, 0, 0, getWidth(), getHeight(), this);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setBufferedImage(BufferedImage bufferedImage) {
/* 20 */     this.bufferedImage = bufferedImage;
/*    */   }
/*    */ }


/* Location:              /home/n/temp/optim.jar!/io/manycore/fractals/AnimationPanel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */