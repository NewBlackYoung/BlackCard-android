package com.sainti.blackcard.db.helpter;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sainti.blackcard.db.bean.CoffeeCountBean;
import com.sainti.blackcard.db.bean.CoffeeLookBean;
import com.sainti.blackcard.db.bean.GoodthingsBean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
* 2.数据库管理类
* */
public class DataBaseHelpter extends OrmLiteSqliteOpenHelper {
    // 数据库的名称
    public static final String DATABASE_NAME = "qingnianheika.db";
    // 数据库版本
    public static final int DATABASE_VERSON = 5;


    // 本类的单体实例
    private static DataBaseHelpter instance;
    //存储APP 所有的DAO对象的集合
    private Map<String, Dao> daos = new HashMap<>();

    private DataBaseHelpter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSON);
    }

    public DataBaseHelpter(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    // 获取本类单例对象的方法
    public static synchronized DataBaseHelpter getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelpter.class) {
                if (instance == null) {
                    instance = new DataBaseHelpter(context);
                }
            }
        }
        return instance;
    }

    // 根据传入的DAO的路径获取到这个DAO的单例对象（要么从daos这个Map中获取，要么新创建一个并存入daos）
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 第一次创建数据库时会回调的方法
     **/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, CoffeeLookBean.class);//创建表
            TableUtils.createTable(connectionSource, CoffeeCountBean.class);//创建表
            TableUtils.createTable(connectionSource, GoodthingsBean.class);//创建表
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        super.close();
        for (String s : daos.keySet()) {
            Dao dao = daos.get(s);
            dao = null;
        }
    }


    /**
     * 数据库版本发生变化时会回调的方法 一般指的是数据库版本升级时会回调的方法
     **/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, CoffeeLookBean.class, true);//删除表
            TableUtils.createTable(connectionSource, CoffeeLookBean.class);//   创建表
            TableUtils.dropTable(connectionSource, CoffeeCountBean.class, true);//删除表
            TableUtils.createTable(connectionSource, CoffeeCountBean.class);//   创建表
            TableUtils.dropTable(connectionSource, GoodthingsBean.class, true);//删除表
            TableUtils.createTable(connectionSource, GoodthingsBean.class);//创建表

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
