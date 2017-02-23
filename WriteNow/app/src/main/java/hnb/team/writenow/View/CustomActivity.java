package hnb.team.writenow.View;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import hnb.team.writenow.Adapter.SquareImageAdapter;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Interface.FileSaveListener;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.AsyncSaveToImage;
import hnb.team.writenow.Util.ValueHelper;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class CustomActivity extends BaseActivity implements FileSaveListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        initBottomSheet();

        initCardLayout();

        initImageList();

    }

    private void initCardLayout(){
        customContentsLayout.setDrawingCacheEnabled(true);
        customContentsLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initBottomSheet(){

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_DRAGGING)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void initImageList(){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        squareImageAdapter = new SquareImageAdapter(getApplicationContext());

        imageRecyclerView.setLayoutManager(gridLayoutManager);
        imageRecyclerView.setAdapter(squareImageAdapter);
        squareImageAdapter.setAdapterOnClickListener((position, data) -> {
            PixaBayImage pixaBayImage = (PixaBayImage) data;
            Log.i("onCLICK", String.valueOf(pixaBayImage.getTestImageUrl()));
        });
    }

    BottomSheetBehavior bottomSheetBehavior;

    private SquareImageAdapter squareImageAdapter;

    @Bind(R.id.customContentsLayout) PercentRelativeLayout customContentsLayout;
    @Bind(R.id.textBox) RelativeLayout textBox;
    @Bind(R.id.editTitleText) EditText editTitleText;
    @Bind(R.id.editDescText) EditText editDescText;
    @Bind(R.id.previewImage) ImageView previewImage;

    @Bind(R.id.coverView) View coverView;

    @Bind(R.id.bottomSheetLayout) LinearLayout bottomSheetLayout;

    @Bind(R.id.bottomOverMenuLayout) LinearLayout bottomOverMenuLayout;

    @Bind(R.id.changeTempleteLayout) LinearLayout changeTempleteLayout;
    @Bind(R.id.changeTextLayout) LinearLayout changeTextLayout;
    @Bind(R.id.changeColorLayout) LinearLayout changeColorLayout;

    @Bind(R.id.imageRecyclerView) RecyclerView imageRecyclerView;

    @Bind(R.id.bottomColorSettingLayout) LinearLayout bottomColorSettingLayout;
    @Bind(R.id.bottomTextSettingLayout) LinearLayout bottomTextSettingLayout;

    @OnClick(R.id.changeImageButton)
    public void changeImageButton(){
        squareImageAdapter.setSource(getTextImageData());
        imageRecyclerView.setVisibility(View.VISIBLE);
        bottomColorSettingLayout.setVisibility(View.GONE);
        bottomTextSettingLayout.setVisibility(View.GONE);
        bottomSheetAnimate(bottomVisibleHeight);
    }

    @OnClick(R.id.completeButton)
    public void completeButton(){

        customContentsLayout.buildDrawingCache();

        AsyncSaveToImage.startSaveToImage(customContentsLayout.getDrawingCache(), this, this);

    }

    @OnClick(R.id.changeTempleteLayout)
    public void changeTempleteLayout(){

    }

    @OnClick(R.id.changeTextLayout)
    public void changeTextLayout(){
        bottomTextSettingLayout.setVisibility(View.VISIBLE);
        bottomColorSettingLayout.setVisibility(View.GONE);
        imageRecyclerView.setVisibility(View.GONE);
        bottomSheetAnimate(bottomVisibleHeight);
    }

    @OnClick(R.id.changeColorLayout)
    public void changeColorLayout(){
        bottomColorSettingLayout.setVisibility(View.VISIBLE);
        bottomTextSettingLayout.setVisibility(View.GONE);
        imageRecyclerView.setVisibility(View.GONE);
        bottomSheetAnimate(bottomVisibleHeight);
    }

    @Override
    public void onCompleteFileSave(String filePath) {
        Log.i("onComplete", "File Save : " + filePath);
    }

    int bottomInvisibleHeight = ValueHelper.dpToPx(40);
    int bottomVisibleHeight = ValueHelper.dpToPx(240);

    public void bottomSheetAnimate(int targetHeight){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(bottomSheetBehavior.getPeekHeight(), targetHeight);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new OvershootInterpolator(0.5f));
        valueAnimator.addUpdateListener(animation -> {
            int animateHeight = (int) animation.getAnimatedValue();
            if(bottomSheetBehavior != null)
                bottomSheetBehavior.setPeekHeight(animateHeight);
        });

        valueAnimator.start();

    }


    @Override
    public void onBackPressed() {
        if(bottomSheetBehavior.getPeekHeight() != bottomInvisibleHeight){
            bottomSheetAnimate(bottomInvisibleHeight);
            return;
        }
        super.onBackPressed();
    }

    private List<PixaBayImage> getTextImageData(){

        List<PixaBayImage> pixaBayImages = new ArrayList<PixaBayImage>();

        List<Integer> imageList = new ArrayList<Integer>();

        imageList.add(R.drawable.pic_4);
        imageList.add(R.drawable.pic_5);
        imageList.add(R.drawable.pic_6);
        imageList.add(R.drawable.pic_7);
        imageList.add(R.drawable.pic_8);
        imageList.add(R.drawable.pic_4);
        imageList.add(R.drawable.pic_5);
        imageList.add(R.drawable.pic_6);
        imageList.add(R.drawable.pic_7);
        imageList.add(R.drawable.pic_8);

        for(int i = 0; i < 30; i++){
            pixaBayImages.add(new PixaBayImage(0, imageList.get(i % imageList.size())));
        }

        return pixaBayImages;
    }


}
