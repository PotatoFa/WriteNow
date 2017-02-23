package hnb.team.writenow.Temp;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.os.Bundle;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import hnb.team.writenow.Adapter.AdapterAlignItems;
import hnb.team.writenow.Adapter.AdapterFontItems;
import hnb.team.writenow.EventBus.EventBus;
import hnb.team.writenow.Interface.AlignChangeListener;
import hnb.team.writenow.Interface.FileSaveListener;
import hnb.team.writenow.Interface.FontChangeListener;
import hnb.team.writenow.Adapter.SquareImageAdapter;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Model.AlignItem;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.Model.FontItem;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.ValueHelper;

public class WriteActivity extends BaseActivity implements WritePresenter.ViewInterface, AlignChangeListener, FontChangeListener, FileSaveListener{

    private WritePresenterImpl writePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        writePresenter = new WritePresenterImpl(this);

        writePresenter.setViewInterface(this);

        initCardLayout();

        initBottomSheet();

        initImageList();

        initTextSettingLayout();

    }

    @BindColor(R.color.filterColor_white) int currentFilterColor;

    int filterAnimDuration = 300;

    @Bind(R.id.previewImage) ImageView previewImage;

    @Bind(R.id.coverView) View coverView;

    @Bind(R.id.imageRecyclerView) RecyclerView imageRecyclerView;

    @Bind(R.id.bottomSheetLayout) RelativeLayout bottomSheetLayout;

    @Bind(R.id.bottomOverMenuLayout) RelativeLayout bottomOverMenuLayout;

    @Bind(R.id.textBox) RelativeLayout textBox;


    @Bind(R.id.customContentsLayout) PercentRelativeLayout customContentsLayout;
    @Bind(R.id.editTitleText) EditText editTitleText;
    @Bind(R.id.editDescText) EditText editDescText;

    BottomSheetBehavior bottomSheetBehavior;

    private SquareImageAdapter squareImageAdapter;

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

    int imageResource;

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
        writePresenter.excuteImageList();
    }

    @Override
    public void setImageList(List<PixaBayImage> pixaBayImages) {
        squareImageAdapter.setSource(pixaBayImages);

        textSettingLayout.setVisibility(View.INVISIBLE);

        imageRecyclerView.setVisibility(View.VISIBLE);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void setImage(int imageResource) {
        this.imageResource = imageResource;
        Glide.with(this).load(this.imageResource).override(800,800).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(previewImage);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }


    @OnClick(R.id.completeButton)
    public void completeButton(){

        customContentsLayout.buildDrawingCache();

        writePresenter.excuteSaveCard(customContentsLayout.getDrawingCache(), this);

    }


    @Override
    public void onCompleteFileSave(String filePath) {

        runOnUiThread(
                () -> {

                    Contents contents = new Contents(0, imageResource);
                    contents.setFilePath(filePath);
                    EventBus.getInstance().post(contents);

                    setResult(RESULT_OK);
                    finish();
                }
        );

    }

    @Override
    public void completeSaveCard(String message) {
        //hideProgressDialog();



        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setImageFilterColorToResource(int colorResource) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), currentFilterColor, getResources().getColor(colorResource));
        valueAnimator.setDuration(filterAnimDuration);
        valueAnimator.addUpdateListener(animation -> {
            int animationValue = (int) animation.getAnimatedValue();
            previewImage.setColorFilter(new PorterDuffColorFilter(animationValue, PorterDuff.Mode.MULTIPLY));
            currentFilterColor = animationValue;
        });
        valueAnimator.start();
    }

    EditText currentEditText;

    @OnFocusChange({R.id.editTitleText, R.id.editDescText})
    public void onFoucsChanged(View view){
        if(view instanceof EditText){
            currentEditText = (EditText) view;
            //changeSetting(currentEditText.getId() == R.id.editTitleText ? changeTitleSetting : changeDescSetting);
        }
    }

    @Bind(R.id.textSettingLayout) LinearLayout textSettingLayout;

    @OnClick({R.id.textSmaller2, R.id.textSmaller1, R.id.textLarger2, R.id.textLarger1})
    public void onTextSizeChange(View view){

        if(currentEditText == null)
            return;

        float currentTextSize = (int) currentEditText.getTextSize();

        switch (view.getId()){

            case R.id.textSmaller1:
                currentTextSize -= ValueHelper.dpToPx(1);
                break;
            case R.id.textSmaller2:
                currentTextSize -= ValueHelper.dpToPx(2);
                break;
            case R.id.textLarger1:
                currentTextSize += ValueHelper.dpToPx(1);
                break;
            case R.id.textLarger2:
                currentTextSize += ValueHelper.dpToPx(2);
                break;
        }

        currentEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize);
    }

    @Bind(R.id.fontList) RecyclerView fontList;
    @Bind(R.id.alignList) RecyclerView alignList;

    AdapterFontItems adapterFontItems;
    AdapterAlignItems adapterAlignItems;

    private void initTextSettingLayout(){

        adapterFontItems = new AdapterFontItems(getApplicationContext(), this);
        adapterAlignItems = new AdapterAlignItems(getApplicationContext(), this);

        adapterAlignItems.setSource(AlignItem.getAlignItems());
        adapterFontItems.setSource(FontItem.getFontItems(getApplicationContext()));

        LinearLayoutManager fontLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager alignGridLayoutManager = new GridLayoutManager(getApplicationContext(), 3, LinearLayoutManager.VERTICAL, true);

        fontList.setLayoutManager(fontLinearLayoutManger);
        alignList.setLayoutManager(alignGridLayoutManager);

        fontList.setAdapter(adapterFontItems);
        alignList.setAdapter(adapterAlignItems);

    }

    @Override
    public void onChangeAlign(AlignItem alignItem) {


        switch (alignItem.getAlign()){

            case Gravity.TOP:
                changeTextBoxPosition(RelativeLayout.ALIGN_PARENT_TOP, 1f);
                break;

            case Gravity.CENTER_VERTICAL:
                changeTextBoxPosition(RelativeLayout.CENTER_IN_PARENT, 0.8f);
                break;

            case Gravity.BOTTOM:
                changeTextBoxPosition(RelativeLayout.ALIGN_PARENT_BOTTOM, 1f);
                break;

            default:
                if(currentEditText == null)
                    return;
                currentEditText.setGravity(alignItem.getAlign());
                break;

        }

    }

    @Override
    public void onChangeFont(FontItem fontItem) {
        if(currentEditText == null)
            return;

        currentEditText.setTypeface(fontItem.getFont());
    }

    private void changeTextBoxPosition(int rule, float widthPercent){

        PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams) textBox.getLayoutParams();

        PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();

        params.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);

        params.addRule(rule, RelativeLayout.TRUE);
        info.widthPercent = widthPercent;

        textBox.requestLayout();

    }

    @Override
    public void onBackPressed() {

        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }

        super.onBackPressed();
    }

    @BindColor(R.color.keyColor_4) int highLightColor;

    @BindColor(R.color.keyColor_2) int normalColor;

    final int colorChangeDuration = 200;


}