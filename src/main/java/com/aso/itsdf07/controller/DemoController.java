package com.aso.itsdf07.controller;

import com.aso.itsdf07.bean.ResponseDemoEntity;
import com.aso.itsdf07.entity.DemoEntity;
import com.aso.itsdf07.service.DemoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 集一身使用案例于一类，前端结合demo.html、demo.js
 * @Auther: itsdf07
 * @Date: 2019/7/26/026 17:39
 */
@Controller
@RequestMapping(value = {"/demo"})
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = {"/getDemoEntity"})
    @ResponseBody
    public String getDemoEntity(String username, String data) {
        //username:itsdf07,data:[{"name":"aso","age":30,"sex":"男"},{"name":"abin","age":30,"sex":"女"}]
        logger.info("username:" + username + ",data:" + data);
        DemoEntity demoEntity = demoService.getDemoEntity(username);
        List<DemoEntity> datas = new ArrayList<>();
        datas.add(demoEntity);
        ResponseDemoEntity responseDemoEntity = new ResponseDemoEntity();
        if (null == demoEntity) {
            responseDemoEntity.setMessage(username + "数据不存在没有");
            responseDemoEntity.setCode(404);
            return new Gson().toJson(responseDemoEntity);
        }
        responseDemoEntity.setMessage("请求成功");
        responseDemoEntity.setCode(200);
        responseDemoEntity.setData(datas);
        //返回json格式:{"data":[{"dId":1,"dUsername":"itsdf07","dPassword":"123456","dDisplayname":"叫我流氓","dAge":30,"dBalance":10.00,"dCreateTime":"Jul 27, 2019 11:17:29 AM"}],"message":"请求成功","code":200}
        return new Gson().toJson(responseDemoEntity);
    }

    @RequestMapping(value = {"/getAllDemoEntity"})
    @ResponseBody
    public String getDemoEntitys() {
        logger.info("getAllDemoEntitys");
        List<DemoEntity> demoEntity = demoService.getAllDemoEntity();
        ResponseDemoEntity responseDemoEntity = new ResponseDemoEntity();
        if (demoEntity.isEmpty()) {
            responseDemoEntity.setMessage("暂无数据");
            responseDemoEntity.setCode(404);
            return new Gson().toJson(responseDemoEntity);
        }
        responseDemoEntity.setMessage("请求成功");
        responseDemoEntity.setCode(200);
        responseDemoEntity.setData(demoEntity);
        //返回json格式:{"data":[{"dId":1,"dUsername":"itsdf07","dPassword":"123456","dDisplayname":"叫我流氓","dAge":30,"dBalance":10.00,"dCreateTime":"Jul 27, 2019 11:17:29 AM"}],"message":"请求成功","code":200}
        return new Gson().toJson(responseDemoEntity);
    }

}
