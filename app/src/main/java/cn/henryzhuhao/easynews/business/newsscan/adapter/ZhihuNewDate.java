package cn.henryzhuhao.easynews.business.newsscan.adapter;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewDate {
    private String id;
    private String picUrl;
    private String title;
    private String content;
    private String Date;


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
