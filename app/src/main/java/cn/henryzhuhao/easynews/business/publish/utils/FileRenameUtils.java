package cn.henryzhuhao.easynews.business.publish.utils;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by HenryZhuhao on 2017/6/27.
 */

public class FileRenameUtils {
    public static ArrayList<String> fileRename(ArrayList<String> imageUrls){
        ArrayList<String> retList=new ArrayList<>();
        for (int i = 0; i <imageUrls.size() ; i++) {
            Log.e("upload","imageUrl"+imageUrls.get(i));
            String s[]=imageUrls.get(i).split("/");
            String filename=s[s.length-1];
            Log.e("upload","list"+filename);
            retList.add(filename);
        }
        return retList;
    }
}
