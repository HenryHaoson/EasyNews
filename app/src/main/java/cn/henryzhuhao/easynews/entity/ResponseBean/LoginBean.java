package cn.henryzhuhao.easynews.entity.ResponseBean;

/**
 * Created by HenryZhuhao on 2017/6/22.
 */

public class LoginBean
{
    /**
     * data : {"id":1,"birthday":"1996-8-6","username":"m13814545863@163.com","sex":0,"nickname":"ben","imageUrl":"aaa","password":"123456","introduction":"就是帅"}
     * code : 200
     * msg : success
     */

    private DataBean data;
    private String code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * id : 1
         * birthday : 1996-8-6
         * username : m13814545863@163.com
         * sex : 0
         * nickname : ben
         * imageUrl : aaa
         * password : 123456
         * introduction : 就是帅
         */

        private int id;
        private String birthday;
        private String username;
        private int sex;
        private String nickname;
        private String imageUrl;
        private String password;
        private String introduction;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
