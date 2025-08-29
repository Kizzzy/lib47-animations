package cn.kizzzy.animations;

public class Animator {
    
    private final StateInfo stateInfo =
        new StateInfo();
    
    private float speed = 1f;
    
    private long lastTime;
    
    private boolean loop = true;
    
    private AnimationController controller;
    
    public void update(AnimatorUpdateType updateType) {
        long elapse = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (speed == 0 || controller == null) {
            return;
        }
        
        if (stateInfo.time >= stateInfo.length || stateInfo.time < 0) {
            stateInfo.time = 0;
            // todo notify end of animation
        }
        
        if (stateInfo.time < stateInfo.length) {
            if (stateInfo.callback != null) {
                stateInfo.callback.beforeUpdate();
            }
            
            elapse = (long) (elapse * speed);
            
            stateInfo.loop = loop;
            stateInfo.updateType = updateType;
            stateInfo.elapse = elapse;
            stateInfo.time += elapse;
            
            controller.update(stateInfo);
            
            if (stateInfo.callback != null) {
                stateInfo.callback.afterUpdate();
            }
        }
    }
    
    public void jumpTo(int frameIndex) {
        // todo
    }
    
    public void setController(AnimationController controller, boolean reset) {
        reset = reset || this.controller == null && controller != null;
        
        this.controller = controller;
        
        if (reset) {
            lastTime = System.currentTimeMillis();
            stateInfo.enterTime = 0;
            stateInfo.evaluatorKvs.clear();
            if (controller != null) {
                stateInfo.time = 0;
                stateInfo.length = controller.length();
            }
        }
    }
    
    public AnimationController getController() {
        return controller;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public boolean getLoop() {
        return loop;
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    public StateInfo getStateInfo() {
        return stateInfo;
    }
}
