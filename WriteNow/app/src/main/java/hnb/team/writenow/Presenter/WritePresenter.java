package hnb.team.writenow.Presenter;

import android.graphics.Bitmap;

import java.util.List;

import hnb.team.writenow.Interface.FileSaveListener;
import hnb.team.writenow.Model.PixaBayImage;

/**
 * Created by jaehoonjung on 2017. 2. 10..
 */

public interface WritePresenter {

    void setViewInterface(ViewInterface viewInterface);

    void initTestData();

    void excuteImageList();

    void excuteImageChange();

    void excuteImageChange(PixaBayImage pixaBayImage);

    void changeFilterColor();

    void excuteSaveCard(Bitmap saveBitmap, FileSaveListener fileSaveListener);

    interface ViewInterface{

        void setImage(int imageResource);

        void setImageList(List<PixaBayImage> pixaBayImages);

        void setImageFilterColorToResource(int colorResource);

        void completeSaveCard(String message);
    }
}
