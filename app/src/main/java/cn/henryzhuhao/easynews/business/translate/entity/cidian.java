package cn.henryzhuhao.easynews.business.translate.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by HenryZhuhao on 2017/6/5.
 */
@Entity
public class Cidian {
    @Id(autoincrement = true)
    private Long id;
    public String ch;
    public String en;
    @Generated(hash = 1498192235)
    public Cidian(Long id, String ch, String en) {
        this.id = id;
        this.ch = ch;
        this.en = en;
    }
    @Generated(hash = 1861721697)
    public Cidian() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCh() {
        return this.ch;
    }
    public void setCh(String ch) {
        this.ch = ch;
    }
    public String getEn() {
        return this.en;
    }
    public void setEn(String en) {
        this.en = en;
    }




}
