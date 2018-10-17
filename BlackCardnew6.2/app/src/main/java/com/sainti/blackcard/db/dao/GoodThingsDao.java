package com.sainti.blackcard.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.helpter.DataBaseHelpter;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/29.
 */

public class GoodThingsDao {
    private static GoodThingsDao sInstance;
    /**********
     * 实际的真正的用户表操作对象
     *************/
    private Dao<GoodthingsBean, Integer> dao;

    private GoodThingsDao(Context context) {
        try {
            dao = DataBaseHelpter.getInstance(context).getDao(GoodthingsBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 单例模式
    public static GoodThingsDao getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GoodThingsDao(context);
        }
        return sInstance;
    }
    //3将对象添加到数据库中的用户表中

    public void add(GoodthingsBean s) {
        try {
            dao.create(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新当前对象属性
    public void update(String w_id, String count) {
        List<GoodthingsBean> list = queryForContent(w_id);
        try {
            if (list != null) {
                for (GoodthingsBean bean : list) {
                    bean.setTingsCount(count);
                    dao.update(bean);
                    //dao.createOrUpdate(bean);//和上一行的方法效果一样
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新当前对象属性
    public void updateChoice(String w_id, String code) {
        List<GoodthingsBean> list = queryForContent(w_id);
        try {
            if (list != null) {
                for (GoodthingsBean bean : list) {
                    bean.setIsChoice(code);
                    dao.update(bean);
                    //dao.createOrUpdate(bean);//和上一行的方法效果一样
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新支付状态对象 转换支付订单状态
    public void updatePay(List<GoodthingsBean> list, String code) {
        //  List<GoodthingsBean> list = queryForContent(w_id);
        try {
            if (list != null) {
                for (GoodthingsBean bean : list) {
                    bean.setIsPay(code);
                    dao.update(bean);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1,查询所有数据
     */

    public List<GoodthingsBean> quaryAll()

    {
        QueryBuilder<GoodthingsBean, Integer> qb = dao.queryBuilder();
        try {
            return qb.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据好物id + 好物分类id  拼接成 去查数据库里是否有当前商品
    public List<GoodthingsBean> queryForContent(String content) {
        // 获得查询器
        QueryBuilder<GoodthingsBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<GoodthingsBean, Integer> where = qb.where();
        try {
            where.eq("tb_thingsId", content);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    // 根据好物id 去查数据库里是否有当前商品
    public List<GoodthingsBean> queryForPayState(String code) {
        // 获得查询器
        QueryBuilder<GoodthingsBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<GoodthingsBean, Integer> where = qb.where();
        try {
            where.eq("tb_isPay", code);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    // 根据是否被选择结算
    public List<GoodthingsBean> queryForChoice(String choice) {
        // 获得查询器
        QueryBuilder<GoodthingsBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<GoodthingsBean, Integer> where = qb.where();
        try {
            where.eq("tb_isChoice", choice);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据内容查询内容相同的用户
    public List<GoodthingsBean> queryForTwoContent(String content, String kind) {
        // 获得查询器
        QueryBuilder<GoodthingsBean, Integer> qb = dao.queryBuilder();
        // 获得条件对象
        Where<GoodthingsBean, Integer> where = qb.where();
        try {
            where.eq("tb_name", content).and().eq("tb_kind", kind);
            qb.setWhere(where);// 重新设置条件
            return qb.query();// 根据条件进行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    // 通过内容删除单个数据
    public void deleteOne(String content) {
        try {
            dao.delete(queryForContent(content));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOneForTwo(String content, String kind) {
        try {
            dao.delete(queryForTwoContent(content, kind));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteForTwo(String name, String kind) {
        try {
            dao.delete(queryForTwoContent(name, kind));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 删除所有数据

    public void deleteAll() {
        try {
            dao.delete(quaryAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
