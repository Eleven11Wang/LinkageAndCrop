package MyPackage.help;

import UI.DialogStub;
import ij.IJ;
import ij.ImageJ;
import ij.io.OpenDialog;
import ij.plugin.PlugIn;
import util.GridBagHelper;
import util.ParameterTracker;

import javax.swing.*;
import java.awt.*;

/**
 * @author kkwang
 * @create 2021-07-25 18:51
 */


public class Parameter_SetUp implements PlugIn {

    public static final String KNOWN_DISTANCE = "knownDistance";
    public static final String KNOWN_PIXELS = "knownPixels";


    private static ParameterTracker tracker = new ParameterTracker("Camers arms");
    private String[] args;


    @Override
    public void run(String s) {
        tracker.addParms("knownDistance", 93.0);
        tracker.addParms("knownPixels", 8.0);
        tracker.addParms("linkDistance",40.0);
        tracker.addParms("linkDuration",500.0);

        DialogStub dialog2 = new DialogStub(tracker, IJ.getInstance(),"Camera setup"){
            @Override
            protected void layoutComponents() {
                setLayout(new GridBagLayout());
                add(new JLabel("knownDistance:"), GridBagHelper.leftCol());
                JTextField knownDistanceField = new JTextField(20);
                add(knownDistanceField, GridBagHelper.rightCol());

                add(new JLabel("knownDistance2:"), GridBagHelper.leftCol());
                JTextField knownDistanceField2 = new JTextField(20);
                add(knownDistanceField2, GridBagHelper.rightCol());

                //tracker.setParam("knownDistance",knownDistanceField);

            }



        };
        dialog2.showAndGetResults();

    }

    public static void main(String[] args){
        ImageJ ij = new ImageJ();
        OpenDialog wo = new OpenDialog("abc",null);
        String we = wo.getDirectory()+wo.getFileName();
        System.out.println(we);
        Parameter_SetUp myobj = new Parameter_SetUp();
        myobj.run("abc");
    }
}
