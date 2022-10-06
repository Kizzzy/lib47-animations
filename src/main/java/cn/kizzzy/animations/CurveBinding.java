package cn.kizzzy.animations;

public class CurveBinding<T> {
    
    private final IProcessor<T> processor;
    
    private final AnimationCurve<T> curve;
    
    public CurveBinding(AnimationCurve<T> curve, IProcessor<T> processor) {
        this.curve = curve;
        this.processor = processor;
    }
    
    public void Update(StateInfo stateInfo, AnimatorUpdateType updateType) {
        processor.process(stateInfo, curve.evaluate(stateInfo, updateType));
    }
    
    public AnimationCurve<T> getCurve() {
        return curve;
    }
}
