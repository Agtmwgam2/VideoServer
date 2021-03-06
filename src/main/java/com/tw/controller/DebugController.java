package com.tw.controller;

import com.tw.config.ConfigProperties;
import com.tw.service.DeviceService;
import com.tw.service.MessageService;
import com.tw.service.VUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: John
 * @Description:    用于测试的接口
 * @Date:  2019/8/2 21:44
 * @param: null
 * @return: 
 */
@RequestMapping("/debug")
@RestController
public class DebugController {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private MessageService messageService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private VUserService vUserService;

    private static org.apache.log4j.Logger logger = Logger.getLogger(LoginController.class);

    /**
     * @Author: John
     * @Description: 查看是否能够调通
     * @Date:  2019/8/7 23:47
     * @param: null
     * @return:
     */
    @RequestMapping("/test")
    public String test() {
        logger.warn("====");
        return "=========调用了test方法";
    }
}
