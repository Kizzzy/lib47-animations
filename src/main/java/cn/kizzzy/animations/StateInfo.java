package cn.kizzzy.animations;

import java.util.HashMap;
import java.util.Map;

public class StateInfo {
    
    public long time;
    
    public long elapse;
    
    public long length;
    
    public long enterTime;
    
    public boolean loop;
    
    public AnimatorUpdateType updateType;
    
    public AnimatorCallback callback;
    
    public Map<Object, KfEvaluator<?>> evaluatorKvs
        = new HashMap<>();
}
