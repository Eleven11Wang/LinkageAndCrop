package MyPackage; /**
 * @author kkwang
 * @create 2021-07-24 19:10
 */

import MyPackage.help.Image_Render;
import MyPackage.help.Link_And_Crop;
import MyPackage.help.myGUI;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


public class My_First_Plugin implements PlugIn {
        private String path;
        public My_First_Plugin(){}
        public My_First_Plugin(String we){
            this.path = we;
        }
    public void run(String arg) {
//        OpenDialog wo = new OpenDialog("abc",null);
//        String we = wo.getDirectory()+ wo.getFileName();
//        IJ.showMessage(" New localization tabel path",we);
        JFrame jf = new myGUI();
        jf.setVisible(true);



    }

    public void runCropAndLink(String we){
        try {
            Link_And_Crop myobj= new Link_And_Crop(we);
            HashMap<String,Integer> myobjLocalizationIntensity = myobj.getLocalizationIntensity();
            String widthAndHeight = myobj.getWidthAndHeight();
            //IJ.showMessage(String.valueOf(myobjLocalizationIntensity.size()));
            Image_Render ijr = new Image_Render(myobjLocalizationIntensity,widthAndHeight);
            ImagePlus img = ijr.img;
            img.show();
        } catch (IOException e) {
            IJ.showMessage("a","bugHappen");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        My_First_Plugin ix = new My_First_Plugin();
        ix.run("abc");
    }

}


