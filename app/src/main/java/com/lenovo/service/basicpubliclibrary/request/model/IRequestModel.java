package com.lenovo.service.basicpubliclibrary.request.model;

import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;
import com.lenovo.service.basicpubliclibrary.linechart.model.UploadRecord;

/**
 * Created by cx on 2017/9/18.
 */

public interface IRequestModel {

    void getUploadRecordData(String id,OnRequestCallback<UploadRecord> callback);

}
