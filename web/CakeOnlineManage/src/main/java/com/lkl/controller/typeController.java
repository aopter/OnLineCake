package com.lkl.controller;

import com.lkl.entity.Type;
import com.lkl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘凯丽
 * @createTime 2020/10/29 16:11
 * @projectName first
 * @className typeController.java
 * @description 菜单管理
 */
@Controller
@RequestMapping("/type")
public class typeController {

    @Autowired
    private TypeService typeService;

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 得到菜单列表
     *
     * @return
     */
    @RequestMapping(value = "/items")
    public ModelAndView typeItems() {
//
        ModelAndView mv = new ModelAndView();
        Map<Type, List> container = new HashMap<>();
        List<Type> types = typeService.getFirstTypes();
        for (Type type : types) {
            List<Type> mySunTypes = new ArrayList<>();
            List<Type> sunTypes = typeService.getSunTypes(type.getTypeId());
            for (Type type1 : sunTypes) {
                mySunTypes.add(type1);
            }
            container.put(type, mySunTypes);
        }
        mv.addObject("container", container);
        mv.setViewName("type_items");
        return mv;
    }

    /**
     * 删除蛋糕类型
     *
     * @return
     */
    @RequestMapping(value = "/deleteType")
    public ModelAndView deleteTypeItemspe(HttpServletRequest request, HttpServletResponse response) {
//
        int typeId = Integer.parseInt(request.getParameter("typeId"));
//        删除类型
        ModelAndView mv = new ModelAndView();
        int isDelete = typeService.deleteType(typeId);
        if (isDelete > 0) {
            try {
                response.sendRedirect("items");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            mv.setViewName("lost_info_eorror");
        return mv;
    }

    /**
     * 跳转到添加类型页面
     * * @return
     */
    @RequestMapping(value = "/toAddType")
    public ModelAndView toAddCake(HttpServletRequest request) {
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
//       查询蛋糕类型
        List<Type> types = typeService.getFirstTypes();
        mv.addObject("types", types);
//       设置
        mv.setViewName("type_add");
        return mv;
    }

    /**
     * 添加蛋糕
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public ModelAndView addCake(HttpServletRequest request) {
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
        int parentId = Integer.parseInt(request.getParameter("cakeType")); // 类型 不会为空
        if (null != request.getParameter("typeName") && "".equals(request.getParameter("typeName"))) {
//            错误界面
            mv.setViewName("lost_info_eorror");
            return mv;
        }
        String name = request.getParameter("typeName");

//        插入数据库
        int addNum = typeService.addType(name, parentId);
        if (addNum > 0) {
            Map<Type, List> container = new HashMap<>();
            List<Type> types = typeService.getFirstTypes();
            for (Type type : types) {
                List<Type> mySunTypes = new ArrayList<>();
                List<Type> sunTypes = typeService.getSunTypes(type.getTypeId());
                for (Type type1 : sunTypes) {
                    mySunTypes.add(type1);
                }
                container.put(type, mySunTypes);
            }
            mv.addObject("container", container);
            mv.setViewName("type_items");
        } else {//??
            mv.setViewName("lost_info_eorror");
        }
        return mv;
    }


    /**
     * 跳转更新页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/toUpdateType")
    public ModelAndView toUpdateCake(HttpServletRequest request) {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        Type type = typeService.getCurrentType(typeId);
        Type pType = typeService.getCurrentType(type.getParentType().getTypeId());
//       查询一级类型
        List<Type> types = typeService.getFirstTypes();
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("types", types);
        mv.addObject("pType", pType);
//      设置
        mv.addObject("currentType", type);

        mv.setViewName("type_update");
        return mv;
    }

    /**
     * 更新页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateType")
    public ModelAndView updateCake(HttpServletRequest request, HttpServletResponse response) {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        int pTypeId = Integer.parseInt(request.getParameter("pTypeId"));
        String name = request.getParameter("typeName");
        Type type = new Type();
        type.setName(name);
        type.setTypeId(typeId);
        Type pType = new Type();
        pType.setTypeId(pTypeId);
        type.setParentType(pType);

        ModelAndView mv = new ModelAndView();
//        插入数据库
        int addNum = typeService.updateType(type);
        if (addNum > 0) {
            try {
                response.sendRedirect("items");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mv.setViewName("lost_info_eorror");
        }
        return mv;
    }
}
