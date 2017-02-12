package hnb.team.writenow.Presenter;

import android.support.v4.app.Fragment;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public interface MainPresenter {

    void onClickDrawerMenu(int menuPosition);

    interface ViewInterface{
        void changeFragment(Fragment fragment);
    }
}
