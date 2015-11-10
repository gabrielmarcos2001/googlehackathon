package com.codesmore.codesmore.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraUtils {

    public static File createImageFile() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timestamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        try {
            return File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap validatePictureOrientation(Context context, Bitmap input, Uri imageUri) {
        int orientation = getExifOrientation(imageUri);

        if (orientation == ExifInterface.ORIENTATION_UNDEFINED) {
            orientation = getMediaStoreOrientation(context, imageUri);
        }

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                input = rotateImage(input, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                input = rotateImage(input, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                input = rotateImage(input, 270);
                break;
        }

        return input;
    }

    private static int getExifOrientation(Uri imageUri) {
        try {
            ExifInterface ei = new ExifInterface(imageUri.getPath());
            return ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException ignored) {
        }

        return ExifInterface.ORIENTATION_UNDEFINED;
    }

    private static int getMediaStoreOrientation(Context context, Uri imageUri) {
        String[] orientationColumn = {MediaStore.Images.Media.ORIENTATION};
        Cursor cursor = null;
        int orientation = ExifInterface.ORIENTATION_UNDEFINED;
        try {
            cursor = context.getContentResolver().query(imageUri, orientationColumn, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                orientation = cursor.getInt(cursor.getColumnIndex(orientationColumn[0]));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return orientation;
    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bitmap;
    }
}
