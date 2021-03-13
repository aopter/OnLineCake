package com.lkl.dao;

import com.lkl.entity.Cake;
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
 * @createTime 2020/10/28 16:36
 * @projectName first
 * @className CakeDao.java
 * @description TODO
 */
@Repository
public class CakeDao {

    @Autowired
    private DBUtil dbUtil;
    private List<Cake> cakes = new ArrayList<>();

    public void setDbUtil(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }


    /**
     * 得到分页数
     *
     * @param
     * @param name
     * @return
     */
    public int countByPage(String name) {
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "";
            if (name != null && !name.equals("")) {
                sql = "select count(id) from cake where name like '%" + name + "%' and state = 1";
            } else {
                sql = "select count(id) from cake where state = 1";
            }
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 根据id得到蛋糕
     *
     * @param cakeId
     * @return
     */
    public Cake getCakeById(int cakeId) {
        Cake cake = null;
        String sql = "select * from cake where id = " + cakeId + "";
        ResultSet rs;
        ResultSet rs2;
        ResultSet rs3;

        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float size = rs.getFloat("size");
                String description = rs.getString("description");
                int typeId = rs.getInt("typeId");
//                查询数据库
                String sql2 = "select * from type where id = " + typeId + "";
                rs2 = dbUtil.queryDate(sql2);
                Type type = new Type();
                while (rs2.next()) {
                    String typeName = rs2.getString("name");
                    int parentId = rs2.getInt("parentId");
                    if (parentId != 0) {//有上一级菜单
//                    查询父菜单
//                    查询数据库
                        String sql3 = "select * from type where id = " + parentId + " and state = 1";
                        rs3 = dbUtil.queryDate(sql3);
                        while (rs3.next()) {
                            Type type2 = new Type();
                            String typeName2 = rs3.getString("name");
                            type2.setTypeId(parentId);
                            type2.setName(typeName2);
                            type.setParentType(type2);
                        }
                    }
                    type.setName(typeName);
                    type.setTypeId(typeId);
                }

                cake = new Cake();
                cake.setPrice(price);
                cake.setSize(size);
                cake.setId(cakeId);
                cake.setName(name);
                cake.setDescription(description);
                cake.setType(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cake;
    }


    /**
     * 得到蛋糕列表
     *
     * @return
     */
    public List<Cake> getCakes(int pageNum, int pageSize) {
        cakes.clear();
        String sql = "select * from cake where state = 1 limit " + (pageNum - 1) * pageSize + "," + pageSize;
        ResultSet rs;
        ResultSet rs2;
        ResultSet rs3;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float size = rs.getFloat("size");
                String description = rs.getString("description");
                int typeId = rs.getInt("typeId");
//                查询数据库
                String sql2 = "select * from type where id = " + typeId + "";
                rs2 = dbUtil.queryDate(sql2);
                Type type = new Type();
                while (rs2.next()) {
                    String typeName = rs2.getString("name");
                    int parentId = rs2.getInt("parentId");
                    if (parentId != 0) {//有上一级菜单
//                    查询父菜单
//                查询数据库
                        String sql3 = "select * from type where id = " + parentId + "";
                        rs3 = dbUtil.queryDate(sql3);
                        while (rs3.next()) {
                            Type type2 = new Type();
                            String typeName2 = rs3.getString("name");
                            type2.setName(typeName2);
                            type2.setTypeId(parentId);
                            type.setParentType(type2);
                        }
                    }
                    type.setName(typeName);
                    type.setTypeId(typeId);
                }
                Cake cake = new Cake();
                cake.setPrice(price);
                cake.setSize(size);
                cake.setId(id);
                cake.setName(name);
                cake.setDescription(description);
                cake.setType(type);
//				加入列表
                cakes.add(cake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//		返回列表
        return cakes;
    }


    /**
     * 根据名称搜索
     *
     * @param cakeName
     * @return
     */
    public List<Cake> getCakesByName(int pageNum, int pageSize, String cakeName) {
        cakes.clear();
        String sql = "select * from cake where name like '%" + cakeName + "%' and state = 1 limit " + (pageNum - 1) * pageSize + "," + pageSize;
        ResultSet rs;
        ResultSet rs2;
        ResultSet rs3;
        try {
//			执行查询
            rs = dbUtil.queryDate(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                float size = rs.getFloat("size");
                String description = rs.getString("description");
                int typeId = rs.getInt("typeId");
//                查询数据库
                String sql2 = "select * from type where id = " + typeId + "";
                rs2 = dbUtil.queryDate(sql2);
                Type type = new Type();
                while (rs2.next()) {
                    String typeName = rs2.getString("name");
                    int parentId = rs2.getInt("parentId");
                    if (parentId != 0) {//有上一级菜单
//                    查询父菜单
                        //                查询数据库
                        String sql3 = "select * from type where id = " + parentId + "";
                        rs3 = dbUtil.queryDate(sql3);
                        while (rs3.next()) {
                            Type type2 = new Type();
                            String typeName2 = rs3.getString("name");
                            type2.setName(typeName2);
                            type2.setTypeId(parentId);
                            type.setParentType(type2);
                        }
                    }
                    type.setName(typeName);
                    type.setTypeId(typeId);
                }
                Cake cake = new Cake();
                cake.setPrice(price);
                cake.setSize(size);
                cake.setId(id);
                cake.setName(name);
                cake.setDescription(description);
                cake.setType(type);
//				加入列表
                cakes.add(cake);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//		返回列表
        return cakes;

    }

    /**
     * 删除蛋糕
     *
     * @param cakeId
     * @return
     */
    public int deleteCakeById(int cakeId) {
//				编写sql语句
        String sql = "update cake set state = 0 where id = " + cakeId + "";
        int deleteNum = -1;// 记录数
        try {
            deleteNum = dbUtil.updateData(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//		返回
        return deleteNum;
    }

    /**
     * 上传蛋糕
     *
     * @param cake
     * @return
     */
    public int addCake(Cake cake) {
        float price = cake.getPrice();
        float size = cake.getSize();
        String name = cake.getName();
        String description = cake.getDescription();
        int typeId = cake.getType().getTypeId();

//		编写sql语句
        String sql = "insert into cake(name,description,price,size,typeId) " + "values('"
                + name + "','" + description + "'," + price + "," + size + "," + typeId + ")";
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
     * 更新蛋糕
     *
     * @param cake
     * @return
     */
    public int UpdateCake(Cake cake) {
        int id = cake.getId();
        float price = cake.getPrice();
        float size = cake.getSize();
        int typeId = cake.getType().getTypeId();
        String name = cake.getName();
        String description = cake.getDescription();
//		编写sql语句
        String sql = "update cake  set name = '" + name + "',typeId = " + typeId + ", description= '" + description +
                "', price = " + price + " , size =" + size + " where id=" + id + "";
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
     * 根据类型删除蛋糕
     *
     * @param typeId
     * @return
     */
    public int deleteCakeByTypeId(int typeId) {
        //				编写sql语句
        String sql = "update cake set state = 0 where typeId = " + typeId + "";
        int deleteNum = -1;// 记录数
        try {
            deleteNum = dbUtil.updateData(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//		返回
        return deleteNum;
    }
}
