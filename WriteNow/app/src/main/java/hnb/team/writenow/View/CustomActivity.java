package hnb.team.writenow.View;

import android.animation.ValueAnimator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import hnb.team.writenow.Adapter.AdapterFontItems;
import hnb.team.writenow.Adapter.AdapterImageFilterColors;
import hnb.team.writenow.Adapter.AdapterTextColors;
import hnb.team.writenow.Adapter.SquareImageAdapter;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Interface.FileSaveListener;
import hnb.team.writenow.Interface.colorChangeListener;
import hnb.team.writenow.Model.ColorItem;
import hnb.team.writenow.Model.FontItem;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.AnimationHelper;
import hnb.team.writenow.Util.AsyncSaveToImage;
import hnb.team.writenow.Util.ValueHelper;

import static android.view.View.GONE;
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

        initFontList();

        initSizeSeekBar();

        initColorSetting();

    }

    private void initCardLayout(){
        customContentsLayout.setDrawingCacheEnabled(true);
        customContentsLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void initBottomSheet(){

        bottomTextSettingLayout.setNestedScrollingEnabled(true);
        bottomColorSettingLayout.setNestedScrollingEnabled(true);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_DRAGGING)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private SquareImageAdapter squareImageAdapter;

    private void initImageList(){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        squareImageAdapter = new SquareImageAdapter(getApplicationContext());

        imageRecyclerView.setLayoutManager(gridLayoutManager);
        imageRecyclerView.setAdapter(squareImageAdapter);
        squareImageAdapter.setAdapterOnClickListener((position, data) -> {
            PixaBayImage pixaBayImage = (PixaBayImage) data;
            Glide.with(this).load(pixaBayImage.getTestImageUrl()).into(previewImage);
        });
    }

    private void initFontList(){

        AdapterFontItems adapterTitleFontItems = new AdapterFontItems(getApplicationContext(), (fontItem) -> {
            if(editTitleText == null)
                return;
            editTitleText.setTypeface(fontItem.getFont());
        });

        AdapterFontItems adapterDescFontItems = new AdapterFontItems(getApplicationContext(), (fontItem) -> {
            if(editDescText == null)
                return;
            editDescText.setTypeface(fontItem.getFont());
        });


        adapterDescFontItems.setSource(FontItem.getFontItems(getApplicationContext()));

        LinearLayoutManager titleFontLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        listDescFont.setLayoutManager(titleFontLinearLayoutManger);

        listDescFont.setAdapter(adapterDescFontItems);


        adapterTitleFontItems.setSource(FontItem.getFontItems(getApplicationContext()));

        LinearLayoutManager descFontLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        listTitleFont.setLayoutManager(descFontLinearLayoutManger);

        listTitleFont.setAdapter(adapterTitleFontItems);
    }

    private int titleMinimumSize = 18;
    private int descMinimumSize = 10;
    private void initSizeSeekBar(){
        seekBarTitleSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editTitleText.setTextSize(titleMinimumSize + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarDescSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editDescText.setTextSize(descMinimumSize + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private final String BACKGROUND_THEME_LIGHT = "LIGHT";
    private final String BACKGROUND_THEME_DARK = "DARK";
    private final String BACKGROUND_THEME_NONE = "NONE";
    private String CURRENT_THEME = BACKGROUND_THEME_LIGHT;
    AdapterTextColors titleTextColorAdapter;
    AdapterTextColors descTextColorAdapter;
    AdapterImageFilterColors adapterImageFilterColors;
    List<Integer> textBoxColorList = ColorItem.getTextBoxColorList();

    private void initColorSetting(){

        seekBarBoxColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textBox.setBackgroundColor(textBoxColorList.get(progress));

                changeContentsBackGroundTheme(progress == 0 ? BACKGROUND_THEME_NONE : progress < 5 ? BACKGROUND_THEME_LIGHT : BACKGROUND_THEME_DARK);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        titleTextColorAdapter = new AdapterTextColors(getApplicationContext(), hexColor -> {

            editTitleText.setTextColor(hexColor);

        }, AdapterTextColors.TEXT_TYPE_TITLE);

        titleTextColorAdapter.setSource(ColorItem.getBrightColorList());

        LinearLayoutManager titleColorLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        listTitleColor.setLayoutManager(titleColorLinearLayoutManger);

        listTitleColor.setAdapter(titleTextColorAdapter);


        descTextColorAdapter = new AdapterTextColors(getApplicationContext(), hexColor -> {

            editDescText.setTextColor(hexColor);

        }, AdapterTextColors.TEXT_TYPE_DESC);

        descTextColorAdapter.setSource(ColorItem.getDarknessColorList());

        LinearLayoutManager descColorLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        listDescColor.setLayoutManager(descColorLinearLayoutManger);

        listDescColor.setAdapter(descTextColorAdapter);


        adapterImageFilterColors = new AdapterImageFilterColors(getApplicationContext(),hexColor -> {
            imageFilterView.setBackgroundColor(hexColor);
            //previewImage.setColorFilter(new PorterDuffColorFilter(hexColor, PorterDuff.Mode.MULTIPLY));
        });

        adapterImageFilterColors.setSource(ColorItem.getImageFilterColorList());

        LinearLayoutManager imageFilterColorLinearLayoutManger = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        listImageFilterColor.setLayoutManager(imageFilterColorLinearLayoutManger);

        listImageFilterColor.setAdapter(adapterImageFilterColors);

    }

    private void changeContentsBackGroundTheme(String backgroundTheme){

        if(CURRENT_THEME.equals(backgroundTheme))
            return;

        switch (backgroundTheme){
            case BACKGROUND_THEME_LIGHT:
                titleTextColorAdapter.setSource(ColorItem.getDarknessColorList());
                descTextColorAdapter.setSource(ColorItem.getBrightColorList());
                break;
            case BACKGROUND_THEME_DARK:
                titleTextColorAdapter.setSource(ColorItem.getBrightColorList());
                descTextColorAdapter.setSource(ColorItem.getDarknessColorList());
                break;
            case BACKGROUND_THEME_NONE:
                titleTextColorAdapter.setSource(ColorItem.getDarknessColorList());
                descTextColorAdapter.setSource(ColorItem.getDarknessColorList());
                break;
        }

        CURRENT_THEME = backgroundTheme;
    }

    BottomSheetBehavior bottomSheetBehavior;

    @Bind(R.id.customContentsLayout) PercentRelativeLayout customContentsLayout;

    @Bind(R.id.previewImage) ImageView previewImage;

    @Bind(R.id.imageFilterView) View imageFilterView;

    @Bind(R.id.textBox) RelativeLayout textBox;
    @Bind(R.id.editTitleText) EditText editTitleText;
    @Bind(R.id.editDescText) EditText editDescText;


    @Bind(R.id.coverView) View coverView;

    @Bind(R.id.bottomSheetLayout) LinearLayout bottomSheetLayout;

    @Bind(R.id.bottomOverMenuLayout) LinearLayout bottomOverMenuLayout;

    @Bind(R.id.changeTempleteLayout) LinearLayout changeTempleteLayout;
    @Bind(R.id.changeTextLayout) LinearLayout changeTextLayout;
    @Bind(R.id.changeColorLayout) LinearLayout changeColorLayout;

    @Bind(R.id.imageRecyclerView) RecyclerView imageRecyclerView;

    @Bind(R.id.bottomTextSettingLayout) NestedScrollView bottomTextSettingLayout;

    @Bind(R.id.listTitleFont) RecyclerView listTitleFont;
    @Bind(R.id.listDescFont) RecyclerView listDescFont;

    @Bind(R.id.seekBarTitleSize) SeekBar seekBarTitleSize;
    @Bind(R.id.seekBarDescSize) SeekBar seekBarDescSize;

    @Bind(R.id.bottomColorSettingLayout) NestedScrollView bottomColorSettingLayout;

    @Bind(R.id.seekBarBoxColor) SeekBar seekBarBoxColor;

    @Bind(R.id.listTitleColor) RecyclerView listTitleColor;
    @Bind(R.id.listDescColor) RecyclerView listDescColor;
    @Bind(R.id.listImageFilterColor) RecyclerView listImageFilterColor;


    @OnCheckedChanged(R.id.checkBoxTitleVisible)
    public void checkBoxTitleVisible(CheckBox cb , boolean checked){
        if(checked)
            AnimationHelper.animationToView(editTitleText, GONE);
        else
            AnimationHelper.animationToView(editTitleText, VISIBLE);

    }

    @OnCheckedChanged(R.id.checkBoxDescVisible)
    public void checkBoxDescVisible(CheckBox cb , boolean checked) {
        if (checked)
            AnimationHelper.animationToView(editDescText, GONE);
        else
            AnimationHelper.animationToView(editDescText, VISIBLE);
    }

    @OnClick(R.id.changeImageButton)
    public void changeImageButton(){
        squareImageAdapter.setSource(getTextImageData());
        imageRecyclerView.setVisibility(View.VISIBLE);
        bottomColorSettingLayout.setVisibility(View.GONE);
        bottomTextSettingLayout.setVisibility(View.GONE);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @OnClick(R.id.changeColorLayout)
    public void changeColorLayout(){
        bottomColorSettingLayout.setVisibility(View.VISIBLE);
        bottomTextSettingLayout.setVisibility(View.GONE);
        imageRecyclerView.setVisibility(View.GONE);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void onCompleteFileSave(String filePath) {
        Log.i("onComplete", "File Save : " + filePath);
    }

    private final int bottomInvisibleHeight = ValueHelper.dpToPx(40);
    private final int bottomVisibleHeight = ValueHelper.dpToPx(240);

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

        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED){

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            return;
        }

        showFinishedDialog();

        //super.onBackPressed();

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

    private void showFinishedDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setMessage(getResources().getString(R.string.str_custom_finish));

        builder.setPositiveButton(getResources().getString(R.string.str_custom_finish_ok), (dialog, which) -> {
            dialog.cancel();
            finish();
        });

        builder.setNegativeButton(getResources().getString(R.string.str_custom_finish_cancel), (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }


}
