package com.example.kacent.mytaksdemo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kacent on 2016/3/1.
 */
public class LoadImage {
    public ImageView imageVIew;
    public String url;

    public LoadImage(ImageView imageVIew, String url) {
        this.imageVIew = imageVIew;
        this.url = url;
    }

    public Bitmap loadImageUrl(String url) {
        InputStream is;
        URLConnection connection;
        try {
            connection = new URL(url).openConnection();
            is = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void ShowByTask(String url) {
        ImageTask task = new ImageTask();
        task.execute(url);

    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return loadImageUrl(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageVIew.getTag().equals(url)) {
                imageVIew.setImageBitmap(bitmap);
            }
        }
    }
}
