package hnb.team.writenow.Presenter;

import java.util.List;

import hnb.team.writenow.Model.Contents;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public interface MakeContentsPresenter {

    void excuteMakeContentsList(String customFolderPath);

    interface ViewInterface{
        void setMakeContentsList(List<Contents> makeContentsList);
        void changePreViewImage(Contents contents);
    }
}
