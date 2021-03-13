package com.lkl.service;

import com.lkl.dao.CakeDao;
import com.lkl.dao.TypeDao;
import com.lkl.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2020/10/29 13:02
 * @projectName first
 * @className TypeService.java
 * @description 菜单Service类
 */
@Service
public class TypeService {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private CakeDao cakeDao;

    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public List<Type> getTypes() {
        return typeDao.getTypes();
    }

    public List<Type> getFirstTypes() {
        return typeDao.getFirstTypes();
    }

    public List<Type> getSunTypes(int parentId) {
        return typeDao.getSunTypes(parentId);
    }

    /**
     * 删除类型
     *
     * @param typeId
     * @return
     */
    public int deleteType(int typeId) {
//        删除类型、子类型
        int isDelete = typeDao.deleteCakeType(typeId);
        if (isDelete <= 0) {
            return isDelete;
        }
//        删除类型蛋糕
        cakeDao.deleteCakeByTypeId(typeId);
//        删除子类型蛋糕
        List<Type> types = typeDao.getSunTypes(typeId);
        for (Type type : types) {
            cakeDao.deleteCakeByTypeId(type.getTypeId());
        }
        return isDelete;
    }

    public int addType(String name, int parentId) {
        return typeDao.addType(name, parentId);
    }

    public Type getCurrentType(int typeId) {
        return typeDao.getCurrentType(typeId);
    }
}

