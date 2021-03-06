package com.sainti.blackcard.db.dao;


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.sainti.blackcard.db.bean.CoffeeCountBean;
import com.sainti.blackcard.db.helpter.DataBaseHelpter;

import java.sql.SQLException;
import java.util.List;

public class CoffeeCountDao {
    private static CoffeeCountDao sInstance;
    /**********
     * 实际的真正的用户表操作对象
     *************/
    private Dao<CoffeeCountBean, Integer> dao;

    private CoffeeCountDao(Context context) {
        try {
            dao = DataBaseHelpter.getInstance(context).getDao(CoffeeCountBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 单例模式
    public static CoffeeCountDao getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CoffeeCountDao(context);
        }
        return sInstance;
    }
    //3将对象添加到数据库中的用户表中

    public void add(CoffeeCountBean s) {
        try {
            dao.create(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1,查询所有数据
     */

    public List<CoffeeCountBean> quaryAll()

    {
        QueryBuilder<CoffeeCountBean, Integer> qb = dao.queryBuilder();
        // qb.orderBy("tb_time", false);
        try {
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据内容查询内容相同的用户
    public List<CoffeeCountBean> queryForContent(String content) {
        // 获得查询器
        QueryBuilder<CoffeeCountBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<CoffeeCountBean, Integer> where = qb.where();
        try {
            where.eq("tb_name", content);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*// 根据内容查询内容相同的用户
    public List<CoffeeCountBean> queryForTwoContent(String content, String kind) {
        // 获得查询器
        QueryBuilder<CoffeeCountBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<CoffeeCountBean, Integer> where = qb.where();
        try {
            where.eq("tb_name", content).and().eq("tb_kind", kind);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }*/

    // 通过内容删除单个数据
    public void deleteOne(String content) {
        try {
            dao.delete(queryForContent(content));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* public void deleteForTwo(String name, String kind) {
        try {
            dao.delete(queryForTwoContent(name, kind));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    // 删除所有数据

    public void deleteAll() {
        try {
            dao.delete(quaryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
