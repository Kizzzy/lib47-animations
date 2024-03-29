package cn.kizzzy.animations;

import java.util.HashMap;
import java.util.Map;

public class StateInfo {
    
    public long time;
    
    public float elapse;
    
    public long length;
    
    public long enterTime;
    
    public boolean loop;
    
    public AnimatorUpdateType updateType;
    
    public Runnable before;
    
    public Runnable after;
    
    public Map<Object, KfEvaluator<?>> evaluatorKvs = new HashMap<>();
}
