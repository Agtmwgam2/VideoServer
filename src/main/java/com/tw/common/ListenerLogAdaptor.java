package com.tw.common;

import com.tw.config.FtpConfig;
import com.tw.entity.LogList;
import com.tw.entity.common.ConstantParam;
import com.tw.service.LogListService;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author: zhuoshouyi
 * @Description:
 * @Date: 2019/8/15
 * @param: 日志目录监听处理类
 * @return:
 */
@Component
public class ListenerLogAdaptor extends FileAlterationListenerAdaptor {

    private static Logger log = Logger.getLogger(ListenerLogAdaptor.class);

    @Autowired
    private FtpConfig ftpConfig;

    /**
     * 业务
     **/
    @Resource(name = "logListService")
    private LogListService logListService;

    /**
     * 目录创建
     **/
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("【日志】创建新目录");
    }

    /**
     * 文件创建
     **/
    @Override
    public void onFileCreate(File file) {
        log.info("【日志】文件监控,监控处理开始。文件名为：" + file);

        String souceFileName = file.getName();
        String logFileName =souceFileName.substring(0,souceFileName.lastIndexOf("."));
        String[] param = logFileName.split("_");
        if (param==null || param.length!=6){
            log.error("【日志】日志名称格式错误,文件:"+souceFileName);
        }else {
            String serial = param[0];
            String logNum = param[1];
            String ipAddress = param[2];
            String logName = param[3];
            String logTimePre = param[4];
            String logTimeSuf = param[5];

            String logPath =file.getAbsolutePath().replace(ftpConfig.getBasePath(),"");

            System.out.println("【日志】serial:"+serial);
            System.out.println("【日志】logNum:"+logNum);
            System.out.println("【日志】ipAddress:"+ipAddress);
            System.out.println("【日志】logName:"+logName);
            System.out.println("【日志】logTime:"+logTimePre+" "+logTimeSuf);
            System.out.println("【日志】logPath:"+logPath);


            LogList logList = new LogList();
            logList.setSerial(serial);
            logList.setLogName(logName);
            logList.setIpAddress(ipAddress);
            logList.setLogNum(logNum);
            logList.setLogTime(logTimePre+" "+logTimeSuf.replace("-", ":"));
            logList.setLogPath(logPath);
            logList.setIsValid(ConstantParam.IS_VALID_YES);
            logListService.addLogList(logList);
        }

        log.info("【日志】监控处理结束=====" );
    }




    /**
     * 目录修改
     **/
    @Override
    public void onDirectoryChange(File directory) {
        log.info("【日志】目录修改======"+directory.getName() );
        System.out.println("目录修改");
    }

    /**
     * 目录删除
     **/
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("【日志】目录删除======"+directory.getName() );
        System.out.println("目录删除");
    }

    /**
     * 文件修改（对于文件名的修改，会先触发文件新增方法，再触发文件删除方法）
     **/
    @Override
    public void onFileChange(File file) {
        log.info("【日志】文件修改======"+file.getAbsolutePath() );
        System.out.println("文件修改");
    }

    /**
     * 文件删除
     **/
    @Override
    public void onFileDelete(File file) {
        log.info("【日志】删除文件======"+file.getAbsolutePath() );
        System.out.println("删除文件：" + file);
    }

    /**
     * 扫描开始
     **/
    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    /**
     * 扫描结束
     **/
    @Override
    public void onStop(FileAlterationObserver observer) {
    }
}