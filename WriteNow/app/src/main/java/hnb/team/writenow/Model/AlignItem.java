package hnb.team.writenow.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 18..
 */

public class AlignItem {

    private int align;
    private String alignText;

    public AlignItem(int align, String alignText) {
        this.align = align;
        this.alignText = alignText;
    }

    public int getAlign() {
        return align;
    }

    public String getAlignText() {
        return alignText;
    }

    public static List<AlignItem> getAlignItems(){

        AlignItem alignItemLeft = new AlignItem(Gravity.LEFT, "왼쪽");
        AlignItem alignItemCenter = new AlignItem(Gravity.CENTER_HORIZONTAL, "가운데");
        AlignItem alignItemRight = new AlignItem(Gravity.RIGHT, "오른쪽");
        AlignItem alignItemTop = new AlignItem(Gravity.TOP, "위");
        AlignItem alignItemMiddle = new AlignItem(Gravity.CENTER_VERTICAL, "중간");
        AlignItem alignItemBottom = new AlignItem(Gravity.BOTTOM, "아래");

        List<AlignItem> alignItems = new ArrayList<AlignItem>();
        alignItems.add(alignItemTop);
        alignItems.add(alignItemMiddle);
        alignItems.add(alignItemBottom);
        alignItems.add(alignItemLeft);
        alignItems.add(alignItemCenter);
        alignItems.add(alignItemRight);

        return alignItems;
    }
}
