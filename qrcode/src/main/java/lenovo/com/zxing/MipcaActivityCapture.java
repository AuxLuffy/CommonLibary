package lenovo.com.zxing;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import lenovo.com.qrcode.R;
import lenovo.com.zxing.camera.CameraManager;
import lenovo.com.zxing.decoding.CaptureActivityHandler;
import lenovo.com.zxing.decoding.InactivityTimer;
import lenovo.com.zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.Vector;

public class MipcaActivityCapture extends Activity implements Callback, View.OnClickListener {

    public static final String RESULT_MSG = "MipcaActivityCapture.Result.Message";
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private View backView;
    private boolean isFlashlightOpen = false;
    private CameraManager cameraManager;
    private ImageView mMCapture_scan_back;
    private ImageView mMCapture_flashLight;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        mMCapture_scan_back = (ImageView) findViewById(R.id.capture_scan_back);
        mMCapture_flashLight = (ImageView) findViewById(R.id.capture_flashlight);

        mMCapture_scan_back.setOnClickListener(this);
        mMCapture_flashLight.setOnClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        Intent intent = new Intent();
        intent.putExtra(RESULT_MSG, resultString);
        setResult(RESULT_OK, intent);
        finishUI();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    public static void openMipcapActivity(Activity context, int requestCode) {
        if (cameraIsCanUse()) {
            Intent intent_qr = new Intent(context, MipcaActivityCapture.class);
            intent_qr.putExtra("requestCode", requestCode);
            context.startActivityForResult(intent_qr, requestCode);
        }else{
            Toast.makeText(context,"相机权限未获得",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * ANDROID 6.0以下判断是否有相机权限
     */
    public static boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    /**
     * 后退键
     *
     * @Author LXY
     * Created at 2017/4/13 15:04
     */
    @Override
    public void onBackPressed() {
        finishUI();
    }

    /**
     * 退出
     *
     * @Author LXY
     * Created at 2017/4/13 15:04
     */
    private void finishUI() {
        CameraManager.get().offLight();
        MipcaActivityCapture.this.finish();// 退出动画
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.capture_scan_back) {
            setResult(RESULT_CANCELED);
            finishUI();
        } else if (v.getId() == R.id.capture_flashlight) {
            if (isFlashlightOpen) {
                // 关闭闪光灯
                isFlashlightOpen = false;
                mMCapture_flashLight.setSelected(false);
                CameraManager.get().offLight();
            } else {
                // 打开闪光灯
                isFlashlightOpen = true;
                mMCapture_flashLight.setSelected(true);
                CameraManager.get().openLight();
            }
        }

    }
}
