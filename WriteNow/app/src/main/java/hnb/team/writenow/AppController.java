package hnb.team.writenow;

import android.app.Application;
import android.graphics.Typeface;

import com.tsengvn.typekit.Typekit;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class AppController extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        initTypeFont();

    }

    private void initTypeFont(){
        Typekit.getInstance()
                .addCustom1(Typeface.createFromAsset(getAssets(), "NanumBarunGothic_Regular.otf"))
                .addCustom2(Typeface.createFromAsset(getAssets(), "NanumBarunGothic_Bold.otf"));
    }
}
