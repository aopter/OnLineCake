package com.lkl.service;

import com.lkl.dao.CakeDao;
import com.lkl.entity.Cake;
import com.lkl.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2020/10/28 16:35
 * @projectName first
 * @className CakeService.java
 * @description
 */
@Service
public class CakeService {
    @Autowired
    private CakeDao cakeDao;

    public void setCakeDao(CakeDao cakeDao) {
        this.cakeDao = cakeDao;
    }

    public Cake getCakeById(int cakeId) {
        return cakeDao.getCakeById(cakeId);
    }

    /**
     * 查询首页蛋糕列表
     *
     * @return
     */
    public Page<Cake> getCakes(int pageNum, int pageSize) {
        Page<Cake> page = new Page<Cake>(pageNum, pageSize);
        int count = cakeDao.countByPage("");
        List<Cake> list = cakeDao.getCakes(pageNum, pageSize);
        page.setList(list);
        page.setTotalCount(count);
        return page;
    }

    /**
     * 根据名字返回蛋糕列表
     *
     * @param cakeName
     * @return
     */
    public Page<Cake> getCakesByName(int pageNum, int pageSize, String cakeName) {
        Page<Cake> page = new Page<Cake>(pageNum, pageSize);
        int count = cakeDao.countByPage(cakeName);
        List<Cake> list = cakeDao.getCakesByName(pageNum, pageSize, cakeName);
        page.setList(list);
        page.setTotalCount(count);
        return page;
    }

    public int deleteCakeById(int cakeId) {
        return cakeDao.deleteCakeById(cakeId);
    }

    public int addCake(Cake cake) {
        return cakeDao.addCake(cake);
    }

    public int UpdateCake(Cake cake) {
        return cakeDao.UpdateCake(cake);
    }

}
