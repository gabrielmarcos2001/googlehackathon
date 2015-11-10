package com.codesmore.codesmore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.codesmore.codesmore.utils.CameraUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class BaseActivityWithImageSaving extends BaseActivity {

    public static final int IMG_WIDTH = 300;
    public static final int IMG_HEIGHT = 300;

    /**
     * Overwrite if the Activity is interested in image capturing.
     * @param captured The image that was just captured.
     */
    public abstract void onImageCaptured(Bitmap captured);

    private static final int REQUEST_CODE_CHOOSE_IMAGE = 22100;
    private static final String KEY_IMAGE_URI = "imageUri";
    private static final String KEY_IMAGE_RAW_DATA = "imageRawData";

    /** User-chosen image location */
    private Uri mImageUri;
    private byte[] mImageByteData;

    /**
     * Create an Image choosing prompt that includes:
     *
     * - choose from Gallery
     * - take picture (only if the device has a camera)
     */
    public void startImageChooser() {
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.choose_picture_title));

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            mImageUri = Uri.fromFile(CameraUtils.createImageFile());
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { takePictureIntent });
        }

        startActivityForResult(chooserIntent, REQUEST_CODE_CHOOSE_IMAGE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Be sure to keep the selected image.
        outState.putParcelable(KEY_IMAGE_URI, mImageUri);
        outState.putByteArray(KEY_IMAGE_RAW_DATA, mImageByteData);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore chosen image if any.
        mImageUri = savedInstanceState.getParcelable(KEY_IMAGE_URI);
        mImageByteData = savedInstanceState.getByteArray(KEY_IMAGE_RAW_DATA);

        if (mImageByteData != null) {
            restoreImageFromByteData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            final boolean isCameraImage =
                    (data == null) || MediaStore.ACTION_IMAGE_CAPTURE.equals(data.getAction());

            Bitmap bitmap = null;

            Uri imageUri = isCameraImage ? mImageUri : data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                // Bitmap not found, will be found later.
            }

            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMG_WIDTH, IMG_HEIGHT, false);
            if (scaled != null) {
                scaled = CameraUtils.ensureGenuinePictureOrientation(this, scaled, imageUri);
                onImageCaptured(scaled);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                scaled.compress(Bitmap.CompressFormat.PNG, 90, bytes);
                mImageByteData = bytes.toByteArray();
            }
        }
    }

    private void restoreImageFromByteData() {
        Bitmap image = BitmapFactory.decodeByteArray(mImageByteData, 0, mImageByteData.length);
        if (image != null) {
            onImageCaptured(image);
        }
    }

}
