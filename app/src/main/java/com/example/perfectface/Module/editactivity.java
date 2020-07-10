package com.example.perfectface.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amnix.skinsmoothness.AmniXSkinSmooth;
import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.example.perfectface.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class editactivity extends AppCompatActivity {
    private HorizontalScrollMenuView menu;
    private ImageView imageView;
    private Uri myUri;
    private TextView ButtonBack;
    private TextView ButtonApply;
    private Bitmap bitmap,Processed;
    private static int smoothR=500;
    private static int  white=0;
    private static int smooth=300;
    private static int  whiteR=6;

    private final AmniXSkinSmooth amniXSkinSmooth = AmniXSkinSmooth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editactivity);
        menu=findViewById(R.id.controller);
        imageView=findViewById(R.id.imgPreview);
        ButtonBack=findViewById(R.id.btnBack);
        ButtonApply=findViewById(R.id.btnApply);
        myUri= Uri.parse(getIntent().getStringExtra("uri"));
        imageView.setImageURI(myUri);

        // convert uri to bitmap
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),myUri);


        } catch (IOException e) {
            e.printStackTrace();
        }


        initMenu();



        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(editactivity.this, dashbord.class);
                startActivity(intent);
                finish();
            }
        });




ButtonApply.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(editactivity.this, finaleditActivity.class);
        startActivity(intent);

    }
});


    }

    private void initMenu() {
        menu.addItem("Beauty skin",R.drawable.ic_filters2);
        menu.addItem("skin brightening",R.drawable.ic_brightness);
        menu.addItem("wrinkles ",R.drawable.wrinkless);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {


            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(editactivity.this,""+menuItem.getText(),Toast.LENGTH_SHORT).show();

                if(menuItem.getText()=="Beauty skin") {

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            amniXSkinSmooth.storeBitmap(bitmap, false);
                            amniXSkinSmooth.initSdk();
                            amniXSkinSmooth.startFullBeauty(smoothR, white);
                            amniXSkinSmooth.startSkinSmoothness(smoothR);
                            amniXSkinSmooth.startSkinWhiteness(white);
                            Processed = amniXSkinSmooth.getBitmapAndFree();
                            amniXSkinSmooth.unInitSdk();
                            return null;
                        }


                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            if (Processed != null) {
                                imageView.setImageBitmap(Processed);
                            } else {
                                Toast.makeText(editactivity.this, "Unable to Process!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute();
                }
if(menuItem.getText()=="skin brightening"){
    new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            amniXSkinSmooth.storeBitmap(bitmap, false);
            amniXSkinSmooth.initSdk();
            amniXSkinSmooth.startFullBeauty(smooth, whiteR);
            amniXSkinSmooth.startSkinSmoothness(smooth);
            amniXSkinSmooth.startSkinWhiteness(whiteR);
            Processed = amniXSkinSmooth.getBitmapAndFree();
            amniXSkinSmooth.unInitSdk();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (Processed != null) {
                imageView.setImageBitmap(Processed);
            } else {
                Toast.makeText(editactivity.this, "Unable to Process!", Toast.LENGTH_LONG).show();
            }
        }
    }.execute();
                   }


             }
         });

    }
}