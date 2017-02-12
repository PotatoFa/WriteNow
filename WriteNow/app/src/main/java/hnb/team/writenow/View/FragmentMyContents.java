package hnb.team.writenow.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Adapter.AdapterMyContents;
import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.Presenter.MyContentsPresenter;
import hnb.team.writenow.Presenter.MyContentsPresenterImpl;
import hnb.team.writenow.R;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class FragmentMyContents extends BaseFragment implements MyContentsPresenter.ViewInterface{

    public static FragmentMyContents newInstance() {
        FragmentMyContents fragmentMyContents = new FragmentMyContents();
        return fragmentMyContents;
    }

    MyContentsPresenterImpl myContentsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_contents, container, false);

        ButterKnife.bind(this, rootView);

        initContentsList();

        myContentsPresenter = new MyContentsPresenterImpl(this, this);

        return rootView;
    }

    @Bind(R.id.myContentsList) RecyclerView myContentsList;

    private AdapterMyContents adapterMyContents;
    private LinearLayoutManager linearLayoutManager;

    private void initContentsList(){

        adapterMyContents = new AdapterMyContents(getActivity());

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        myContentsList.setLayoutManager(linearLayoutManager);

        myContentsList.setAdapter(adapterMyContents);

    }

    @Override
    public void onResume() {
        super.onResume();
        myContentsPresenter.excuteContentsList();
    }

    @Override
    public void setContentsList(List<Contents> contentsList) {
        adapterMyContents.setSource(contentsList);
    }
}

