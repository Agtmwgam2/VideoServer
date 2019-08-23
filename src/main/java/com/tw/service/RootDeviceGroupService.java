package com.tw.service;

import com.tw.dao.RootDeviceGroupDao;
import com.tw.entity.Device;
import com.tw.entity.RootDeviceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RootDeviceGroupService {

    @Autowired
    private RootDeviceGroupDao rootDeviceGroupDao;

    public List<RootDeviceGroup> getAllRootDeviceGroup() {
        return rootDeviceGroupDao.getAllRootDeviceGroup();
    }

    public List<Device> getRootDeviceByGroupId(int rootDeviceGroupId) {
        return rootDeviceGroupDao.getRootDeviceByGroupId(rootDeviceGroupId);
    }

    public int addRootGroup(String rootDeviceGroupName) {
        return rootDeviceGroupDao.addRootGroup(rootDeviceGroupName);
    }

    /**
     * @Author: John
     * @Description: 移动root分组
     * @Date:  2019/8/22 23:50
     * @param: deviceId
     * @param: oldGroupId
     * @param: newGroupId
     * @param: newRootGroupName
     * @return:
     */
    public int moveRootGroup(int deviceId, int oldGroupId, int newGroupId, String newRootGroupName) {
        Map<String, Object> param = new HashMap<>();
        param.put("deviceId", deviceId);
        param.put("oldGroupId", oldGroupId);
        param.put("newGroupId", newGroupId);
        param.put("newRootGroupName", newRootGroupName);
        return rootDeviceGroupDao.moveRootGroup(param);
    }

    //根据groupId拿到组名
    public String getGroupNameByCondition(int newGroupId) {
        return rootDeviceGroupDao.getGroupNameByCondition(newGroupId);
    }


    public int deleteRootGroup(RootDeviceGroup rootDeviceGroup) {
        //将设备的状态置为库存状态

        return rootDeviceGroupDao.deleteRootGroup(rootDeviceGroup);
    }

    public int modifyRootDeviceGroupName(int rootDeviceGroupId, String newDeviceGroupName) {
        Map<String, Object> param = new HashMap<>();
        param.put("rootDeviceGroupId", rootDeviceGroupId);
        param.put("newDeviceGroupName", newDeviceGroupName);
        return rootDeviceGroupDao.modifyRootDeviceGroupName(param);
    }
}