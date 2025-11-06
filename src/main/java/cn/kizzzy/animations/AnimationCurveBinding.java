package cn.kizzzy.animations;

public class AnimationCurveBinding<T> {
    
    private final IProcessor<T> processor;
    
    private final AnimationCurve<T> curve;
    
    public AnimationCurveBinding(AnimationCurve<T> curve, IProcessor<T> processor) {
        this.curve = curve;
        this.processor = processor;
    }
    
    public void update(StateInfo stateInfo) {
        T value = curve.evaluate(stateInfo);
        if (value != null) {
            processor.process(stateInfo, value);
        }
    }
    
    public long length() {
        return curve.length();
    }
}
