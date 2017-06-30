package cn.henryzhuhao.easynews.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import cn.henryzhuhao.easynews.gen.DaoMaster;
import cn.henryzhuhao.easynews.gen.DaoSession;
import cn.henryzhuhao.mainframe.utils.SPUtils;
import okhttp3.OkHttpClient;

import static cn.henryzhuhao.easynews.app.AppContants.LOGINSTATUS_LOGOUT;
import static cn.henryzhuhao.easynews.app.AppContants.SP_LOGINSTATUS;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class App extends Application {
    private static App instance;
    private int LoginStatus;
    public OkHttpClient okHttpClient;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private int userId;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public String ZhihuId;

    public String headImageUrl;

    public String getZhihuId() {
        return ZhihuId;
    }

    public void setZhihuId(String zhihuId) {
        ZhihuId = zhihuId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        initOkHttp();
        setDatabase();
        LoginStatus= (int) SPUtils.get(getApplicationContext(),AppContants.SP_LOGINSTATUS, LOGINSTATUS_LOGOUT);
        if(LoginStatus==AppContants.LOGINSTATUS_LOGIN){
            userId= (int) SPUtils.get(getApplicationContext(),"userId",1);
            headImageUrl= (String) SPUtils.get(getApplicationContext(),"headimage","/storage/emulated/0/Pictures/Screenshots/Screenshot_20170623-144951.png");
        }

    }
    private void initOkHttp() {
        okhttp3.OkHttpClient.Builder ClientBuilder=new okhttp3.OkHttpClient.Builder();
        ClientBuilder.readTimeout(30, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(10, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        okHttpClient=ClientBuilder.build();
    }
    public static App getInstance() {
        return instance;
    }
    /**
     * 设置greenDao
     */
    private void setDatabase() {

        mHelper = new DaoMaster.DevOpenHelper(this, "easyschool-db1", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public int getLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        LoginStatus = loginStatus;
        SPUtils.put(getApplicationContext(), SP_LOGINSTATUS,loginStatus);
    }
    public int getUser() {
        return userId;
    }

    public void setUser(int user) {
        this.userId = user;
        SPUtils.put(getApplicationContext(),"userId",user);
    }
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
        SPUtils.put(getApplicationContext(),"headimage",headImageUrl);
    }
}
