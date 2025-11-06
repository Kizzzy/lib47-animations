package cn.kizzzy.animations;

public class AnimatorPlayer implements Runnable {
    
    private final Animator animator;
    
    private Thread thread;
    private volatile boolean running;
    
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
}
