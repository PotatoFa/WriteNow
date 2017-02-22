package hnb.team.writenow.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.OnClick;
import hnb.team.writenow.EventBus.EventBus;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.Model.Contents;
import hnb.team.writenow.Presenter.MainPresenter;
import hnb.team.writenow.Presenter.MainPresenterImpl;
import hnb.team.writenow.R;

import static hnb.team.writenow.View.FragmentMakeContents.REQUEST_MAKE_CONTENTS;

public class MainActivity extends BaseActivity implements MainPresenter.ViewInterface{

    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fragmentManager;

    MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this, this);

        initComponent();

        mainPresenter.onClickDrawerMenu(0);

    }

    private void initComponent(){

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.app_name, R.string.app_name);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentManager = getSupportFragmentManager();
    }

    @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;

    @Bind(R.id.menuMakeContents) Button menuMakeContents;
    @Bind(R.id.menuMyContents) Button menuMyContents;
    @Bind(R.id.menuUsersContents) Button menuUsersContents;

    @Bind(R.id.mainToolbar) Toolbar mainToolbar;

    @OnClick({R.id.menuMyContents, R.id.menuMakeContents, R.id.menuUsersContents})
    public void onClickDrawerMenu(View view){
        int position = 0;
        switch (view.getId()){
            case R.id.menuMyContents:{
                position = 0;
                break;
            }
            case R.id.menuMakeContents:{
                position = 1;

                break;
            }
            case R.id.menuUsersContents:{
                position = 2;
                break;
            }
        }
        mainPresenter.onClickDrawerMenu(position);
    }

    @Override
    public void changeFragment(Fragment fragment) {
        if(fragmentManager.getBackStackEntryCount() >= 1)
            fragmentManager.popBackStack();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFragment, fragment)
                .addToBackStack(null)
                .commit();

        drawerLayout.closeDrawers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != RESULT_OK)
            return;

        switch (requestCode){
            case REQUEST_MAKE_CONTENTS:{

                break;
            }
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

}
