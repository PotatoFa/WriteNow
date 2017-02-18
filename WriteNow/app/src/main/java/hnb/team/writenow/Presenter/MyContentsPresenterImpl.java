package hnb.team.writenow.Presenter;

import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class MyContentsPresenterImpl implements MyContentsPresenter{

    BaseFragment baseFragment;

    ViewInterface viewInterface;

    public MyContentsPresenterImpl(BaseFragment baseFragment, ViewInterface viewInterface) {
        this.baseFragment = baseFragment;
        this.viewInterface = viewInterface;
    }

    @Override
    public void excuteContentsList() {
        viewInterface.setContentsList(getContentsList());
    }

    private List<Contents> getContentsList(){
        List<Contents> contentsList = new ArrayList<Contents>();
        contentsList.add(new Contents(0, R.drawable.pic_4));
        contentsList.add(new Contents(0, R.drawable.pic_5));
        contentsList.add(new Contents(0, R.drawable.pic_6));
        contentsList.add(new Contents(0, R.drawable.pic_7));
        contentsList.add(new Contents(0, R.drawable.pic_8));
        contentsList.add(new Contents(0, R.drawable.pic_4));
        contentsList.add(new Contents(0, R.drawable.pic_5));
        contentsList.add(new Contents(0, R.drawable.pic_6));
        contentsList.add(new Contents(0, R.drawable.pic_7));
        contentsList.add(new Contents(0, R.drawable.pic_8));
        return contentsList;
    }
}
