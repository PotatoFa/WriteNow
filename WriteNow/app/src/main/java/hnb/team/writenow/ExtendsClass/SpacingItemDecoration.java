package hnb.team.writenow.ExtendsClass;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */
public class SpacingItemDecoration extends RecyclerView.ItemDecoration{

    int marginPixel = 0;
    int marginEdge = 0;

    public SpacingItemDecoration(int marginPixel){
        this.marginPixel = marginPixel;
    }

    public SpacingItemDecoration(int marginPixel, int marginEdge){
        this.marginPixel = marginPixel;
        this.marginEdge = marginEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);

        outRect.right = marginPixel;
        outRect.left = marginPixel;

        if(position == 0){
            outRect.left = marginEdge;
        }else if(position == parent.getAdapter().getItemCount() - 1){
            outRect.right = marginEdge;
        }

    }
}
