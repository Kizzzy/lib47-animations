package cn.kizzzy.animations;

public class AnimationController {
    
    private final AnimationClip clip;
    
    public AnimationController(AnimationClip clip) {
        this.clip = clip;
    }
    
    public long getLength() {
        if (clip != null) {
            return clip.getLength();
        }
        return 0;
    }
    
    public void update(StateInfo stateInfo) {
        clip.update(stateInfo);
    }
}
