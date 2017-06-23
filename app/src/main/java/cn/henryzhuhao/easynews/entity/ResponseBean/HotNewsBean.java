package cn.henryzhuhao.easynews.entity.ResponseBean;

import java.util.List;

/**
 * Created by HenryZhuhao on 2017/6/23.
 */

public class HotNewsBean {

    /**
     * data : [{"id":1,"content":"你好","title":"你好","editorid":1,"picurl1":"","picurl2":"","picurl3":""}]
     * code : 200
     * msg : success
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * content : 你好
         * title : 你好
         * editorid : 1
         * picurl1 :
         * picurl2 :
         * picurl3 :
         */

        private int id;
        private String content;
        private String title;
        private int editorid;
        private String picurl1;
        private String picurl2;
        private String picurl3;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getEditorid() {
            return editorid;
        }

        public void setEditorid(int editorid) {
            this.editorid = editorid;
        }

        public String getPicurl1() {
            return picurl1;
        }

        public void setPicurl1(String picurl1) {
            this.picurl1 = picurl1;
        }

        public String getPicurl2() {
            return picurl2;
        }

        public void setPicurl2(String picurl2) {
            this.picurl2 = picurl2;
        }

        public String getPicurl3() {
            return picurl3;
        }

        public void setPicurl3(String picurl3) {
            this.picurl3 = picurl3;
        }
    }
}
