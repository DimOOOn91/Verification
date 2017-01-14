package com.example.dima.offline04_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 111;
    private static final String IMAGE_DIRECTORY = "TestPhoto";
    private Uri fileUri;

    private ImageView fullImage;
    private ImageView shortImage;
    private TextView fullImageData;
    private TextView shortImageData;
    private File mOutputMediaFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
            }
        });

        fullImage = (ImageView) findViewById(R.id.full_image);
        shortImage = (ImageView) findViewById(R.id.short_image);
        fullImageData = (TextView) findViewById(R.id.full_image_data);
        shortImageData = (TextView) findViewById(R.id.short_image_data);

    }

    private void captureImage() {
        Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
        fileUri = getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    public File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment.
                        getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "testPhoto");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("123", "Oooops! Field created");
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
    }

    private void previewCaptureImage() {
        try {
            Bitmap bitmapOriginal = BitmapFactory.decodeFile(fileUri.getPath());
            BitmapFactory.Options options = new BitmapFactory.Options();
            fullImage.setImageBitmap(bitmapOriginal);
            options.inSampleSize = 4;

//            Bitmap bitmapNoOriginal = BitmapFactory.decodeFile(fileUri.getPath(), options);
            Bitmap bitmapNoOriginal = Bitmap.createScaledBitmap(bitmapOriginal, 100, 100, false);
            shortImage.setImageBitmap(bitmapNoOriginal);
            fullImageData.setText("width: " + bitmapOriginal.getWidth() + ", height: " + bitmapOriginal.getHeight());
            shortImageData.setText("width: " + bitmapNoOriginal.getWidth() + ", height: " + bitmapNoOriginal.getHeight());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_CAPTURE_IMAGE_REQUEST_CODE:
                    previewCaptureImage();
                    break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            Toast.makeText(this, "CANCEL", Toast.LENGTH_SHORT).show();
        }
    }
}
