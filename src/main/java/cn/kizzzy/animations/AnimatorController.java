package cn.kizzzy.animations;

public class AnimatorController {
    private AnimationClip clip;
    
    public AnimatorController(AnimationClip clip) {
        this.clip = clip;
    }
    
    public float getLength() {
        if (clip != null) {
            return clip.getLength();
        }
        return 0;
    }
    
    public void update(StateInfo stateInfo) {
        clip.update(stateInfo);
    }
}
