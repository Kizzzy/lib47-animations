package cn.kizzzy.animations;

public class CurveBinding {
    private IProcessor processor;
    
    private AnimationCurve curve;
    
    public CurveBinding(AnimationCurve curve, IProcessor processor) {
        this.curve = curve;
        this.processor = processor;
    }
    
    public void Update(StateInfo stateInfo) {
        processor.process(stateInfo.target, curve.evaluate(stateInfo.time));
    }
    
    public AnimationCurve getCurve() {
        return curve;
    }
    
    public void setCurve(AnimationCurve curve) {
        this.curve = curve;
    }
}
