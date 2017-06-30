package cn.henryzhuhao.easynews.business.newsscan;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.henryzhuhao.easynews.MainActivity;
import cn.henryzhuhao.easynews.R;
import cn.henryzhuhao.easynews.business.newsscan.adapter.ZhihuNewsBody;
import cn.henryzhuhao.easynews.business.newsscan.presenter.ZhihuNewsBodyPresenter;
import cn.henryzhuhao.easynews.business.newsscan.view.ZhihuNewsBodyView;
import cn.henryzhuhao.mainframe.frame.base.BaseFragment;
import cn.henryzhuhao.mainframe.imageLoader.ImageLoader;
import cn.henryzhuhao.mainframe.view.photoview.HphotoFragment;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsBodyFragment extends BaseFragment implements ZhihuNewsBodyView{
    public static final String BUNDLE_KEY_NEWS_ID="bundle.key.newsId";
    public Toolbar toolbar;
    public WebView webView;
    public ImageView articalToolbarPic;
    public ZhihuNewsBodyPresenter presenter;
    public String htmlbody;
    public static String mId;
    public static String mTitle;
    public static String mPicUrl;
    public static String mTransitionName;
    public String css = "<style type=\"text/css\"> img {" +
            "max-width:100%;" +//限定图片宽度填充屏幕
            "height:auto;" +//限定图片高度自动
            "}" +
            "body {" +
            "margin-right:15px;" +//限定网页中的文字右边距为15px(可根据实际需要进行行管屏幕适配操作)
            "margin-left:15px;" +//限定网页中的文字左边距为15px(可根据实际需要进行行管屏幕适配操作)
            "margin-top:15px;" +//限定网页中的文字上边距为15px(可根据实际需要进行行管屏幕适配操作)
           // "font-size:40px;" +//限定网页中文字的大小为40px,请务必根据各种屏幕分辨率进行适配更改
            "word-wrap:break-word;"+//允许自动换行(汉字网页应该不需要这一属性,这个用来强制英文单词换行,类似于word/wps中的西文换行)
            "}" +
            "</style>";

    public String js="<script>\n" +
            "        function sayHello()\n" +
            "        {\n" +
            "             alert(\"Hello\")\n" +
            "         }\n" +
            "         var aTag = document.getElementsByTagName('img')[0];\n" +
            "         aTag.addEventListener('click', function(){\n" +
            "        //调用android本地方法\n" +
            "            control.startPhotoView();\n" +
            "             return false;\n" +
            "          }, false);\n" +
            "    </script>";

    public static ZhihuNewsBodyFragment newInstance(String id,String title,String picUrl,String transitonName) {
        
        Bundle args = new Bundle();

        mId=id;
        mPicUrl=picUrl;
        mTitle=title;
        mTransitionName=transitonName;
        ZhihuNewsBodyFragment fragment = new ZhihuNewsBodyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ///////////////////////////////////////////////////////



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
    }
    @Override
    public void initView() {
        webView = (WebView) view.findViewById(R.id.web_zhihu_body);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//适应内容大小
        webView.getSettings().setJavaScriptEnabled(true);
        //设置本地调用对象及其接口
        webView.addJavascriptInterface(new JsInteraction(), "control");
        toolbar= (Toolbar) view.findViewById(R.id.artical_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
        articalToolbarPic= (ImageView) view.findViewById(R.id.artical_toolbar_Pic);
//        articalToolbarPic.setTransitionName(mTransitionName);
        ImageLoader imageLoader=ImageLoader.build(getContext());
        imageLoader.bindBitmap(mPicUrl,articalToolbarPic);
        //id= App.getInstance().getZhihuId();

        presenter=new ZhihuNewsBodyPresenter(this);
        presenter.getZhihuNewsBody(mId);



    }

    public class JsInteraction {
        @JavascriptInterface
        public void startPhotoView() {   //提供给js调用的方法
            ((MainActivity)getActivity()).startfragment(R.id.activity_container, HphotoFragment.newInstance(getImgs(htmlbody),0));

        }
    }
    public ArrayList<String> getImgs(String content) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String str = "";
        ArrayList<String> images = new ArrayList<>();
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
//            images.add(str.split(","));
            for(int i=0;i<str.split(",").length;i++){
                images.add(str.split(",")[i]);
            }
        }
        return images;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void hideContentView() {

    }

    @Override
    public void showLoadingContentView() {

    }

    @Override
    public void removeLoadingContentView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_zhihunewsbody;
    }

    @Override
    public void loadSuccess(final ZhihuNewsBody body) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(mTitle);
                htmlbody=body.getBody();
                webView.loadDataWithBaseURL(null, body.getBody()+css+js, "text/html", "utf-8", null);
            }
        });

    }



}
