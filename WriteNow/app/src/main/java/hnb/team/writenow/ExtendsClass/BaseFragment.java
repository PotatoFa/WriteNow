package hnb.team.writenow.ExtendsClass;

import android.util.Log;

import butterknife.ButterKnife;
import hnb.team.writenow.EventBus.EventBus;
import me.jaem.viewdataupdateactivity.BasicFragment;

/**
 * Created by jaehoonjung on 2017. 2. 12..
 */

public class BaseFragment extends BasicFragment {

    @Override
    public void onDestroyView() {
        Log.i(getClass().getSimpleName(), "onDestroyView");
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getInstance().unregister(this);
    }

}
