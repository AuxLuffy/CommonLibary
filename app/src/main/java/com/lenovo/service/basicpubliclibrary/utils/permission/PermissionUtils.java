package com.lenovo.service.basicpubliclibrary.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qianxiaoai on 2016/7/7.
 */
public class PermissionUtils {

    private static final String TAG = PermissionUtils.class.getSimpleName();
    public static final int CODE_GET_ACCOUNTS = 0;      // 获取通讯录标识
    public static final int CODE_READ_EXTERNAL_STORAGE = 1;// 读取内存卡权限标识
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 2;// 写入内存卡权限标识

    public static final int CODE_MULTI_PERMISSION = 100;


    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;    // 获取通讯录权限
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;// 读取存储卡权限
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;// 写入存储卡权限



    private static final String[] requestPermissions = {
            PERMISSION_READ_CONTACTS,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    public interface PermissionGrant {
        void onPermissionGranted(int requestCode);
    }

    /**
     * 获取权限的方法
     * @param activity
     * @param requestCode   要获取的权限标识
     * @param permissionGrant   获取权限后的成功回调
     */
    public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }
        // 权限标识同时也代表权限在集合中的位置。
        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            return;
        }
        // 获取具体的权限
        final String requestPermission = requestPermissions[requestCode];
        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            Toast.makeText(activity, "please open this permission", Toast.LENGTH_SHORT).show();
            return;
        }
        // 判断是否具有权限
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                // 如果没有权限,则弹框提示用户去设置
                shouldShowRationale(activity, requestCode, requestPermission);
            } else {
                // 如果第一次,就弹出系统自带的权限提示。
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }

        } else {
            // 回调给调用者
            permissionGrant.onPermissionGranted(requestCode);
        }
    }

    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }
        Map<String, Integer> perms = new HashMap<>();
        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            Toast.makeText(activity, "all permission success" + notGranted, Toast.LENGTH_SHORT).show();
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION);
        } else {
            openSettingActivity(activity, "those permission need granted!");
        }

    }


    /**
     * 一次性申请多个权限
     * @param activity
     * @param grant 多个权限的集合
     */
    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant) {

        final List<String> permissionsList = getNoGrantedPermission(activity, false);// 获取所有权限里,用户没有允许的权限
        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true);// 获取用户拒绝的权限集合
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        // 如果用户没有权限，则依次给用户提示
        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
CODE_MULTI_PERMISSION);

        } else if (shouldRationalePermissionsList.size() > 0) {
            showApplyPowerMsg(activity, new OnApplyPowerListener() {
                @Override
                public void onMyPositiveButton() {
                    ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                            CODE_MULTI_PERMISSION);
                }
            });

        } else {
            grant.onPermissionGranted(CODE_MULTI_PERMISSION);
        }

    }


    private static void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {

        showApplyPowerMsg(activity, new OnApplyPowerListener() {
            @Override
            public void onMyPositiveButton() {
               ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }
        });



    }


    /**
     * @param activity
     * @param requestCode  Need consistent with requestPermission
     * @param permissions
     * @param grantResults
     */
    public static void requestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
                                                @NonNull int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        if (requestCode == CODE_MULTI_PERMISSION) {
            requestMultiResult(activity, permissions, grantResults, permissionGrant);
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            Toast.makeText(activity, "illegal requestCode:" + requestCode, Toast.LENGTH_SHORT).show();
            return;
        }


        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGrant.onPermissionGranted(requestCode);

        } else {
            String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
            openSettingActivity(activity, "Result" + permissionsHint[requestCode]);
        }

    }

    private static void openSettingActivity(final Activity activity, String message) {

        showApplyPowerMsg(activity, new OnApplyPowerListener() {
            @Override
            public void onMyPositiveButton() {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Log.d(TAG, "getPackageName(): " + activity.getPackageName());
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });


    }


    /**
     * @param activity
     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions, false:return no granted and !shouldShowRequestPermissionRationale
     * @return
     */
    public static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < requestPermissions.length; i++) {
            String requestPermission = requestPermissions[i];


            //TODO checkSelfPermission
            int checkSelfPermission = -1;
            try {
                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
            } catch (RuntimeException e) {
                Toast.makeText(activity, "please open those permission", Toast.LENGTH_SHORT)
                        .show();
                return null;
            }

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {

                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }

            }
        }
        return permissions;
    }


    private static void showApplyPowerMsg(final Activity context,  final OnApplyPowerListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.help);//提示帮助
        builder.setMessage(R.string.string_help_text);

        //如果是拒绝授权，则退出应用
        //退出
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
                dialog.dismiss();
                dialog.cancel();
            }
        });
        //打开设置，让用户选择打开权限
        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                okListener.onMyPositiveButton();

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

     interface OnApplyPowerListener{
        void onMyPositiveButton();
    }



