package cn.henryzhuhao.easynews.entity.ResponseBean;

import java.util.List;

/**
 * Created by HenryZhuhao on 2017/6/26.
 */

public class TopNewsList {

    /**
     * data : [{"id":1,"content":"你好","title":"你好","picurl2":" ","picurl1":" ","picurl3":" ","editorid":1},{"id":4,"content":"123123","title":"qqq","picurl1":"http://106.15.194.192:8080/baoliao/qqq5499369940.png","editorid":2},{"id":5,"content":"tytyty","title":"tyty","picurl1":"http://106.15.194.192:8080/baoliao/tyty5499369940.png","editorid":2},{"id":6,"content":"qwewqe1231232131221","title":"qwer","picurl1":"http://106.15.194.192:8080/baoliao/qwer5499369940.png","editorid":2},{"id":7,"content":"qwe","title":"qweqweqwe","picurl2":"http://106.15.194.192:8080/baoliao/qweqweqwe5499369941.png","picurl1":"http://106.15.194.192:8080/baoliao/qweqweqwe5499369940.png","picurl3":" ","editorid":2},{"id":8,"content":"zhen shuai\n\n","title":"moqian","picurl2":" ","picurl1":"http://106.15.194.192:8080/baoliao/moqian5499369940.png","picurl3":" ","editorid":2},{"id":9,"content":"666666666666","title":"6666","picurl2":" ","picurl1":"http://106.15.194.192:8080/baoliao/66665499369940.png","picurl3":" ","editorid":2},{"id":10,"content":"123123123","title":"123123","picurl2":"http://106.15.194.192:8080/baoliao/1231235499369941.png","picurl1":"http://106.15.194.192:8080/baoliao/1231235499369940.png","picurl3":" ","editorid":2},{"id":11,"content":"123123","title":"123123aaaa","picurl2":"http://106.15.194.192:8080/baoliao/123123aaaa5499369941.png","picurl1":"http://106.15.194.192:8080/baoliao/123123aaaa5499369940.png","picurl3":"http://106.15.194.192:8080/baoliao/123123aaaa5499369942.png","editorid":2}]
     * code : 200
     * msg : success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * picurl2 :
         * picurl1 :
         * picurl3 :
         * editorid : 1
         */

        private int id;
        private String content;
        private String title;
        private String picurl2;
        private String picurl1;
        private String picurl3;
        private int editorid;

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

        public String getPicurl2() {
            return picurl2;
        }

        public void setPicurl2(String picurl2) {
            this.picurl2 = picurl2;
        }

        public String getPicurl1() {
            return picurl1;
        }

        public void setPicurl1(String picurl1) {
            this.picurl1 = picurl1;
        }

        public String getPicurl3() {
            return picurl3;
        }

        public void setPicurl3(String picurl3) {
            this.picurl3 = picurl3;
        }

        public int getEditorid() {
            return editorid;
        }

        public void setEditorid(int editorid) {
            this.editorid = editorid;
        }
    }
}
