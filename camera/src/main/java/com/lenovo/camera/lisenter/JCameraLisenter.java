package com.lenovo.camera.lisenter;

import android.graphics.Bitmap;

public interface JCameraLisenter {

    void captureSuccess(Bitmap bitmap);

    void recordSuccess(String url, Bitmap firstFrame);

    void quit();

}