//    /**
//     * Requests permission.
//     *
//     * @param activity
//     * @param requestCode request code, e.g. if you need request CAMERA permission,parameters is PermissionUtils.CODE_CAMERA
//     */
//    public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
//        if (activity == null) {
//            return;
//        }
//
//        Log.i(TAG, "requestPermission requestCode:" + requestCode);
//        if (requestCode < 0 || requestCode >= requestPermissions.length) {
//            Log.w(TAG, "requestPermission illegal requestCode:" + requestCode);
//            return;
//        }
//
//        final String requestPermission = requestPermissions[requestCode];
//
//        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
//        // 但是，如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
//        // 你可以使用try{}catch(){},处理异常，也可以判断系统版本，低于23就不申请权限，直接做你想做的。permissionGrant.onPermissionGranted(requestCode);
////        if (Build.VERSION.SDK_INT < 23) {
////            permissionGrant.onPermissionGranted(requestCode);
////            return;
////        }
//        int checkSelfPermission;
//        try {
//            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
//        } catch (RuntimeException e) {
//            Toast.makeText(activity, "please open this permission", Toast.LENGTH_SHORT)
//                    .show();
//            Log.e(TAG, "RuntimeException:" + e.getMessage());
//            return;
//        }
//
//        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "ActivityCompat.checkSelfPermission != PackageManager.PERMISSION_GRANTED");
//
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
//                Log.i(TAG, "requestPermission shouldShowRequestPermissionRationale");
//                shouldShowRationale(activity, requestCode, requestPermission);
//
//            } else {
//                Log.d(TAG, "requestCameraPermission else");
//                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
//            }
//
//        } else {
//            Log.d(TAG, "ActivityCompat.checkSelfPermission ==== PackageManager.PERMISSION_GRANTED");
//            Toast.makeText(activity, "opened:" + requestPermissions[requestCode], Toast.LENGTH_SHORT).show();
//            permissionGrant.onPermissionGranted(requestCode);
//        }
//    }
//
//    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {
//
//        if (activity == null) {
//            return;
//        }
//
//        //TODO
//        Log.d(TAG, "onRequestPermissionsResult permissions length:" + permissions.length);
//        Map<String, Integer> perms = new HashMap<>();
//
//        ArrayList<String> notGranted = new ArrayList<>();
//        for (int i = 0; i < permissions.length; i++) {
//            Log.d(TAG, "permissions: [i]:" + i + ", permissions[i]" + permissions[i] + ",grantResults[i]:" + grantResults[i]);
//            perms.put(permissions[i], grantResults[i]);
//            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                notGranted.add(permissions[i]);
//            }
//        }
//
//        if (notGranted.size() == 0) {
//            Toast.makeText(activity, "all permission success" + notGranted, Toast.LENGTH_SHORT)
//                    .show();
//            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION);
//        } else {
//            openSettingActivity(activity, "those permission need granted!");
//        }
//
//    }
//
//
//    /**
//     * 一次申请多个权限
//     */
//    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant) {
//
//        final List<String> permissionsList = getNoGrantedPermission(activity, false);
//        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true);
//
//        //TODO checkSelfPermission
//        if (permissionsList == null || shouldRationalePermissionsList == null) {
//            return;
//        }
//        Log.d(TAG, "requestMultiPermissions permissionsList:" + permissionsList.size() + ",shouldRationalePermissionsList:" + shouldRationalePermissionsList.size());
//
//        if (permissionsList.size() > 0) {
//            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
//                    CODE_MULTI_PERMISSION);
//            Log.d(TAG, "showMessageOKCancel requestPermissions");
//
//        } else if (shouldRationalePermissionsList.size() > 0) {
//            showMessageOKCancel(activity, "should open those permission",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
//                                    CODE_MULTI_PERMISSION);
//                            Log.d(TAG, "showMessageOKCancel requestPermissions");
//                        }
//                    });
//        } else {
//            grant.onPermissionGranted(CODE_MULTI_PERMISSION);
//        }
//
//    }
//
//
//    private static void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {
//        //TODO
//        String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
//        showMessageOKCancel(activity, "Rationale: " + permissionsHint[requestCode], new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
//                Log.d(TAG, "showMessageOKCancel requestPermissions:" + requestPermission);
//            }
//        });
//    }
//
//    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
//        new AlertDialog.Builder(context)
//                .setMessage(message)
//                .setPositiveButton("OK", okListener)
//                .setNegativeButton("Cancel", null)
//                .create()
//                .show();
//    }
//
//    /**
//     * @param activity
//     * @param requestCode  Need consistent with requestPermission
//     * @param permissions
//     * @param grantResults
//     */
//    public static void requestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
//                                                @NonNull int[] grantResults, PermissionGrant permissionGrant) {
//
//        if (activity == null) {
//            return;
//        }
//        Log.d(TAG, "requestPermissionsResult requestCode:" + requestCode);
//
//        if (requestCode == CODE_MULTI_PERMISSION) {
//            requestMultiResult(activity, permissions, grantResults, permissionGrant);
//            return;
//        }
//
//        if (requestCode < 0 || requestCode >= requestPermissions.length) {
//            Log.w(TAG, "requestPermissionsResult illegal requestCode:" + requestCode);
//            Toast.makeText(activity, "illegal requestCode:" + requestCode, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Log.i(TAG, "onRequestPermissionsResult requestCode:" + requestCode + ",permissions:" + permissions.toString()
//                + ",grantResults:" + grantResults.toString() + ",length:" + grantResults.length);
//
//        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "onRequestPermissionsResult PERMISSION_GRANTED");
//            //TODO success, do something, can use callback
//            permissionGrant.onPermissionGranted(requestCode);
//
//        } else {
//            //TODO hint user this permission function
//            Log.i(TAG, "onRequestPermissionsResult PERMISSION NOT GRANTED");
//            //TODO
//            String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
//            openSettingActivity(activity, "Result" + permissionsHint[requestCode]);
//        }
//
//    }
//
//    private static void openSettingActivity(final Activity activity, String message) {
//
//        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Log.d(TAG, "getPackageName(): " + activity.getPackageName());
//                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//                intent.setData(uri);
//                activity.startActivity(intent);
//            }
//        });
//    }
//
//
//    /**
//     * @param activity
//     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions, false:return no granted and !shouldShowRequestPermissionRationale
//     * @return
//     */
//    public static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale) {
//
//        ArrayList<String> permissions = new ArrayList<>();
//
//        for (int i = 0; i < requestPermissions.length; i++) {
//            String requestPermission = requestPermissions[i];
//
//
//            //TODO checkSelfPermission
//            int checkSelfPermission = -1;
//            try {
//                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
//            } catch (RuntimeException e) {
//                Toast.makeText(activity, "please open those permission", Toast.LENGTH_SHORT)
//                        .show();
//                Log.e(TAG, "RuntimeException:" + e.getMessage());
//                return null;
//            }
//
//            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
//                Log.i(TAG, "getNoGrantedPermission ActivityCompat.checkSelfPermission != PackageManager.PERMISSION_GRANTED:" + requestPermission);
//
//                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
//                    Log.d(TAG, "shouldShowRequestPermissionRationale if");
//                    if (isShouldRationale) {
//                        permissions.add(requestPermission);
//                    }
//
//                } else {
//
//                    if (!isShouldRationale) {
//                        permissions.add(requestPermission);
//                    }
//                    Log.d(TAG, "shouldShowRequestPermissionRationale else");
//                }
//
//            }
//        }
//
//        return permissions;
//    }

}
