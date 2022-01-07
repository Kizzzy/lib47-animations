package cn.kizzzy.animations;

public class Animator {
    
    private AnimatorController controller;
    
    private float speed;
    
    private StateInfo StateInfo =
        new StateInfo();
    
    public void update() {
        if (speed == 0 || controller == null) {
            return;
        }
        
        //StateInfo.time += Time.fixedDeltaTime * speed;
        
        controller.update(StateInfo);
        
        if (StateInfo.time >= StateInfo.length) {
            StateInfo.time = 0;
            // todo
        }
    }
    
    public void setController(AnimatorController controller, boolean reset) {
        reset = reset || this.controller == null && controller != null;
        
        this.controller = controller;
        
        if (reset) {
            //StateInfo.enterTime = Time.time;
            if (controller != null) {
                // todo
                //StateInfo.target = gameObject;
                StateInfo.length = controller.getLength();
                StateInfo.time = 0;
            }
        }
    }
    
    public AnimatorController getController() {
        return controller;
    }
    
    public void setController(AnimatorController controller) {
        this.controller = controller;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public cn.kizzzy.animations.StateInfo getStateInfo() {
        return StateInfo;
    }
    
    public void setStateInfo(cn.kizzzy.animations.StateInfo stateInfo) {
        StateInfo = stateInfo;
    }
}
