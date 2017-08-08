package com.lenovo.service.basicpubliclibrary.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.lenovo.service.basicpubliclibrary.maillistananimation.bean.ModelContactCity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2017/8/3.
 */

public class ContactInfoUtils {

    public static List<ModelContactCity> getContactList(Context mContext){
        String contactName;
        String contactNumber;
        String contactHeadLetter;
        List<ModelContactCity> list = new ArrayList<ModelContactCity>();
        try {
            ContentResolver contentResolver = mContext.getContentResolver();

            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = contentResolver.query(contactUri,
                    new String[]{"display_name", "sort_key", "contact_id", "data1","phonebook_label"},
                    null, null, "sort_key");
            if(!cursor.moveToFirst()){
                cursor.close();
                return null;
            }
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactHeadLetter=cursor.getString(cursor.getColumnIndex("phonebook_label"));//这个字段保存了每个联系人首字的拼音的首字母

                String contactNames = contactName.replaceAll(" ", "");
                String contactNumbers = contactNumber.replaceAll(" ", "").replace("-", "").replace("+86", "");
                if (isMobileNO(contactNumbers)) {
                    ModelContactCity contactsInfo = new ModelContactCity(contactNames, contactHeadLetter,contactNumbers);
                    if (contactName != null)
                        list.add(contactsInfo);
                }
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            context = null;
        }
            return list;

    }
    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
