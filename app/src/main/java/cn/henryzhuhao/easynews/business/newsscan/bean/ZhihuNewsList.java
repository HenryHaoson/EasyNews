package cn.henryzhuhao.easynews.business.newsscan.bean;

import java.util.List;

/**
 * Created by HenryZhuhao on 2017/4/10.
 */

public class ZhihuNewsList {
    //http://news-at.zhihu.com/api/3/news/latest
    /**
     * date : 20170410
     * stories : [{"images":["https://pic4.zhimg.com/v2-4785b17b86721ab1916e1356b6d589cb.jpg"],"type":0,"id":9347216,"ga_prefix":"041017","title":"「看着这幅画，我想起了电影里的情节」"},{"images":["https://pic1.zhimg.com/v2-d465d1c1d5c021f9188f7c7f4b2faee0.jpg"],"type":0,"id":9349116,"ga_prefix":"041016","title":"首个共享单车公司要上市了，却不是你常骑的那两家"},{"images":["https://pic2.zhimg.com/v2-5d620477f39d9f3367442a11f2d228d1.jpg"],"type":0,"id":9347708,"ga_prefix":"041015","title":"「春药」这种东西真的存在吗？"},{"title":"柯洁对战 AlphaGo，手中的这一招可能是人类最后的机会（多图）","ga_prefix":"041014","images":["https://pic4.zhimg.com/v2-2f90d0ece99a1852764029f0153797b7.jpg"],"multipic":true,"type":0,"id":9348673},{"images":["https://pic1.zhimg.com/v2-f23283645f86245a656e89938505ed40.jpg"],"type":0,"id":9346434,"ga_prefix":"041013","title":"为什么宇宙是 11 维，而不是 12 或 13 维？"},{"images":["https://pic3.zhimg.com/v2-09aa7f508c62897e786d8ebfba7d117a.jpg"],"type":0,"id":9347484,"ga_prefix":"041012","title":"大误 · 嫦娥对羿的爱，我们恐怕都不理解"},{"images":["https://pic1.zhimg.com/v2-82c28d2f2188a53d98211888d468cc0c.jpg"],"type":0,"id":9347253,"ga_prefix":"041011","title":"过去两个月里，世界经济发生了哪些变化？"},{"title":"表面布满裂纹的饼干，吃起来酥脆香甜（多图）","ga_prefix":"041010","images":["https://pic3.zhimg.com/v2-32699e6d2ae33ea1c4b4da747e9ad2ea.jpg"],"multipic":true,"type":0,"id":9347739},{"images":["https://pic1.zhimg.com/v2-5453bba17f9bed4bdf82b2d7a2b3b0a0.jpg"],"type":0,"id":9347676,"ga_prefix":"041009","title":"没有工作怎么会幸福呢？嗯\u2026\u2026我可以打游戏啊"},{"title":"无论怎样形容南极，都比不上一句：「若不是亲眼所见」（多图）","ga_prefix":"041008","images":["https://pic2.zhimg.com/v2-a7765d1109c9865a4c96e476c51815a9.jpg"],"multipic":true,"type":0,"id":9347100},{"images":["https://pic1.zhimg.com/v2-f26330bf13525ed21cf5a81e26599090.jpg"],"type":0,"id":9346661,"ga_prefix":"041007","title":"常出现在签名档的 MBTI 的测试，其实早就被学界抛弃了"},{"images":["https://pic4.zhimg.com/v2-125c030cdd854e07af42903a10f64463.jpg"],"type":0,"id":9347686,"ga_prefix":"041007","title":"手机里的很多 app 都请了大明星代言，这在过去可不常见"},{"images":["https://pic2.zhimg.com/v2-899419634502aa31036e3479ab77b61d.jpg"],"type":0,"id":9337747,"ga_prefix":"041007","title":"未来给你做手术的，会有张医生、李医生和人工智能医生"},{"images":["https://pic4.zhimg.com/v2-d4c9c60c23bab741f5f4605268b7f71b.jpg"],"type":0,"id":9347635,"ga_prefix":"041006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-ae523a09ebac794ac6e580517c54db2a.jpg","type":0,"id":9348673,"ga_prefix":"041014","title":"柯洁对战 AlphaGo，手中的这一招可能是人类最后的机会"},{"image":"https://pic3.zhimg.com/v2-108086f42db1296656f3e745b2e2d5e6.jpg","type":0,"id":9347708,"ga_prefix":"041015","title":"「春药」这种东西真的存在吗？"},{"image":"https://pic3.zhimg.com/v2-e394bd233c8dc6438ff7a7df6903ed4a.jpg","type":0,"id":9346661,"ga_prefix":"041007","title":"常出现在签名档的 MBTI 的测试，其实早就被学界抛弃了"},{"image":"https://pic4.zhimg.com/v2-33e2fbee6f82a7201135bca0d91623eb.jpg","type":0,"id":9347686,"ga_prefix":"041007","title":"手机里的很多 app 都请了大明星代言，这在过去可不常见"},{"image":"https://pic1.zhimg.com/v2-692fa246e3829526303a76b67f27ef34.jpg","type":0,"id":9344819,"ga_prefix":"040908","title":"红肉致癌的说法，罪魁祸首是「铁」吗？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-4785b17b86721ab1916e1356b6d589cb.jpg"]
         * type : 0
         * id : 9347216
         * ga_prefix : 041017
         * title : 「看着这幅画，我想起了电影里的情节」
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-ae523a09ebac794ac6e580517c54db2a.jpg
         * type : 0
         * id : 9348673
         * ga_prefix : 041014
         * title : 柯洁对战 AlphaGo，手中的这一招可能是人类最后的机会
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
