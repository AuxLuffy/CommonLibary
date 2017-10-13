package com.lenovo.service.basicpubliclibrary.uploadfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.request.net.ServiceGenerator;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

public class UploadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        findViewById(R.id.btnUpload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UploadFileActivity.this, "start uploading", Toast.LENGTH_SHORT).show();

//                uploadPic(null, "");
            }
        });
    }


    private void uploadPic(File uploadFile, String otherArgs) {

        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploadfile", uploadFile.getName(), reqFile);
        RequestBody reqSNCode =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), otherArgs);
        RequestBody reqShopId =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), otherArgs);

        ServiceGenerator.getApiService().couponUploadPic(reqSNCode, reqShopId, body)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(UploadFileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(UploadFileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
