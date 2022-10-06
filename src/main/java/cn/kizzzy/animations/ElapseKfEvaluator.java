package cn.kizzzy.animations;

public class ElapseKfEvaluator<T> implements KfEvaluator<T> {
    
    private final KeyFrame<T>[] keyFrames;
    
    private long elapse;
    
    public ElapseKfEvaluator(KeyFrame<T>[] keyFrames) {
        this.keyFrames = keyFrames;
    }
    
    public Result<T> evaluate(StateInfo stateInfo, AnimatorUpdateType updateType) {
        KeyFrame<T> prev = null;
        KeyFrame<T> next = null;
        
        for (KeyFrame<T> keyFrame : keyFrames) {
            if (stateInfo.time < keyFrame.time) {
                next = keyFrame;
                break;
            }
            prev = keyFrame;
        }
        
        if (next == null) {
            next = prev;
        }
        
        float rate = (stateInfo.time - prev.time) * 1f / (next.time - prev.time);
        return new Result<>(prev, next, Math.min(1, Math.max(0, rate)));
    }
}
