package MyPackage.help;

import ij.ImagePlus;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

import java.util.HashMap;

/**
 * @author kkwang
 * @create 2021-07-25 0:20
 */
public class Image_Render {

    private static Integer maxWidth = 0;
    private static Integer maxHeight = 0;

    private static HashMap<String, Integer> intensityDict;
    public static double[] pixels;
    public static ImagePlus img;
    public Image_Render(HashMap<String, Integer> intensityDict, String widthAndHeight) {

        this.intensityDict = intensityDict;
        String[] widthHeight = widthAndHeight.split("_");
        maxWidth=Integer.valueOf(widthHeight[0].trim()) * 8;
        maxHeight = Integer.valueOf(widthHeight[1].trim()) * 8;
        pixels = findPixels();
        System.out.println(pixels.length);
        System.out.println(maxWidth);
        System.out.println(maxHeight);

        ImageProcessor ip = new FloatProcessor(maxWidth, maxHeight, pixels);
        ImagePlus imgx = new ImagePlus("test title",ip);
        this.img = imgx;

    }



    private static double[] findPixels() {
        int pixelLength = (int) (maxWidth ) * (maxHeight );
        double [] pixels = new double[pixelLength];
        for (String pixelLoc : intensityDict.keySet()) {
            String[] pixelXY = pixelLoc.split("_");
            Integer pixelX = Integer.valueOf(pixelXY[0]);
            Integer pixelY = Integer.valueOf(pixelXY[1]);
            Integer idx = pixelY * maxWidth + pixelX;
            pixels[pixelY * maxWidth + pixelX] = (double) intensityDict.get(pixelLoc);
        }
        return pixels;
    }
}