/*    */ package io.manycore;
/*    */ import io.manycore.fractals.MandelbrotAnim;
/*    */ import java.util.concurrent.ExecutorCompletionService;
/*    */ 
/*    */ public class AnimationMain {
/*    */   public static void main(String[] args) throws InterruptedException {
/*  7 */     threadpool = new ExecutorCompletionService(completion = Executors.newFixedThreadPool(16)); sequencedResults = new ConcurrentHashMap<>(); if (GraphicsEnvironment.isHeadless()) {
/*  8 */       throw new RuntimeException("This program can't run on headless Java.");
/*    */     }
/*    */     
/* 11 */     MandelbrotAnim m = new MandelbrotAnim(1920, 1080);
/* 12 */     m.animate(100);
/* 13 */     Thread.sleep(5000L); System.out.println("Shutting down thread pool"); completion.shutdown();
/*    */   }
/*    */   
/*    */   public static ExecutorCompletionService threadpool;
/*    */   public static ExecutorService completion;
/*    */   public static ConcurrentHashMap sequencedResults;
/*    */ }


/* Location:              /home/n/temp/optim.jar!/io/manycore/AnimationMain.class
 * Java compiler version: 19 (63.0)
 * JD-Core Version:       1.1.3
 */