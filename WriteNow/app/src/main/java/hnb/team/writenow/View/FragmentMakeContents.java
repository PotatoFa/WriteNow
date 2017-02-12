package hnb.team.writenow.View;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Adapter.AdapterMakeContents;
import hnb.team.writenow.Adapter.AdapterMyContents;
import hnb.team.writenow.Adapter.SquareImageAdapter;
import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.ExtendsClass.SpacingItemDecoration;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.Presenter.MakeContentsPresenter;
import hnb.team.writenow.Presenter.MakeContentsPresenterImpl;
import hnb.team.writenow.Presenter.MyContentsPresenterImpl;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.ValueHelper;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class FragmentMakeContents extends BaseFragment implements MakeContentsPresenter.ViewInterface{


    public static FragmentMakeContents newInstance() {
        FragmentMakeContents fragmentMakeContents = new FragmentMakeContents();
        return fragmentMakeContents;
    }

    MakeContentsPresenterImpl makeContentsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_make_contents, container, false);

        ButterKnife.bind(this, rootView);

        initContentsList();

        makeContentsPresenter = new MakeContentsPresenterImpl(this, this);

        return rootView;
    }

    @Bind(R.id.makedContentsList) RecyclerView makedContentsList;

    @Bind(R.id.previewImage) ImageView previewImage;

    AdapterMakeContents adapterMakeContents;

    private LinearLayoutManager linearLayoutManager;

    private void initContentsList(){

        adapterMakeContents = new AdapterMakeContents(getActivity());

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        SpacingItemDecoration spacingItemDecoration = new SpacingItemDecoration(ValueHelper.dpToPx(16), ValueHelper.dpToPx(16));

        makedContentsList.addItemDecoration(spacingItemDecoration);

        makedContentsList.setLayoutManager(linearLayoutManager);

        makedContentsList.setAdapter(adapterMakeContents);

    }

    @Override
    public void onResume() {

        super.onResume();

        makeContentsPresenter.excuteMakeContentsList();

    }

    @Override
    public void setMakeContentsList(List<Contents> makeContentsList) {

        adapterMakeContents.setSource(makeContentsList);

    }

    @Override
    public void changePreViewImage() {

    }
}
