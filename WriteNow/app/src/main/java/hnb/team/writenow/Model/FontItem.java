package hnb.team.writenow.Model;

import android.content.Context;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaehoonjung on 2017. 2. 18..
 */

public class FontItem {

    private Typeface font;

    public FontItem(Typeface font) {
        this.font = font;
    }

    public Typeface getFont() {
        return font;
    }

    public static List<FontItem> getFontItems(Context context){

        List<FontItem> fontItems = new ArrayList<FontItem>();

        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Light.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Regular.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Bold.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NotoSansKR-Medium.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "Flexo_Light.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "Flexo_Medium.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "Flexo_Bold.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic_Regular.otf")));
        fontItems.add(new FontItem(Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic_Bold.otf")));

        return fontItems;
    }
}
