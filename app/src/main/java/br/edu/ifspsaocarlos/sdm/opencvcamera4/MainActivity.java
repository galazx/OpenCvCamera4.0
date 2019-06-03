package br.edu.ifspsaocarlos.sdm.opencvcamera4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button cameraBGRBtn, cameraCinzaBtn, cameraRGBBtn, cameraCannyBtn, cameraBtn, cameraBlurBtn, cameraHSVBtn, cameraCanny2Btn;
    Button cameraLUVBtn, cameraYUVBtn, cameraLABBtn, cameraXYZBtn, cameraFaceBtn, cameraGlBtn;
    int nTipo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraBGRBtn = findViewById(R.id.cameraBGRButton);
        cameraRGBBtn = findViewById(R.id.cameraRGBButton);
        cameraCinzaBtn = findViewById(R.id.cameraGButton);
        cameraCannyBtn = findViewById(R.id.cameraCanny);
        cameraBlurBtn = findViewById(R.id.cameraBlur);
        cameraHSVBtn = findViewById(R.id.cameraHSV);
        cameraBtn = findViewById(R.id.camera);
        cameraCanny2Btn = findViewById(R.id.cameraCanny2);
        cameraLUVBtn = findViewById(R.id.cameraLUV);
        cameraYUVBtn = findViewById(R.id.cameraYUV);
        cameraLABBtn = findViewById(R.id.cameraLAB);
        cameraXYZBtn = findViewById(R.id.cameraXYZ);
        cameraFaceBtn = findViewById(R.id.cameraFace);
        cameraGlBtn = findViewById(R.id.cameraGL);

        cameraCinzaBtn.setOnClickListener(camListener);
        cameraBGRBtn.setOnClickListener(camListener);
        cameraRGBBtn.setOnClickListener(camListener);
        cameraCannyBtn.setOnClickListener(camListener);
        cameraBlurBtn.setOnClickListener(camListener);
        cameraBtn.setOnClickListener(camListener);
        cameraHSVBtn.setOnClickListener(camListener);
        cameraLUVBtn.setOnClickListener(camListener);
        cameraLABBtn.setOnClickListener(camListener);
        cameraYUVBtn.setOnClickListener(camListener);
        cameraCanny2Btn.setOnClickListener(camListener);
        cameraXYZBtn.setOnClickListener(camListener);
        cameraFaceBtn.setOnClickListener(camListener);
        cameraGlBtn.setOnClickListener(camListener);
    }
     private View.OnClickListener camListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == cameraBGRBtn) {
                Intent i = new Intent(getApplicationContext() , CameraBGR.class);
                startActivity(i);
            }
            if (v == cameraRGBBtn) {
                Intent i = new Intent(getApplicationContext() , CameraRGB.class);
                startActivity(i);
            }
            if (v == cameraCinzaBtn) {
                Intent i = new Intent(getApplicationContext(), CameraCinza.class);
                startActivity(i);
            }
            if (v == cameraCannyBtn) {
                    Intent i = new Intent(getApplicationContext() , CameraCanny.class);
                    startActivity(i);
            }
            if (v == cameraBtn) {
                Intent i = new Intent(getApplicationContext() , CameraPreview.class);
                startActivity(i);
            }
            if (v == cameraBlurBtn) {
                Intent i = new Intent(getApplicationContext() , CameraBlur.class);
                startActivity(i);
            }
            if (v == cameraCanny2Btn) {
                Intent i = new Intent(getApplicationContext() , CameraCannyGray.class);
                startActivity(i);
            }
            if (v == cameraHSVBtn) {
                nTipo = 1;
                Intent i = new Intent(getApplicationContext() , CameraHSV.class);
                i.putExtra("tipo", nTipo);
                startActivity(i);
            }
            if (v == cameraLUVBtn) {
                nTipo = 2;
                Intent i = new Intent(getApplicationContext() , CameraHSV.class);
                i.putExtra("tipo", nTipo);
                startActivity(i);
            }
            if (v == cameraYUVBtn) {
                nTipo = 3;
                Intent i = new Intent(getApplicationContext() , CameraHSV.class);
                i.putExtra("tipo", nTipo);
                startActivity(i);
            }
            if (v == cameraLABBtn) {
                nTipo = 4;
                Intent i = new Intent(getApplicationContext() , CameraHSV.class);
                i.putExtra("tipo", nTipo);
                startActivity(i);
            }
            if (v == cameraXYZBtn) {
                nTipo = 5;
                Intent i = new Intent(getApplicationContext() , CameraHSV.class);
                i.putExtra("tipo", nTipo);
                startActivity(i);
            }
            if (v == cameraFaceBtn) {
                Intent i = new Intent(getApplicationContext() , FaceDetection.class);
                startActivity(i);
            }
            if (v == cameraGlBtn) {
                Intent i = new Intent(getApplicationContext() , CameraGL.class);
                startActivity(i);
            }
        }
    };
}
