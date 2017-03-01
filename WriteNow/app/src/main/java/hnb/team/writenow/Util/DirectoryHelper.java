package hnb.team.writenow.Util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaehoonjung on 2017. 2. 26..
 */

public class DirectoryHelper {

    private static final String CUSTOM_FOLDER_NAME = "/custom";

    public static String getCustomFolderPath() {

        String hnbFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hnb";

        boolean result = true;

        File hnbFolder = new File(hnbFolderPath);

        if (!hnbFolder.exists()){

            result = hnbFolder.mkdir();

            if(result){

                String lastCustomFolderPath = hnbFolderPath + CUSTOM_FOLDER_NAME + 0;
                File lastCustomFolder = new File(lastCustomFolderPath);

                if (!lastCustomFolder.exists()){
                    result = lastCustomFolder.mkdir();
                    return result ? lastCustomFolderPath : "failed to make 0 folder";
                }
            }
        }else{

            int customFolderCount = hnbFolder.listFiles().length;

            String lastCustomFolderPath = hnbFolderPath + CUSTOM_FOLDER_NAME;

            File lastCustomFolder = new File(lastCustomFolderPath);

            if (!lastCustomFolder.exists()){
                result = lastCustomFolder.mkdir();
                return result ? lastCustomFolderPath : "failed to make custom folder";
            }else{
                if(lastCustomFolder.listFiles().length == 0){
                    return lastCustomFolderPath + customFolderCount;
                }else{
                    return lastCustomFolderPath + (customFolderCount + 1);
                }
            }

        }
        return "failed to load save path";

    }

    public static List<String> getFileToFolderPath(String customFolderPath){

        boolean result = true;

        File searchFolder = new File(customFolderPath);

        if (!searchFolder.exists())
            result = searchFolder.mkdir();

        List<String> filePaths = new ArrayList<String>();

        if(result){
            for(int i = 0; i < searchFolder.listFiles().length; i++){
                filePaths.add(searchFolder.listFiles()[i].getAbsolutePath());

                Log.i("DIRECTORY HELPER", searchFolder.listFiles()[i].getAbsolutePath() + " ::: " + searchFolder.listFiles()[i].getName());

            }
        }

        return filePaths;
    }

}
