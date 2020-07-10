package com.example.perfectface.Module;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;

import androidx.annotation.NonNull;
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


import java.util.List;


public class faceDetection {
    public FirebaseVisionImage image;
public void faceDetector(Bitmap bitmap){

         image=FirebaseVisionImage.fromBitmap(bitmap);

         Bitmap mutableBitmap=bitmap.copy(Bitmap.Config.ARGB_8888,true);
    final Canvas canvas=new Canvas(mutableBitmap );

    FirebaseVisionFaceDetectorOptions options =
            new FirebaseVisionFaceDetectorOptions.Builder()
                    .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                    .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                    .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                    .build();
    FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
            .getVisionFaceDetector(options);

    Task<List<FirebaseVisionFace>> result =
            detector.detectInImage(image)
                    .addOnSuccessListener(
                            new OnSuccessListener<List<FirebaseVisionFace>>() {
                                @Override
                                public void onSuccess(List<FirebaseVisionFace> faces) {
                                    // Task completed successfully
                                    // ...
                                    for (FirebaseVisionFace face : faces) {
                                        Rect bounds = face.getBoundingBox();
                                        Paint paint=new Paint();
                                        paint.setColor(Color.GREEN);
                                        paint.setStyle(Paint.Style.STROKE);
                                        canvas.drawRect(bounds,paint);
                                        float rotY = face.getHeadEulerAngleY();  // Head is rotated to the right rotY degrees
                                        float rotZ = face.getHeadEulerAngleZ();  // Head is tilted sideways rotZ degrees

                                        // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
                                        // nose available):
                                        FirebaseVisionFaceLandmark leftEar = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EAR);
                                        if (leftEar != null) {
                                            FirebaseVisionPoint leftEarPos = leftEar.getPosition();
                                        }

                                        // If contour detection was enabled:
                                        List<FirebaseVisionPoint> leftEyeContour =
                                                face.getContour(FirebaseVisionFaceContour.LEFT_EYE).getPoints();
                                        List<FirebaseVisionPoint> upperLipBottomContour =
                                                face.getContour(FirebaseVisionFaceContour.UPPER_LIP_BOTTOM).getPoints();

                                        // If classification was enabled:
                                        if (face.getSmilingProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                            float smileProb = face.getSmilingProbability();
                                        }
                                        if (face.getRightEyeOpenProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                            float rightEyeOpenProb = face.getRightEyeOpenProbability();
                                        }

                                        // If face tracking was enabled:
                                        if (face.getTrackingId() != FirebaseVisionFace.INVALID_ID) {
                                            int id = face.getTrackingId();
                                        }
                                    }
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Task failed with an exception
                                    // ...
                                }
                            });


}
}
