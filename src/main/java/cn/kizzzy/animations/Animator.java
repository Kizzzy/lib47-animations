package cn.kizzzy.animations;

public class Animator {
    
    private final StateInfo stateInfo =
        new StateInfo();
    
    private float speed = 1f;
    private boolean loop = false;
    
    private long index = -1;
    private AnimationController controller;
    
    public void update(AnimatorUpdateType updateType) {
        if (speed == 0 || controller == null) {
            return;
        }
        
        stateInfo.loop = loop;
        stateInfo.updateType = updateType;
        
        if (stateInfo.callback != null) {
            stateInfo.callback.beforeUpdate();
        }
        
        if ((index == stateInfo.index) && (stateInfo.time < stateInfo.length)) {
            long elapse = 40;
            if (updateType == AnimatorUpdateType.NEXT ||
                updateType == AnimatorUpdateType.PREV) {
                elapse = 0;
            } else {
                elapse = (long) (elapse * speed);
                if ((stateInfo.time + elapse) > stateInfo.length) {
                    elapse = stateInfo.length - stateInfo.time;
                }
            }
            
            stateInfo.elapse = elapse;
            stateInfo.time += elapse;
        }
        
        controller.update(stateInfo);
        index = stateInfo.index;
        
        if (stateInfo.callback != null) {
            stateInfo.callback.afterUpdate();
        }
        
        if (loop) {
            if (stateInfo.time >= stateInfo.length || stateInfo.time < 0) {
                stateInfo.elapse = 0;
                stateInfo.time = 0;
                stateInfo.index = (stateInfo.index + 1) % 1000;
                // todo notify end of animation
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
            index = -1;
            
            if (controller != null) {
                stateInfo.time = 0;
                stateInfo.elapse = 0;
                stateInfo.index = 0;
                stateInfo.length = controller.length();
            }
        }
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
