package cn.kizzzy.animations;

public interface IProcessor<T> {
    
    void process(StateInfo stateInfo, T value);
}
