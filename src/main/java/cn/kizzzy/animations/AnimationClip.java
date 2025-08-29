package cn.kizzzy.animations;

public class AnimationClip {
    
    private final CurveBinding<?>[] bindings;
    
    public AnimationClip(CurveBinding<?>[] bindings) {
        this.bindings = bindings;
    }
    
    public void update(StateInfo stateInfo) {
        for (CurveBinding<?> binding : bindings) {
            binding.Update(stateInfo);
        }
    }
    
    public long length() {
        long length = Long.MIN_VALUE;
        for (CurveBinding<?> binding : bindings) {
            if (binding.getCurve().length() > length) {
                length = binding.getCurve().length();
            }
        }
        return length;
    }
}
