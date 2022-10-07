package cn.kizzzy.animations;

public class AnimatorPlayer implements Runnable {
    
    private final Animator animator;
    
    private volatile boolean running;
    
    private Thread thread;
    
    public AnimatorPlayer(Animator animator) {
        this.animator = animator;
    }
    
    public void reset() {
        running = false;
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread = null;
        }
    }
    
    public void start() {
        reset();
        
        running = true;
        
        thread = new Thread(this);
        thread.start();
        
    }
    
    public void stop() {
        running = false;
        thread = null;
    }
    
    public void prev() {
        animator.update(AnimatorUpdateType.PREV);
    }
    
    public void next() {
        animator.update(AnimatorUpdateType.NEXT);
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                animator.update(AnimatorUpdateType.TIME);
                
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
    
    public boolean isPlaying() {
        return running;
    }
    
    public Animator getAnimator() {
        return animator;
    }
}
