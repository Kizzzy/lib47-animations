package cn.kizzzy.animations;

@SuppressWarnings("unchecked")
public class LinkedKfEvaluator<T> implements KfEvaluator<T> {
    
    public static class Node<T> {
        
        public final KeyFrame<T> kf;
        
        public final boolean last;
        
        public final boolean first;
        
        public Node<T> prev;
        
        public Node<T> next;
        
        public Node(KeyFrame<T> kf, boolean first, boolean last) {
            this.kf = kf;
            this.first = first;
            this.last = last;
        }
    }
    
    private final long startTime;
    
    private Node<T> curr;
    private Node<T> first;
    private Node<T> last;
    
    private long length;
    private long elapse;
    private long index = -1;
    
    public LinkedKfEvaluator(KeyFrame<T>[] keyFrames, long startTime) {
        this.startTime = startTime;
        
        Node<T>[] nodes = new Node[keyFrames.length];
        for (int i = 0; i < keyFrames.length; ++i) {
            nodes[i] = new Node<>(keyFrames[i], i == 0, i == keyFrames.length - 1);
            length += keyFrames[i].time;
        }
        
        for (int i = 0; i < nodes.length; ++i) {
            Node<T> curr = nodes[i];
            curr.prev = nodes[(i - 1 + nodes.length) % nodes.length];
            curr.next = nodes[(i + 1) % nodes.length];
            
            if (curr.first) {
                this.curr = curr;
                first = curr;
            }
            
            if (curr.last) {
                last = curr;
            }
        }
    }
    
    @Override
    public Result<T> evaluate(StateInfo stateInfo) {
        if (stateInfo.index != index) {
            index = stateInfo.index;
            
            curr = first;
            elapse = 0;
            
            return new Result<>(curr.kf, curr.next.kf, 0);
        }
        
        switch (stateInfo.updateType) {
            case PREV:
                if (/*stateInfo.loop ||*/ !curr.first) {
                    curr = curr.prev;
                    elapse = 0;
                }
                return new Result<>(curr.kf, curr.next.kf, 0);
            case NEXT:
                if (/*stateInfo.loop ||*/ !curr.last) {
                    curr = curr.next;
                    elapse = 0;
                }
                return new Result<>(curr.kf, curr.next.kf, 0);
            case TIME:
                elapse += stateInfo.elapse;
                if ((/*stateInfo.loop || */!curr.last) && elapse > curr.kf.time) {
                    elapse -= curr.kf.time;
                    curr = curr.next;
                }
                
                float rate = elapse * 1f / (curr.kf.time == 0 ? Integer.MAX_VALUE : curr.kf.time);
                rate = Math.min(1, Math.max(0, rate));
                
                return new Result<>(curr.kf, curr.next.kf, rate);
            default:
                return new Result<>(curr.kf, curr.next.kf, 0);
        }
    }
    
    @Override
    public long length() {
        return length;
    }
    
    @Override
    public long startTime() {
        return startTime;
    }
    
    @Override
    public long endTime() {
        return startTime + length();
    }
}
