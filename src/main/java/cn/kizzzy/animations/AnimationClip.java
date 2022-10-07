package cn.kizzzy.animations;

public class AnimationClip {
    
    private final CurveBinding<?>[] bindings;
    
    public AnimationClip(CurveBinding<?>[] bindings) {
        this.bindings = bindings;
    }
    
    public long getLength() {
        for (CurveBinding<?> binding : bindings) {
            return binding.getCurve().getLength();
        }
        return 0;
    }
    
    public void update(StateInfo stateInfo) {
        for (CurveBinding<?> binding : bindings) {
            binding.Update(stateInfo);
        }
    }
}
