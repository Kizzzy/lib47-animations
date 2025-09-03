package cn.kizzzy.animations;

public class AnimationCurve<T> {
    
    private final KfEvaluator<T> evaluator;
    private final TangentMode<T> tangentMode;
    
    public AnimationCurve(KfEvaluator<T> evaluator, TangentMode<T> tangentMode) {
        this.evaluator = evaluator;
        this.tangentMode = tangentMode;
    }
    
    public T evaluate(StateInfo stateInfo) {
        if (stateInfo.time < evaluator.startTime() ||
            stateInfo.time > evaluator.endTime()) {
            return null;
        }
        
        KfEvaluator.Result<T> result = evaluator.evaluate(stateInfo);
        return tangentMode.lerp(result.curr.value, result.next.value, result.rate);
    }
    
    public long length() {
        return evaluator.length();
    }
}
