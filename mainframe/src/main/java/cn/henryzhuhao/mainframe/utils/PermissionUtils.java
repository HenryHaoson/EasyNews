package cn.henryzhuhao.mainframe.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import cn.henryzhuhao.mainframe.frame.base.BaseActivity;


/**
 * Created by HenryZhuhao on 2017/3/3.
 */

public class PermissionUtils {
    public static void applypermissions(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, BaseActivity.REQUEST_CODE_PERMISSION);
            }
        }
    }
}
