package io.manycore.magicator.loop;

import io.manycore.AnimationMain;
import io.manycore.fractals.MandelbrotAnim;
import io.manycore.fractals.MandelbrotSet;
import java.awt.image.BufferedImage;

public class LoopRunner implements Runnable {
  private int param1;
  
  private double param2;
  
  private int param4;
  
  private int param5;
  
  public int width;
  
  public int height;
  
  private int start;
  
  public LoopRunner(int param1, double param2, int param4, int param5, int width, int height, int start) {
    this.param1 = param1;
    this.param2 = param2;
    this.param4 = param4;
    this.param5 = param5;
    this.width = width;
    this.height = height;
    this.start = start;
  }
  
  public void run() {
    int i = this.param1;
    double d = this.param2;
    int j = this.param4;
    int k = this.param5;
    k = this.start;
    while (k < i) {
      int m = k;
      MandelbrotSet mandelbrotSet = new MandelbrotSet(this.width, this.height, d, j);
      long l1 = System.nanoTime();
      mandelbrotSet.drawFractal();
      long l2 = System.nanoTime();
      double d1 = (l2 - l1) / 1000000.0D;
      BufferedImage bufferedImage = mandelbrotSet.getImage();
      MandelbrotAnim.setFrameText(bufferedImage, k, (int)d, d1, MandelbrotAnim.currentFps);
      MandelbrotAnim.computeFps();
      d += 16.0D;
      k += 16;
      /* monitor enter FieldReferenceExpression{lastType=ObjectType{java/util/concurrent/ConcurrentHashMap}, expression=ObjectTypeReferenceExpression{ObjectType{io/manycore/AnimationMain}}, name=sequencedResults, descriptor=Ljava/util/concurrent/ConcurrentHashMap;} */
      AnimationMain.sequencedResults.put(Integer.valueOf(m), bufferedImage);
      /* monitor exit FieldReferenceExpression{lastType=ObjectType{java/util/concurrent/ConcurrentHashMap}, expression=ObjectTypeReferenceExpression{ObjectType{io/manycore/AnimationMain}}, name=sequencedResults, descriptor=Ljava/util/concurrent/ConcurrentHashMap;} */
    } 
  }
}


/* Location:              /home/n/temp/optim.jar!/io/manycore/magicator/loop/LoopRunner.class
 * Java compiler version: 19 (63.0)
 * JD-Core Version:       1.1.3
 */