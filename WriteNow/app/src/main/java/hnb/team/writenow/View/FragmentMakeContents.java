package hnb.team.writenow.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hnb.team.writenow.Adapter.AdapterMakeContents;
import hnb.team.writenow.Interface.AdapterOnClickListener;
import hnb.team.writenow.EventBus.EventBus;
import hnb.team.writenow.ExtendsClass.BaseFragment;
import hnb.team.writenow.ExtendsClass.SpacingItemDecoration;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.Presenter.MakeContentsPresenter;
import hnb.team.writenow.Presenter.MakeContentsPresenterImpl;
import hnb.team.writenow.R;
import hnb.team.writenow.Util.ValueHelper;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class FragmentMakeContents extends BaseFragment implements MakeContentsPresenter.ViewInterface, AdapterOnClickListener{


    public static FragmentMakeContents newInstance() {
        FragmentMakeContents fragmentMakeContents = new FragmentMakeContents();
        return fragmentMakeContents;
    }

    MakeContentsPresenterImpl makeContentsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_make_contents, container, false);

        EventBus.getInstance().register(this);

        ButterKnife.bind(this, rootView);

        initContentsList();

        makeContentsPresenter = new MakeContentsPresenterImpl(this, this);

        makeContentsPresenter.excuteMakeContentsList();

        return rootView;
    }

    @Bind(R.id.makedContentsList) RecyclerView makedContentsList;

    @Bind(R.id.previewImage) ImageView previewImage;

    private AdapterMakeContents adapterMakeContents;

    private LinearLayoutManager linearLayoutManager;

    private void initContentsList(){

        adapterMakeContents = new AdapterMakeContents(getActivity());

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        SpacingItemDecoration spacingItemDecoration = new SpacingItemDecoration(ValueHelper.dpToPx(12), ValueHelper.dpToPx(12));

        makedContentsList.addItemDecoration(spacingItemDecoration);

        makedContentsList.setLayoutManager(linearLayoutManager);

        makedContentsList.setAdapter(adapterMakeContents);

        adapterMakeContents.setAdapterOnClickListener(this);

    }

    public static final int REQUEST_MAKE_CONTENTS = 1;

    @Override
    public void onClickView(int position, Object data) {
        Contents contents = (Contents) data;
        if(contents.getContentsId() == AdapterMakeContents.ADD_BUTTON_ID){
            Intent intent = new Intent(getActivity(), WriteActivity.class);
            getActivity().startActivityForResult(intent, REQUEST_MAKE_CONTENTS);
        }else{
            changePreViewImage(contents);
        }
    }

    @Override
    public void bindingViewRefresh() {
        super.bindingViewRefresh();
        adapterMakeContents.refreshBindingView();
    }

    @Subscribe
    public void addMakingContents(Contents contents){
        adapterMakeContents.addContents(contents);
        dataUpdate();
    }

    @Override
    public void setMakeContentsList(List<Contents> makeContentsList) {
        adapterMakeContents.setSource(makeContentsList);
    }

    @Override
    public void changePreViewImage(Contents contents) {
        Glide.with(this).load(contents.getTitleImage()).into(previewImage);
    }

}
