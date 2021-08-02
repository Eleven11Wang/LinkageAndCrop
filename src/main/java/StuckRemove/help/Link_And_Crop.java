package MyPackage.help;

/**
 * @author kkwang
 * @create 2021-07-25 0:01
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Link_And_Crop {
    private static int LINKLENGTH =2;
    private static int MAXDURATION = 10;
    static String filePath = "D:\\BaiduNetdiskDownload\\20201104.3\\1 stream 647 20ms  correct crop_Localization.txt";

    private String width;
    private String height;
    private static int planes;
    private static int points;
    private static int cnt;
    private static int newtacingCnt = 0;
    private static int nextIdx = 0;
    private static LinkedHashMap<String, ArrayList<String>> planeDict = new LinkedHashMap<>();
    private static HashMap<String, Double> parmDict = new HashMap<String, Double>() {
        {
            put("trajectoryStopTime", 500.0);
            put("trajectoryDistance", 40.0);
            //put("trajectoryDistance", );

        }
    };
    private static ArrayList<String> trackingList = new ArrayList();
    private static HashMap<Integer, ArrayList<String>> traceDict = new HashMap<Integer, ArrayList<String>>();
    private  static HashMap<String, Integer> localizationIntensity = new HashMap<String, Integer>();

    public Link_And_Crop(String path) throws IOException {
        filePath = path;
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        str = bufferedReader.readLine();
        while(str.length()==0){str=bufferedReader.readLine();}
        str = bufferedReader.readLine();
        while(str.length()==0){str=bufferedReader.readLine();}
        String[] basicInfo = str.split("\t");

        System.out.println(str.length());

        width = basicInfo[0];
        height = basicInfo[1];
        this.width = width;
        this.height = height;
        str = bufferedReader.readLine();
        while(str.length()==0){str=bufferedReader.readLine();}
        parmDict.put("trajectoryDistance",Double.valueOf(MyParm.inGroupDistance));

        while ((str = bufferedReader.readLine()) != null) {
            while( str != null && str.length()==0){ str=bufferedReader.readLine();}
            if(str==null)break;
            cnt += 1;
            System.out.println(str);
            String[] dataString = str.split("\t");

            String tabel_x = dataString[10];
            String tabel_y = dataString[11];
            String plane = dataString[0];
            Double widx = localizationTable2RealWorld(tabel_x);
            Double widy = localizationTable2RealWorld(tabel_y);
            //int plane = Integer.valueOf(dataString[0]);
            addToPlaneDict(plane, widx + "_" + widy);

//        System.out.println(widx+","+widy);
            //if(cnt > 100){break;}
        }
        //printPlaneDict();
        //close
        inputStream.close();
        bufferedReader.close();
        trajectoryTracking(parmDict);
        //System.out.println(traceDict);
        System.out.println(traceDict.size());
        linkageAndCrop();
        renderImage();
        System.out.println(localizationIntensity);
    }
    public static void main(String[] args) throws IOException {
//        File file = new File(filePath);
//        System.out.println(file.canRead());
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        str = bufferedReader.readLine();
        str = bufferedReader.readLine();
        String[] basicInfo = str.split("\t");
        str = bufferedReader.readLine();


        while ((str = bufferedReader.readLine()) != null) {
            cnt += 1;
            String[] dataString = str.split("\t");
            String tabel_x = dataString[10];
            String tabel_y = dataString[11];
            String plane = dataString[0];
            Double widx = localizationTable2RealWorld(tabel_x);
            Double widy = localizationTable2RealWorld(tabel_y);
            //int plane = Integer.valueOf(dataString[0]);
            addToPlaneDict(plane, widx + "_" + widy);

//        System.out.println(widx+","+widy);
            //if(cnt > 100){break;}
        }
        //printPlaneDict();
        //close
        inputStream.close();
        bufferedReader.close();
        trajectoryTracking(parmDict);
        //System.out.println(traceDict);
        System.out.println(traceDict.size());
        linkageAndCrop();
        renderImage();
        System.out.println(localizationIntensity);
    }
    public HashMap<String,Integer> getLocalizationIntensity(){
        HashMap<String,Integer> cp = localizationIntensity;
        return cp;
    }
    public String getWidthAndHeight(){
        return this.width+"_"+this.height;
    }
    private static void renderImage() {

        for (Integer trajectoryIndex : traceDict.keySet()) {
            ArrayList<String> trajectory = traceDict.get(trajectoryIndex);
            for (String str : trajectory){
                String[] ss = str.split("_");
                Integer pixelX = realWorld2Pixel(ss[1]);
                Integer pixelY = realWorld2Pixel(ss[2]);
                String localizationKey = pixelX+"_"+pixelY;
                if(localizationIntensity.containsKey(localizationKey)){
                    localizationIntensity.put(localizationKey,localizationIntensity.get(localizationKey)+1);
                }else{localizationIntensity.put(localizationKey,1);}
            }
        }
    }
    private static Integer realWorld2Pixel(String realworldLocN){
        Double dloc =Double.valueOf(realworldLocN);
        Double knownPixels = Double.valueOf(MyParm.knownPixels);
        Double knownDistance = Double.valueOf(MyParm.knownDistance);
        Double pixel= dloc*8.0/93;

        return (int) Math.round(pixel);
    }
    private static void addToPlaneDict(String key, String value) {

        if (!planeDict.containsKey(key)) {
            ArrayList<String> newPosList = new ArrayList<>();
            newPosList.add(value);
            planeDict.put(key, newPosList);
        } else {
            ArrayList<String> posList = planeDict.get(key);
            posList.add(value);

        }

    }

    private static double localizationTable2RealWorld(String tabelS) {
        double value = Double.valueOf(tabelS);
        return 93.0 * value;
    }

    private static void printPlaneDict() {

        System.out.println(planeDict);
        System.out.println("print planeDict");
        System.out.println("*******************************************************************************************");
    }

    private static void trajectoryTracking(HashMap<String, Double> parmDict) {
        for (String plane : planeDict.keySet()) {
            ArrayList<String> posInfoList = planeDict.get(plane);
//            System.out.println("plane:"+plane);
            for (String realworldLoc : posInfoList) {
                String[] realworldLocXY = realworldLoc.split("_");
                String realLocX = realworldLocXY[0];
                String realLocY = realworldLocXY[1];
                if (trackingList.isEmpty()) {
                    addNewTrajectory2TraceDict(plane + "_" + realworldLoc);
                    continue;
                }
                trajectoryTrackingHelp(realLocX, realLocY, plane);
//                System.out.println(trackingList.size());
                uploadTracingList();
            }
        }

    }

    private static void trajectoryTrackingHelp(String thisLocX, String thisLocY, String plane) {
        boolean flag = false;
        int trackingIdx = trackingList.size() - 1;
        while (trackingIdx > -1) {
            String trackingInfo = trackingList.get(trackingIdx);
            String[] trackingInfoList = trackingInfo.split("_");
            Integer lastTrajectoryIdx = Integer.valueOf(trackingInfoList[0]);
            String lastPlane = trackingInfoList[1];
            String lastLocX = trackingInfoList[2];
            String lastLocY = trackingInfoList[3];
            String numOfPos = trackingInfoList[4];
            Integer tracingCnt = Integer.valueOf(trackingInfoList[5]);

            double locOldX = Double.valueOf(lastLocX);
            double locOldY = Double.valueOf(lastLocY);
            double locNewX = Double.valueOf(thisLocX);
            double locNewY = Double.valueOf(thisLocY);

            if (isSameSource(locOldX, locOldY, locNewX, locNewY)) {
                String newAveragePos = upDataMeanPos(locOldX, locOldY, locNewX, locNewY, numOfPos);
                if (Double.valueOf(plane) - Double.valueOf(lastPlane) > parmDict.get("trajectoryStopTime")) {
                    Integer newtracingCnt = tracingCnt + 1;
                    trackingIdx -= 1;
                    continue;
                }
                Integer newtracingCnt = tracingCnt;


                trackingList.set(trackingIdx, lastTrajectoryIdx + "_" + plane + "_" + newAveragePos + "_" + (Double.valueOf(numOfPos) + 1) + "_" + newtacingCnt);
                flag = true;
                ArrayList<String> trajectoryN = traceDict.get(lastTrajectoryIdx);
                trajectoryN.add(plane + "_" + thisLocX + "_" + thisLocY);
                traceDict.put(lastTrajectoryIdx, trajectoryN);
                break;
            }
            trackingIdx -= 1;

        }
        if (!flag) addNewTrajectory2TraceDict(plane + "_" + thisLocX + "_" + thisLocY);
    }

    private static void uploadTracingList() {
        ArrayList<String> newTracingList = new ArrayList<>();
        for (String str : trackingList) {
            String[] ss = str.split("_");
            if (Double.valueOf(ss[5]) < 1) {
                newTracingList.add(str);
            }
        }
        trackingList = newTracingList;
//        System.out.println(newTracingList);
    }

    private static boolean isSameSource(double locOldX, double locOldY, double locNewX, double locNewY) {

        double distance = ((locOldX - locNewX) * (locOldX - locNewX)) + ((locOldY - locNewY) * (locOldY - locNewY));
        double trajectoryDistance = parmDict.get("trajectoryDistance");
        if (distance < trajectoryDistance * trajectoryDistance) {
            return true;
        }
        return false;
    }

    private static void addNewTrajectory2TraceDict(String info) {
        ArrayList<String> newTrajectory = new ArrayList<>();
        nextIdx += 1;
        newTrajectory.add(info);
        traceDict.put(nextIdx, newTrajectory);
        // add to traceDict
        String trackingInfo = nextIdx + "_" + info + "_" + 0 + "_" + 0;
        trackingList.add(trackingInfo);
        // add to track
    }

    private static String upDataMeanPos(double locOldX, double locOldY, double locNewX, double locNewY, String numOfPos) {
        double cnt = Double.valueOf(numOfPos);
        double newPosX = (locOldX * cnt + locNewX) / (cnt + 1.0);
        double newPosY = (locOldY * cnt + locNewY) / (cnt + 1.0);
        return newPosX + "_" + newPosY;
    }

    private static void linkageAndCrop() {
        for (Integer trajectoryIndex : traceDict.keySet()) {
            ArrayList<String> trajectory = traceDict.get(trajectoryIndex);
            if(trajectory.size()>10){

                ArrayList<Integer> planeLs = new ArrayList<>();
                for(String str: trajectory){
                    String[] ss = str.split("_");
                    //System.out.println(ss[0].trim());
                    planeLs.add(Integer.valueOf(ss[0].trim()));
                }
                ArrayList<Integer> filtedStartLs = helpLinkageAndCrop(planeLs);
                ArrayList<String> newTrajectory = new ArrayList<>();
                for(String str: trajectory){
                    String[] ss = str.split("_");
                    if(filtedStartLs.contains(Integer.valueOf(ss[0].trim()))){newTrajectory.add(str);}
                    //else{System.out.println("happend");}
                }
                traceDict.put(trajectoryIndex,newTrajectory);
            }



        }
    }
    private static ArrayList<Integer> helpLinkageAndCrop(ArrayList<Integer> planeLs){
        planeLs.sort(Comparator.naturalOrder());
        ArrayList<Integer> dlPlaneLs = new ArrayList<Integer>();
        ArrayList<Integer> cntLs = new ArrayList<Integer>();
        ArrayList<Integer> startLs = new ArrayList<Integer>();
        ArrayList<Integer> endLs = new ArrayList<Integer>();
        int cnt = 0;
        int st = -1;
        int dlx =-1;
        for(int i=1;i<planeLs.size();i++){
            dlPlaneLs.add(planeLs.get(i) - planeLs.get(i - 1));
        }
        dlPlaneLs.add(-1);
        for(int i=0;i<dlPlaneLs.size();i++){
            dlx = dlPlaneLs.get(i);
            if (cnt==0){
                st = planeLs.get(i);
                if(dlx ==1){cnt+=1;}else{startLs.add(st);cntLs.add(1);}
            }else {
                cnt+=1;
                if(dlx!=1){startLs.add(st);cntLs.add(cnt);cnt=0;}
            }
        }

        for(int i=0;i<startLs.size();i++){
            endLs.add(startLs.get(i)+cntLs.get(i)-1);
        }
        Integer stp = startLs.get(0);
        Integer endp = endLs.get(0);
        Integer nextStp;
        ArrayList<Integer> newStartLs =new ArrayList<>();
        ArrayList<Integer> newCntLs = new ArrayList<>();
        for(int i=1;i<startLs.size();i++){
            nextStp = startLs.get(i);
            if(nextStp-endp < LINKLENGTH){endp= endLs.get(i);}
            else{newStartLs.add(stp);newCntLs.add(endp-stp+1);
                stp=startLs.get(i);endp=endLs.get(i);}
        }
        newStartLs.add(stp);
        newCntLs.add(endp-stp+1);
        ArrayList<Integer> filtedStartLs = new ArrayList<>();
        for(int i=0;i<newStartLs.size();i++){
            int duration = newCntLs.get(i);
            if(duration < MAXDURATION){
                for(int c=0;c<duration;c++){
                    filtedStartLs.add(newStartLs.get(i)+c);}}
        }

        return filtedStartLs;
    }
}
