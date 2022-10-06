package cn.kizzzy.animations;

public class ConstTangentMode<T> implements TangentMode<T> {
    
    @Override
    public T lerp(T x, T y, float rate) {
        return x;
    }
}
