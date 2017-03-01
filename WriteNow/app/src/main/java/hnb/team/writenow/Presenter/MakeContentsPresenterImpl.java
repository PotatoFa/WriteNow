package hnb.team.writenow.Presenter;

import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.DirectoryHelper;

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
    public void excuteMakeContentsList(String customFolderPath) {
        viewInterface.setMakeContentsList(getContentsList(customFolderPath));
    }

    private List<Contents> getContentsList(String customFolderPath){
        List<Contents> contentsList = new ArrayList<Contents>();

        List<String> files = DirectoryHelper.getFileToFolderPath(customFolderPath);
        for(String s : files){
            Contents contents = new Contents(0, 0, s);
            contentsList.add(contents);
        }

        return contentsList;
    }

}
