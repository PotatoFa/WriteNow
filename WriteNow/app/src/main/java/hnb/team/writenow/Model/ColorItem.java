package hnb.team.writenow.Model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaehoonjung on 2017. 2. 25..
 */

public class ColorItem {

    public static List<Integer> getBrightColorList(){

        List<Integer> brightColorList = new ArrayList<Integer>();

        brightColorList.add(Color.parseColor("#353533"));
        brightColorList.add(Color.parseColor("#003302"));
        brightColorList.add(Color.parseColor("#d30211"));
        brightColorList.add(Color.parseColor("#359911"));
        brightColorList.add(Color.parseColor("#3dc03d"));
        brightColorList.add(Color.parseColor("#5930f1"));

        return brightColorList;
    }

    public static List<Integer> getDarknessColorList(){

        List<Integer> darknessColorList = new ArrayList<Integer>();

        darknessColorList.add(Color.parseColor("#359911"));
        darknessColorList.add(Color.parseColor("#3dc03d"));
        darknessColorList.add(Color.parseColor("#5930f1"));
        darknessColorList.add(Color.parseColor("#353533"));
        darknessColorList.add(Color.parseColor("#003302"));
        darknessColorList.add(Color.parseColor("#d30211"));

        return darknessColorList;
    }

    public static List<Integer> getImageFilterColorList(){

        List<Integer> imageFilterColorList = new ArrayList<Integer>();

        imageFilterColorList.add(Color.parseColor("#99353533"));
        imageFilterColorList.add(Color.parseColor("#99003302"));
        imageFilterColorList.add(Color.parseColor("#99d30211"));
        imageFilterColorList.add(Color.parseColor("#99359911"));
        imageFilterColorList.add(Color.parseColor("#993dc03d"));
        imageFilterColorList.add(Color.parseColor("#995930f1"));

        return imageFilterColorList;
    }

    public static List<Integer> getTextBoxColorList(){

        List<Integer> imageFilterColorList = new ArrayList<Integer>();

        imageFilterColorList.add(Color.parseColor("#00FFFFFF"));
        imageFilterColorList.add(Color.parseColor("#FFFFFFFF"));
        imageFilterColorList.add(Color.parseColor("#BBFFFFFF"));
        imageFilterColorList.add(Color.parseColor("#88FFFFFF"));
        imageFilterColorList.add(Color.parseColor("#44FFFFFF"));

        imageFilterColorList.add(Color.parseColor("#44000000"));
        imageFilterColorList.add(Color.parseColor("#88000000"));
        imageFilterColorList.add(Color.parseColor("#BB000000"));
        imageFilterColorList.add(Color.parseColor("#FF000000"));

        return imageFilterColorList;
    }

}
