package hnb.team.writenow.Presenter;

import java.util.ArrayList;
import java.util.List;

import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.View.FragmentMakeContents;
import hnb.team.writenow.View.FragmentMyContents;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class MainPresenterImpl implements MainPresenter {

    private ViewInterface viewInterface;
    private BaseActivity baseActivity;

    private FragmentMyContents fragmentMyContents;
    private FragmentMakeContents fragmentMakeContents;

    private List<BaseFragment> fragments = new ArrayList<BaseFragment>();

    public MainPresenterImpl(BaseActivity baseActivity, ViewInterface viewInterface) {
        this.baseActivity = baseActivity;
        this.viewInterface = viewInterface;
        fragmentMyContents = FragmentMyContents.newInstance();
        fragmentMakeContents = FragmentMakeContents.newInstance();
        fragments.add(fragmentMyContents);
        fragments.add(fragmentMakeContents);
    }

    @Override
    public void onClickDrawerMenu(int menuPosition) {
        viewInterface.changeFragment(fragments.get(menuPosition));
    }
}
