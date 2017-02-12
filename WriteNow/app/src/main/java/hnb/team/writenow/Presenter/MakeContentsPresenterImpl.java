package hnb.team.writenow.Presenter;

import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class MakeContentsPresenterImpl implements MakeContentsPresenter{

    BaseFragment baseFragment;

    ViewInterface viewInterface;

    public MakeContentsPresenterImpl(BaseFragment baseFragment, ViewInterface viewInterface) {
        this.baseFragment = baseFragment;
        this.viewInterface = viewInterface;
    }

    @Override
    public void excuteMakeContentsList() {
        viewInterface.setMakeContentsList(getContentsList());
    }


    private List<Contents> getContentsList(){
        List<Contents> contentsList = new ArrayList<Contents>();
        contentsList.add(new Contents(0, R.drawable.pic_8));
        return contentsList;
    }

}
