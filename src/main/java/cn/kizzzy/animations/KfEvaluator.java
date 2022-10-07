package cn.kizzzy.animations;

public interface KfEvaluator<T> {
    
    class Result<T> {
        
        public final KeyFrame<T> curr;
        
        public final KeyFrame<T> next;
        
        public final float rate;
        
        public Result(KeyFrame<T> curr, KeyFrame<T> next, float rate) {
            this.curr = curr;
            this.next = next;
            this.rate = rate;
        }
    }
    
    Result<T> evaluate(StateInfo stateInfo);
}
