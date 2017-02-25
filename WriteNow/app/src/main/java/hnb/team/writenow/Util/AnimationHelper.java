package hnb.team.writenow.Util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by jaehoonjung on 2017. 2. 25..
 */

public class AnimationHelper {

    public static void animationToView(View view, int visibility){

        if(view != null && view.getVisibility() == visibility)
            return;

        Animation alphaAnimation;

        if(visibility == View.VISIBLE){
            alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if(view != null)
                        view.setVisibility(View.VISIBLE);
                }
                @Override
                public void onAnimationEnd(Animation animation) {}
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }else{
            alphaAnimation = new AlphaAnimation(1, 0);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    if(view != null)
                        view.setVisibility(View.GONE);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
        }
        alphaAnimation.setDuration(300);

        if(view != null)
            view.startAnimation(alphaAnimation);

    }
}
