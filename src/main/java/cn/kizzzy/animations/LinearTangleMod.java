package cn.kizzzy.animations;

public abstract class LinearTangleMod<T> implements TangentMode<T> {
    
    protected float lerp(float x, float y, float rate) {
        return x + (y - x) * rate;
    }
}
