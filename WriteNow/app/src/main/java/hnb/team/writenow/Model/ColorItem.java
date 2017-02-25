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

        brightColorList.add(Color.parseColor("#000000"));
        brightColorList.add(Color.parseColor("#00AAA0"));
        brightColorList.add(Color.parseColor("#FF4C3B"));
        brightColorList.add(Color.parseColor("#0072BB"));
        brightColorList.add(Color.parseColor("#FF4500"));
        brightColorList.add(Color.parseColor("#462066"));
        brightColorList.add(Color.parseColor("#32B92D"));
        brightColorList.add(Color.parseColor("#FFD034"));

        return brightColorList;
    }

    public static List<Integer> getDarknessColorList(){

        List<Integer> darknessColorList = new ArrayList<Integer>();

        darknessColorList.add(Color.parseColor("#FFFFFF"));
        darknessColorList.add(Color.parseColor("#FF4500"));
        darknessColorList.add(Color.parseColor("#FFD034"));
        darknessColorList.add(Color.parseColor("#00AAA0"));
        darknessColorList.add(Color.parseColor("#FF4C3B"));
        darknessColorList.add(Color.parseColor("#C6C8CA"));
        darknessColorList.add(Color.parseColor("#0072BB"));

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