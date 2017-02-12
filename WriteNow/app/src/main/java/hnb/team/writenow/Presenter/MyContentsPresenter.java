package hnb.team.writenow.Presenter;

import java.util.List;

import hnb.team.writenow.Model.Contents;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public interface MyContentsPresenter {

    void excuteContentsList();

    interface ViewInterface{
        void setContentsList(List<Contents> contentsList);
    }

}
