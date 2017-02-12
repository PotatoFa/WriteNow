package hnb.team.writenow.ExtendsClass;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class BaseFragment extends RxFragment {

    @Override
    public void onDestroyView() {
        Log.i(getClass().getSimpleName(), "onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
