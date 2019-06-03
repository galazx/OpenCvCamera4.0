package br.edu.ifspsaocarlos.sdm.opencvcamera4;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class CameraHSV extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{

    private static final String TAG = "OpenCVCamera";
    private CameraBridgeViewBase cameraBridgeViewBase;
//------------------------imagem processada-----------------------------------------
    Mat imgHSV;
    public int tipo = 0;
    //------------------------------------------------------------------

    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS: {
                    cameraBridgeViewBase.enableView();
                }break;
                default: {
                    super.onManagerConnected(status);
                }break;
            }
        }
    };
    public CameraHSV() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Intent i = getIntent();
        Bundle desc = i.getExtras();
        tipo = desc.getInt("tipo");*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.camera);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        cameraBridgeViewBase = findViewById(R.id.camera_view);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        //cameraBridgeViewBase.setCameraIndex(0); //0 para câmera normal e 1 para self
        cameraBridgeViewBase.setCvCameraViewListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (!OpenCVLoader.initDebug()){
            Log.d(TAG, "opencv library not loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, baseLoaderCallback);
        }
        else {
            Log.d(TAG, "opencv loaded successfully");
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        cameraBridgeViewBase.disableView();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        //------------------------imagem processada----------------------------
        imgHSV = new Mat(width, height, CvType.CV_16UC4);
        //---------------------------------------------------------------------
    }

    @Override
    public void onCameraViewStopped() {
        //mRgba.release();
        //------------------imagem processada-----------
        imgHSV.release();
        //----------------------------------------------
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
      //  if (tipo == 1) {
            Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_RGB2HSV_FULL);
            return imgHSV;
      /*  }
        if (tipo == 2){
            Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_RGB2Luv);
            return imgHSV;
        }
        if (tipo == 3) {
            Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_RGB2YUV);
            return imgHSV;
        }
        if (tipo == 4) {
            Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_RGB2Lab);
            return imgHSV;
        }
        if (tipo == 5) {
            Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_RGB2XYZ);
            return imgHSV;
        }
        else{
            imgHSV = inputFrame.rgba();
            return imgHSV;
        }*/
    }
}
