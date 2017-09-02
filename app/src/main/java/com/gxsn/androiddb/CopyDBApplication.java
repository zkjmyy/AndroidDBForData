package com.gxsn.androiddb;

import android.app.Application;

/**
 * Created by zkj on 2017/08/30
 * AndroidDB
 */

public class CopyDBApplication extends Application {

    //该路径也可以自定义到自己的想要的路径下面。但是外部存储卡的存储是不安全的；
    public static String DBPATH = "";

    public static String DBNAME = "BasicDB.db";
    /**
     * 本地存储全路径名目录
     */
    public static String CACHE_DIR;

    /**
     * 本地存储文件夹名
     */
    public static final String CACHE_DIR_NAME = "您自己定义的路径";

    @Override
    public void onCreate() {
        super.onCreate();
        //当前应用的私有目录下；
        DBPATH = "/data/data/" + this.getPackageName() + "/databases/";

        //该段代码是若想要定义到外部路径下；有些应用是需要定义在外部。使用比较方便
//        if (Environment.MEDIA_MOUNTED.equals(Environment
//                .getExternalStorageState())) {
//            CACHE_DIR = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + "/" + CACHE_DIR_NAME;
//        } else {
//            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/"
//                    + CACHE_DIR_NAME;
//        }

    }
}
