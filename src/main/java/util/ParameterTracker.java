package util;

import java.util.HashMap;
/**
 * @author kkwang
 * @create 2021-07-19 18:25
 */
public class ParameterTracker {


    String prefsPrefix;
    HashMap<String,Double>trackedParameters = new HashMap<String, Double>();

    public ParameterTracker(String prefsPrefix){
        this.prefsPrefix = prefsPrefix;
    }

    public void addParms(String keyString, Double defaultValue){
        this.trackedParameters.put(keyString,defaultValue);

    }
//    public void setParam(String keyString, Object num){
//        String strNum = num.toString();
//       this.trackedParameters.put(keyString, Double.valueOf(strNum));
//    }
    public void show(){
        System.out.println("show method");
    }
    public HashMap getTrackedParameters() {
        return (HashMap) this.trackedParameters;
    }



}
