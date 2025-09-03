package cn.kizzzy.animations;

public class AnimationClip {
    
    private final AnimationCurveBinding<?>[] bindings;
    
    public AnimationClip(AnimationCurveBinding<?>[] bindings) {
        this.bindings = bindings;
    }
    
    public void update(StateInfo stateInfo) {
        for (AnimationCurveBinding<?> binding : bindings) {
            binding.Update(stateInfo);
        }
    }
    
    public long length() {
        long length = Long.MIN_VALUE;
        for (AnimationCurveBinding<?> binding : bindings) {
            if (binding.length() > length) {
                length = binding.length();
            }
        }
        return length;
    }
}
