package hnb.team.writenow.Util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import hnb.team.writenow.Interface.FileSaveListener;

public class AsyncSaveToImage extends AsyncTask<Bitmap, Integer, String>{

    private Context context;

    private FileSaveListener fileSaveListener;

    private AsyncSaveToImage(Context context, FileSaveListener fileSaveListener){
        this.context = context;
        this.fileSaveListener = fileSaveListener;
    }

    public static void startSaveToImage(Bitmap bitmap, Context context, FileSaveListener fileSaveListener){
        AsyncSaveToImage asyncSaveToImage = new AsyncSaveToImage(context, fileSaveListener);
        asyncSaveToImage.execute(bitmap);
    }

    @Override
    protected String doInBackground(Bitmap... params) {
        Log.i("IMAGE SAVE ASYNC", "ASYNC START");

        excuteSaveCard(params[0]);
        return null;
    }

    @Override
    protected void onCancelled(String s) {
        Log.i("IMAGE SAVE ASYNC", "ASYNC CANCEL");
        super.onCancelled(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        fileSaveListener.onCompleteFileSave(s);
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    public void excuteSaveCard(Bitmap saveBitmap) {
        boolean result = true;

        String hnbFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hnb";

        String saveImagePath = hnbFolderPath;

        File hnbFolder = new File(hnbFolderPath);

        if (!hnbFolder.exists())
            result = hnbFolder.mkdir();

        if (!result) {
            onCancelled("failed folder create");
        }

        if (!result) return;

        int fileCounts = hnbFolder.listFiles().length;

        saveImagePath += "/cardImage"+ ++fileCounts + ".jpg";

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(saveImagePath);
            saveBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException nfe) {
            Log.d("FileNotFoundException:", nfe.getMessage());
            onCancelled("File NOTFOUND");
        } finally {
            try {
                if (out != null) out.close();
                onPostExecute(saveImagePath);

//                MediaScanner.newInstance(context).mediaScanning(hnbFolderPath);

                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + saveImagePath)));

            } catch (IOException ioe) {
                onCancelled("COMPLETE FAIL");
            }
        }
        //saveBitmap.recycle();
    }




}
