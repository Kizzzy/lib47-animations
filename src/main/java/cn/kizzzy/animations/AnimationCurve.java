package cn.kizzzy.animations;

@SuppressWarnings("unchecked")
public class AnimationCurve<T> {
    
    private final KeyFrame<T>[] keyFrames;
    
    private final TangentMode<T> tangentMode;
    
    private final long length;
    
    public AnimationCurve(KeyFrame<T>[] keyFrames, TangentMode<T> tangentMode) {
        this.keyFrames = keyFrames;
        this.tangentMode = tangentMode;
        
        length = keyFrames[keyFrames.length - 1].time;
    }
    
    public T evaluate(StateInfo stateInfo, AnimatorUpdateType updateType) {
        KfEvaluator<T> evaluator = (KfEvaluator<T>) stateInfo.evaluatorKvs.computeIfAbsent(this, k -> {
            return new LinkedKfEvaluator<>(keyFrames);
        });
        KfEvaluator.Result<T> result = evaluator.evaluate(stateInfo, updateType);
        return tangentMode.lerp(result.curr.value, result.next.value, result.rate);
    }
    
    public long getLength() {
        return length;
    }
}
