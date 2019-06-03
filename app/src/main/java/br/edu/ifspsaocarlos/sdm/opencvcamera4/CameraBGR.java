package br.edu.ifspsaocarlos.sdm.opencvcamera4;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CameraBGR extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{

    private static final String TAG = "OpenCVCamera";
    private CameraBridgeViewBase cameraBridgeViewBase;
    Mat mRgba;
    Mat mRgbaF;
    Mat mRgbaT;
//------------------------imagem processada-----------------------------------------
    int iLowH = 45;
    int iHighH = 75;
    int iLowS = 20;
    int iHighS = 255;
    int iLowV = 10;
    int iHighV = 255;
    Mat imgHSV, imgThresholded;
    Scalar sc1, sc2;
    //------------------------------------------------------------------

    // Used in CameraBGR selection from menu (when implemented)
    private boolean              mIsJavaCamera = true;
    private MenuItem mItemSwitchCamera = null;

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
    public CameraBGR() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.camera);

        //----------------------------imagem processada-------------------------------
        sc1 = new Scalar(iLowH, iLowS, iLowS);
        sc2 = new Scalar(iHighV, iHighH, iHighS);
        //-----------------------------------------------------------------------

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
       // mRgba = new Mat(height, width, CvType.CV_8UC4); mRgbaF = new Mat(height, width, CvType.CV_8UC4);
      //  mRgbaT = new Mat(width, width, CvType.CV_8UC4);
        //------------------------imagem processada----------------------------
        imgHSV = new Mat(width, height, CvType.CV_16UC4);
        imgThresholded = new Mat(width, height, CvType.CV_16UC4);
        //---------------------------------------------------------------------
    }

    @Override
    public void onCameraViewStopped() {
        //mRgba.release();
        //------------------imagem processada-----------
        imgThresholded.release();
        //----------------------------------------------
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
      //  mRgba = inputFrame.rgba();
       // Core.transpose(mRgba, mRgbaT);
       // Imgproc.resize(mRgbaT, mRgbaF, mRgbaF.size(), 0,0, 0);
        //Core.flip(mRgbaF, mRgba, 1 );
        //----------------------------imagem processada-------------------------
        //Imgproc.COLOR_BGR2HSV - mãos claras - 40
        //Imgproc.COLOR_RGB2HSV - mãos escuras - 41
        Imgproc.cvtColor(inputFrame.rgba(), imgHSV, Imgproc.COLOR_BGR2HSV);
        Core.inRange(imgHSV, sc1, sc2, imgThresholded);
        return imgThresholded;
        //------------------------------------------------------------------------
       // return mRgba;
    }
}
