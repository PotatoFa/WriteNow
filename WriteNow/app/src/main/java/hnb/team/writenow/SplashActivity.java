package hnb.team.writenow;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import hnb.team.writenow.ExtendsClass.BaseActivity;
import hnb.team.writenow.View.CustomActivity;
import hnb.team.writenow.View.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("E5DDE62BA30D319E26568FBFFDDCC586")
                .build();

        admobBottomBanner.loadAd(request);

    }

    @Bind(R.id.admobBottomBanner) AdView admobBottomBanner;

    @OnClick(R.id.writeButton)
    public void writeButton(){
        checkPhoneStatePermission();
    }


    public void checkPhoneStatePermission() {
        TedPermission tedPermission;
        PermissionListener permissionListener;
        permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                startActivity(new Intent(getApplicationContext(), CustomActivity.class));
            }
            @Override
            public void onPermissionDenied(ArrayList<String> arrayList) {
                finish();
                Log.i("Permission Request: ", "Denied");
            }
        };
        tedPermission = new TedPermission(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("권한 체크")
                .setDeniedMessage("WriteNow에서 제작한 카드를 저장하기 위해\n파일 접근 권한을 승인해주세요")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        tedPermission.check();
    }



}
