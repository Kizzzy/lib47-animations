package cn.kizzzy.animations;

public class AnimationController {
    
    private final AnimationClip clip;
    
    public AnimationController(AnimationClip clip) {
        this.clip = clip;
    }
    
    public void update(StateInfo stateInfo) {
        clip.update(stateInfo);
    }
    
    public long length() {
        return clip.length();
    }
}
