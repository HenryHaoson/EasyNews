package cn.henryzhuhao.easynews.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.TimeUnit;

import cn.henryzhuhao.easynews.gen.DaoMaster;
import cn.henryzhuhao.easynews.gen.DaoSession;
import okhttp3.OkHttpClient;

/**
 * Created by HenryZhuhao on 2017/4/7.
 */

public class App extends Application {
    private static App instance;
    public OkHttpClient okHttpClient;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public String ZhihuId;

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
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        initOkHttp();
        setDatabase();
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
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "easyschool-db1", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
