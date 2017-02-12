package hnb.team.writenow.View;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.OnClick;
import hnb.team.writenow.Adapter.SquareImageAdapter;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.Presenter.WritePresenter;
import hnb.team.writenow.Presenter.WritePresenterImpl;
import hnb.team.writenow.R;

public class WriteActivity extends BaseActivity implements WritePresenter.ViewInterface{

    private WritePresenterImpl writePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        setResult(RESULT_OK);

        writePresenter = new WritePresenterImpl(this);

        writePresenter.setViewInterface(this);

        initCardLayout();

        initBottomSheet();

        initImageList();

    }

    @BindColor(R.color.filterColor_white) int currentFilterColor;

    int filterAnimDuration = 300;

    @Bind(R.id.cardLayout) RelativeLayout cardLayout;

    @Bind(R.id.cardImage) ImageView cardImage;

    @Bind(R.id.imageRecyclerView) RecyclerView imageRecyclerView;

    @Bind(R.id.bottomSheetLayout) RelativeLayout bottomSheetLayout;

    BottomSheetBehavior bottomSheetBehavior;

    private SquareImageAdapter squareImageAdapter;

    private void initCardLayout(){
        cardLayout.setDrawingCacheEnabled(true);
        cardLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initBottomSheet(){

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
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
            writePresenter.excuteImageChange(pixaBayImage);
        });
    }

    @OnClick(R.id.changeImageButton)
    public void changeImageButton(){
        writePresenter.excuteImageChange();
    }

    @OnClick(R.id.showBottomSheetButton)
    public void showBottomSheetButton(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        writePresenter.excuteImageList();
    }

    @OnClick(R.id.changeFilterColorButton)
    public void changeFilterColorButton(){
        writePresenter.changeFilterColor();
    }

    @OnClick(R.id.saveToImageButton)
    public void saveToImageButton(){
        showProgressDialog("이미지 저장 중 입니다", true);
        cardLayout.buildDrawingCache();
        writePresenter.excuteSaveCard(cardLayout.getDrawingCache());
    }

    @Override
    public void completeSaveCard(String message) {
        //hideProgressDialog();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setImageList(List<PixaBayImage> pixaBayImages) {
        squareImageAdapter.setSource(pixaBayImages);
    }

    @Override
    public void setImage(int imageResource) {
        Glide.with(this).load(imageResource).override(800,800).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(cardImage);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void setImageFilterColorToResource(int colorResource) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), currentFilterColor, getResources().getColor(colorResource));
        valueAnimator.setDuration(filterAnimDuration);
        valueAnimator.addUpdateListener(animation -> {
            int animationValue = (int) animation.getAnimatedValue();
            cardImage.setColorFilter(new PorterDuffColorFilter(animationValue, PorterDuff.Mode.MULTIPLY));
            currentFilterColor = animationValue;
        });
        valueAnimator.start();
    }


}
