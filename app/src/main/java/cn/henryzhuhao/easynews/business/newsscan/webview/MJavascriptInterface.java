package cn.henryzhuhao.easynews.business.newsscan.webview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HenryZhuhao on 2017/6/13.
 */

public class MJavascriptInterface {
    private Context context;
    private String[] imageUrls;

    //    public MJavascriptInterface(Context context,String[] imageUrls) {
//        this.context = context;
//        this.imageUrls = imageUrls;
//    }
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        Intent intent = new Intent();
        intent.putExtra("imageUrls", imageUrls);
        intent.putExtra("curImageUrl", img);
        context.startActivity(intent);
    }

    public static void getFirstImg(String content, String[] imageUrls) {

        if (content.contains("<img")) {

            String img = "";
            Pattern p_image;
            Matcher m_image;
            // List<String> pics = new ArrayList<String>();
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
            m_image = p_image.matcher(content);
            while (m_image.find()) {
                img = img + "," + m_image.group();
                // Matcher m =
                // Pattern.compile("src=http://my.oschina.net/"?(.*?)(\"|>|\\s+)").matcher(img);
                // //匹配src

                Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
                        .matcher(img);

                while (m.find()) {
                    // pics.add(m.group(1));
                    Log.e("tag", m.group(1));
                }
            }
        }
        return;
    }

    public String[] getImgs(String content) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String str = "";
        String[] images = null;
        String regEx_img = "(<img.*srcs*=s*(.*?)[^>]*?>)";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(content);
        while (m_image.find()) {
            img = m_image.group();
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
                    .matcher(img);
            while (m.find()) {
                String tempSelected = m.group(1);
                if ("".equals(str)) {
                    str = tempSelected;
                } else {
                    String temp = tempSelected;
                    str = str + "," + temp;
                }
            }
        }
        if (!"".equals(str)) {
            images = str.split(",");
        }
        return images;
    }
}