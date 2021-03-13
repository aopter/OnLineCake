package com.lkl.controller;

import com.lkl.entity.Cake;
import com.lkl.entity.Type;
import com.lkl.service.CakeService;
import com.lkl.service.TypeService;
import com.lkl.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 刘凯丽
 * @createTime 2020/10/28 16:27
 * @projectName first
 * @className cakeController.java
 * @description
 */

@RequestMapping("/cake")
public class cakeController {

    @Autowired
    private CakeService cakeService;
    @Autowired
    private TypeService typeService;

    public void setCakeService(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    //
    @RequestMapping(value = "/items")
    public ModelAndView cakeItems(HttpServletRequest request) {
        String page1 = request.getParameter("page");
        String cakeName = request.getParameter("name"); // 名称

//       获取请求参数
        int pageNum = 1, pageSize = 10;
        if (null != page1 && !"".equals(page1)) {
            pageNum = Integer.parseInt(page1);
        }
        Page<Cake> page;
        if (null != cakeName && !"".equals(cakeName))
            page = cakeService.getCakesByName(pageNum, pageSize, cakeName);
        else
            page = cakeService.getCakes(pageNum, pageSize);
        //创建modelandview对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("cake_items");

        return mv;
    }


    /**
     * 删除蛋糕对象
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCake")
    public ModelAndView deleteCake(HttpServletRequest request, HttpServletResponse response) {
        //创建modelandview对象
        ModelAndView mv = new ModelAndView();
        if (null == request.getParameter("id") || "".equals(request.getParameter("id"))) {

            try {
                response.sendRedirect("items");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int cakeId = Integer.parseInt(request.getParameter("id"));
        int deleteNum = cakeService.deleteCakeById(cakeId);
        if (deleteNum > 0) {//重新获取
            try {
                response.sendRedirect("items");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mv.setViewName("lost_info_eorro");
        }
        return mv;
    }

    /**
     * 跳转更新页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toUpdateCake")
    public ModelAndView toUpdateCake(HttpServletRequest request, HttpServletResponse response) {
        int cakeId = Integer.parseInt(request.getParameter("id"));
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
        Cake cake = cakeService.getCakeById(cakeId);
        //       查询蛋糕类型
        List<Type> types = typeService.getTypes();
        mv.addObject("types", types);
//       设置
        mv.addObject("cake", cake);
        mv.setViewName("cake_update");
        return mv;
    }

    /**
     * 更新蛋糕
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateCake")
    public ModelAndView updateCake(HttpServletRequest request, HttpServletResponse response) {
        int cakeId = Integer.parseInt(request.getParameter("cakeId"));
        System.out.println("cakeID:" + cakeId);
        String cakeName = request.getParameter("cakeName"); // 名称
        int typeId = Integer.parseInt(request.getParameter("cakeType")); // 类型
        String cakeDescription = request.getParameter("cakeDescription"); // 材料
        float cakePrice = Float.parseFloat(request.getParameter("cakePrice")); // 价格
        float cakeSize = Float.parseFloat(request.getParameter("cakeSize")); // 尺寸
        Cake cake = new Cake();
        Type type = new Type();
        type.setTypeId(typeId);
        cake.setId(cakeId);
        cake.setType(type);
        cake.setName(cakeName);
        cake.setDescription(cakeDescription);
        cake.setPrice(cakePrice);
        cake.setSize(cakeSize);
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
//        插入数据库
        int updateNum = cakeService.UpdateCake(cake);
        if (updateNum > 0) {
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

    /**
     * 跳转到添加蛋糕页面
     * * @return
     */
    @RequestMapping(value = "/toAddCake")
    public ModelAndView toAddCake(HttpServletRequest request) {
//      创建modelandview对象
        ModelAndView mv = new ModelAndView();
//       查询蛋糕类型
        List<Type> types = typeService.getTypes();
        mv.addObject("types", types);
//       设置
        mv.setViewName("cake_add");
        return mv;
    }

    /**
     * 添加蛋糕
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCake", method = RequestMethod.POST)
    public ModelAndView addCake(HttpServletRequest request, HttpServletResponse response) {
//        创建modelandview对象
        ModelAndView mv = new ModelAndView();
        if ("".equals(request.getParameter("cakeType")) || null == request.getParameter("cakeType") || "".equals(request.getParameter("cakePrice")) || null == request.getParameter("cakePrice") || "".equals(request.getParameter("cakeSize")) || null == request.getParameter("cakeSize") || "".equals(request.getParameter("cakeName")) || null == request.getParameter("cakeName")) {
            mv.setViewName("lost_info_eorror");
            return mv;
        }
        String cakeName = request.getParameter("cakeName"); // 名称

        int typeId = Integer.parseInt(request.getParameter("cakeType")); // 类型
        String cakeDescription = request.getParameter("cakeDescription"); // 材料
        float cakePrice = Float.parseFloat(request.getParameter("cakePrice")); // 价格
        float cakeSize = Float.parseFloat(request.getParameter("cakeSize")); // 尺寸
        Cake cake = new Cake();
        Type type = new Type();
        type.setTypeId(typeId);
        cake.setType(type);
        cake.setName(cakeName);
        cake.setDescription(cakeDescription);
        cake.setPrice(cakePrice);
        cake.setSize(cakeSize);
//        插入数据库
        System.out.println(cake);
        int addNum = cakeService.addCake(cake);
        if (addNum > 0) {//添加成功
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
