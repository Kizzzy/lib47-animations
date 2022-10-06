package cn.kizzzy.animations;

public class LinkedKfEvaluator<T> implements KfEvaluator<T> {
    
    public static class Node<T> {
        
        public final KeyFrame<T> kf;
        
        public Node<T> prev;
        
        public Node<T> next;
        
        public Node(KeyFrame<T> kf) {
            this.kf = kf;
        }
    }
    
    private Node<T> curr;
    
    private long elapse;
    
    public LinkedKfEvaluator(KeyFrame<T>[] keyFrames) {
        Node<T>[] nodes = new Node[keyFrames.length];
        for (int i = 0; i < keyFrames.length; ++i) {
            nodes[i] = new Node<>(keyFrames[i]);
        }
        for (int i = 0; i < nodes.length; ++i) {
            Node<T> curr = nodes[i];
            curr.prev = nodes[(i - 1 + nodes.length) % nodes.length];
            curr.next = nodes[(i + 1) % nodes.length];
            
            if (i == 0) {
                this.curr = curr;
            }
        }
    }
    
    @Override
    public Result<T> evaluate(StateInfo stateInfo, AnimatorUpdateType updateType) {
        elapse += stateInfo.elapse;
        float rate = elapse * 1f / (curr.kf.time == 0 ? Integer.MAX_VALUE : curr.kf.time);
        
        if (updateType == AnimatorUpdateType.PREV) {
            curr = curr.prev;
            this.elapse = 0;
            rate = 0;
        } else if (updateType == AnimatorUpdateType.NEXT) {
            curr = curr.next;
            this.elapse = 0;
            rate = 0;
        } else {
            if (elapse > curr.kf.time) {
                curr = curr.next;
                this.elapse = 0;
                rate = 0;
            }
        }
        return new Result<>(curr.kf, curr.next.kf, Math.min(1, Math.max(0, rate)));
    }
}
