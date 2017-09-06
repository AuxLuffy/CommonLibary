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
 *
 * @Author 李巷阳
 * Created at 2017/8/10 15:28
 */
public class ContactInfoUtils {

    /**
     * 获取通讯录。
     * @param mContext
     * @return
     */
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
                ModelContactCity contactsInfo = new ModelContactCity(contactName, contactHeadLetter,contactNumber);
                if (contactName != null)
                    list.add(contactsInfo);
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

    /**
     * 通讯录去除不符合手机号格式的数据。
     * @param oldModelList
     * @return
     */
    public static List<ModelContactCity> getRightMobile(List<ModelContactCity> oldModelList)
    {
        List<ModelContactCity> newlist = new ArrayList<ModelContactCity>();
        if(oldModelList!=null && oldModelList.size()>0){
            for(int x=0;x<oldModelList.size();x++){
                ModelContactCity mc=oldModelList.get(x);
                String contactNames = mc.name.replaceAll(" ", "");
                String contactNumbers = mc.number.replaceAll(" ", "").replace("-", "").replace("+86", "");
                if (isMobileNO(contactNumbers)) {
                    ModelContactCity contactsInfo = new ModelContactCity(contactNames, mc.pys,contactNumbers);
                    newlist.add(contactsInfo);
                }

            }

          }
        return newlist;
    }

    /**
     * 获取通讯录对应的字符集。
     * @param ContactInfo  所有的通讯录
     * @param Letterdata   所有的字符集
     * @return
     */
    public static List<ModelContactCity> getLetterdata(List<ModelContactCity> ContactInfo,List<ModelContactCity> Letterdata){

        final List<ModelContactCity> results = new ArrayList<>();
        if(ContactInfo.isEmpty() || Letterdata.isEmpty())
            return null;

        final int size = ContactInfo.size();
        final int count = Letterdata.size();
        for (int i = 0; i < size; i++)
        {
            String srcId = ContactInfo.get(i).pys;
            for (int j = 0; j < count; j++)
            {
                ModelContactCity t = Letterdata.get(j);
                if (srcId.equals(t.pys))
                {
                    if(!results.contains(t))
                    {
                        results.add(t);
                    }
                }
            }
        }
        return results;
    }

}
