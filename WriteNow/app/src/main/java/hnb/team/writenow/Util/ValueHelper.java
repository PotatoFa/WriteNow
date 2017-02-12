package hnb.team.writenow.Util;

import android.content.res.Resources;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class ValueHelper {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
