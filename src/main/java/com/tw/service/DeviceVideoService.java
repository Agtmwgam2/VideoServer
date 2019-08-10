package com.tw.service;

import com.tw.dao.DeviceVideoDao;
import com.tw.entity.Device;
import com.tw.entity.DeviceVideo;
import com.tw.entity.common.ConstantParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lushiqin
 * @Description: 视频的service类
 * @Date: 2019/8/6
 * @param: null
 * @return:
 */
@Service
public class DeviceVideoService {

    @Autowired
    private DeviceVideoDao dao;

    /***
     * 告警视频详情接口
     * @param serial
     * @return
     */
    public String getWarningInfoDesc(String serial,String eventId) {
        return dao.getWarningInfoDesc(serial,eventId);
    }

    /***
     * 告警视频列表接口
     * @param serial ,eventId
     * @return
     */
    public List<DeviceVideo> getWarningInfoList(String serial) {

        return dao.getWarningInfoList(serial);
    }

}