package cn.kizzzy.animations;

public class AnimationCurve<T> {
    
    private final TangentMode<T> tangentMode;
    private final KfEvaluator<T> evaluator;
    
    public AnimationCurve(KeyFrame<T>[] kfs, TangentMode<T> tangentMode, boolean linked) {
        this.tangentMode = tangentMode;
        this.evaluator = linked ?
            new LinkedKfEvaluator<>(kfs) :
            new ElapseKfEvaluator<>(kfs);
    }
    
    public T evaluate(StateInfo stateInfo) {
        KfEvaluator.Result<T> result = evaluator.evaluate(stateInfo);
        return tangentMode.lerp(result.curr.value, result.next.value, result.rate);
    }
    
    public long length() {
        return evaluator.length();
    }
}
