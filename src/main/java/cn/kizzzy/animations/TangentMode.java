package cn.kizzzy.animations;

public interface TangentMode<T> {
    
    T lerp(T x, T y, float rate);
}
