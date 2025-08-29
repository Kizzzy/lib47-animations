package cn.kizzzy.animations;

public class CurveBinding<T> {
    
    private final IProcessor<T> processor;
    
    private final AnimationCurve<T> curve;
    
    private final float start_time;
    
    public CurveBinding(AnimationCurve<T> curve, IProcessor<T> processor, float start_time) {
        this.curve = curve;
        this.processor = processor;
        this.start_time = start_time;
    }
    
    public void Update(StateInfo stateInfo) {
        if (stateInfo.time < start_time) {
            return;
        }
        
        processor.process(stateInfo, curve.evaluate(stateInfo));
    }
    
    public AnimationCurve<T> getCurve() {
        return curve;
    }
}
