package br.edu.ifspsaocarlos.sdm.opencvcamera4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NovaActivity extends AppCompatActivity {
    int[] imagensIds = new int[]{
            R.drawable.original, R.drawable.cinza, R.drawable.canny, R.drawable.glaucoma,
            R.drawable.cinzacanny, R.drawable.blur, R.drawable.facedetection, R.drawable.rgb,
            R.drawable.camerahsv, R.drawable.bluecanny
    };
    int[] nomesIDs = new int[]{
            R.string.camera, R.string.cameraGButton, R.string.cameraCanny, R.string.CameraGL,
            R.string.cameraCanny2, R.string.cameraBlur, R.string.FaceDetection, R.string.cameraRGBButton,
            R.string.cameraHSV, R.string.BlueCanny
    };
    Class[] classesIDs = new Class[]{
            CameraPreview.class, CameraCinza.class, CameraCanny.class, CameraGL.class,
            CameraCannyGray.class, CameraBlur.class, FaceDetection.class, CameraRGB.class,
            CameraHSV.class, BlueCanny.class
    };
    int i = 0;
    ImageView image;
    TextView tittle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);
        tittle = findViewById(R.id.textView);
        tittle.setText("Original");
        image = (ImageView) findViewById(R.id.cameraImage);
        image.setImageResource(imagensIds[0]);
    }

    public void next (View v){
        if (i < 9){
            i++;
            tittle.setText(nomesIDs[i]);
            image.setImageResource(imagensIds[i]);
        }
    }
    public void back (View v){
        if (i > 0){
            i--;
            tittle.setText(nomesIDs[i]);
            image.setImageResource(imagensIds[i]);
        }
    }
    public void abrircam (View v){
            Intent intent = new Intent(getApplicationContext(), classesIDs[i]);
            startActivity(intent);
    }
}
