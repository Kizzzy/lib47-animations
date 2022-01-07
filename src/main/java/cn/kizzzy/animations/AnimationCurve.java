package cn.kizzzy.animations;

public class AnimationCurve {
    private KeyFrame[] keyFrames;
    private TangentMode tangentMode;
    
    private int length;
    
    public AnimationCurve(KeyFrame[] keyFrames, TangentMode tangentMode) {
        this.keyFrames = keyFrames;
        this.tangentMode = tangentMode;
    }
    
    public Object evaluate(float time) {
        KeyFrame prev = null;
        KeyFrame next = null;
        
        for (KeyFrame keyFrame : keyFrames) {
            if (time < keyFrame.time) {
                next = keyFrame;
            }
            prev = keyFrame;
        }
        
        if (next == null) {
            return prev.value;
        }
        
        float t = (time - prev.time) / (next.time - prev.time);
        return tangentMode.lerp(prev.value, next.value, Math.min(1, Math.max(0, t)));
    }
    
    public int getLength() {
        return length;
    }
}
