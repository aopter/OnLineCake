package com.lkl.dao;

import com.lkl.entity.Type;
import com.lkl.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2020/10/29 13:01
 * @projectName first
 * @className TypeDao.java
 * @description
 */
@Repository
public class TypeDao {
    @Autowired
    private DBUtil dbUtil;

    private List<Type> types = new ArrayList<>();
    private List<Type> sunTypes = new ArrayList<>();

    public void setDbUtil(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    /**
     * 更新类型
     *
     * @param type
     * @return
     */
    public int updateType(Type type) {
        int id = type.getTypeId();
        String name = type.getName();
        int pId = type.getParentType().getTypeId();
//		编写sql语句
        String sql = "update type  set name = '" + name + "', parentId = " + pId + " where id=" + id + "";
        System.out.println(sql);
        // 将信息插入表中
        int updateNum = -1;// 存储插入的记录数
        try {
            updateNum = dbUtil.updateData(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//		返回
        return updateNum;
    }

    /**
     * 得到一级类型
     *
     * @return
     */
    public List<Type> getFirstTypes() {
//        查询所有蛋糕类型
        types.clear();
        String sql = "select * from type where state = 1 and parentId = 0";
        ResultSet rs;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Type type = new Type();
                type.setTypeId(id);
                type.setName(name);
                types.add(type);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return types;
    }


    /**
     * 得到所有类型
     *
     * @return
     */
    public List<Type> getTypes() {
//        查询所有蛋糕类型
        types.clear();
        String sql = "select * from type where state = 1";
        ResultSet rs;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int parentId = rs.getInt("parentId");
                Type type = new Type();
                type.setTypeId(id);
                type.setName(name);
//				加入列表
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return types;
    }


    /**
     * 二级菜单
     *
     * @param parentId
     * @return
     */
    public List<Type> getSunTypes(int parentId) {
        sunTypes.clear();
        String sql = "select * from type where parentId = " + parentId + " and state = 1";
        ResultSet rs;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Type type = new Type();
                type.setTypeId(id);
                type.setName(name);
                sunTypes.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sunTypes;
    }

    /**
     * 删除类型
     *
     * @param typeId
     * @return
     */
    public int deleteCakeType(int typeId) {
//        得到子菜单  删除
        sunTypes.clear();
        sunTypes = getSunTypes(typeId);
//		编写sql语句
        String sql = "update type  set state = 0" + " where id=" + typeId + "";
        // 将信息插入表中
        int updateNum = -1;// 存储插入的记录数
        try {
            updateNum = dbUtil.updateData(sql);
            for (Type type : sunTypes) {
                String sql2 = "update type  set state = 0" + " where parentId=" + typeId + "";
                updateNum = dbUtil.updateData(sql);

                if (updateNum <= 0) {
                    return updateNum;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//		返回
        return updateNum;
    }

    /**
     * 添加类型
     *
     * @param name
     * @param parentId
     * @return
     */
    public int addType(String name, int parentId) {
        //		编写sql语句
        String sql = "insert into type(name,parentId) " + "values('"
                + name + "'," + parentId + ")";
        // 将信息插入表中
        int addNum = -1;// 存储插入的记录数
        try {
            addNum = dbUtil.addDataToTable(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //		返回
        return addNum;
    }

    /**
     * 根据id得到类型
     *
     * @param typeId
     * @return
     */
    public Type getCurrentType(int typeId) {
        Type type = new Type();
        String sql = "select * from type where id = " + typeId + " and state = 1";
        ResultSet rs;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int parentId = rs.getInt("parentId");
                Type pType = new Type();
                type.setTypeId(id);
                type.setName(name);
                pType.setTypeId(parentId);
                type.setParentType(pType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return type;

    }
}
