package hnb.team.writenow.Presenter;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Model.PixaBayImage;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.AsyncSaveToImage;

import static android.R.attr.bitmap;

/**
 * Created by jaehoonjung on 2017. 2. 10..
 */

public class WritePresenterImpl implements WritePresenter{

    BaseActivity baseActivity;
    ViewInterface viewInterface;

    List<Integer> colorList = new ArrayList<Integer>();
    int colorPosition = 0;

    List<Integer> imageList = new ArrayList<Integer>();
    int imagePosition = 0;

    public WritePresenterImpl(BaseActivity baseActivity){
        this.baseActivity = baseActivity;
        initTestData();
    }

    @Override
    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Override
    public void initTestData() {
        colorList.add(R.color.color1);
        colorList.add(R.color.color2);
        colorList.add(R.color.color3);
        colorList.add(R.color.color4);
        colorList.add(R.color.color5);
        colorList.add(R.color.color6);

        imageList.add(R.drawable.pic_4);
        imageList.add(R.drawable.pic_5);
        imageList.add(R.drawable.pic_6);
        imageList.add(R.drawable.pic_7);
        imageList.add(R.drawable.pic_8);
        imageList.add(R.drawable.pic_1);
        imageList.add(R.drawable.pic_2);
        imageList.add(R.drawable.pic_3);

        for(int i = 0; i < 30; i++){
            pixaBayImages.add(new PixaBayImage(0, imageList.get(i % imageList.size())));
        }
    }

    List<PixaBayImage> pixaBayImages = new ArrayList<PixaBayImage>();

    @Override
    public void excuteImageList() {
        viewInterface.setImageList(pixaBayImages);
    }

    @Override
    public void excuteImageChange() {
        if(imagePosition == imageList.size())
            imagePosition = 0;
        viewInterface.setImage(imageList.get(imagePosition++));
    }

    @Override
    public void excuteImageChange(PixaBayImage pixaBayImage) {
        viewInterface.setImage(pixaBayImage.getTestImageUrl());
    }

    @Override
    public void changeFilterColor() {
        viewInterface.setImageFilterColorToResource(colorList.get(colorPosition));
        if(++colorPosition == colorList.size())
            colorPosition = 0;
    }

    @Override
    public void excuteSaveCard(Bitmap saveBitmap) {

        AsyncSaveToImage.startSaveToImage(saveBitmap, baseActivity);

    }



}
