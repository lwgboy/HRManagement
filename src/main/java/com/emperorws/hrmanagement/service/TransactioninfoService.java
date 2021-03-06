package com.emperorws.hrmanagement.service;

import com.emperorws.hrmanagement.logger.SystemServiceLog;
import com.emperorws.hrmanagement.mapper.AppattMapper;
import com.emperorws.hrmanagement.model.Appatt;
import com.emperorws.hrmanagement.model.Employee;
import com.emperorws.hrmanagement.model.Employeesalary;
import com.emperorws.hrmanagement.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: EmperorWS
 * @Date: 2020/3/8 14:07
 * @Description: 所有考勤事务申请管理服务层
 **/
@Service
public class TransactioninfoService {
    @Autowired
    AppattMapper appattMapper;

    @SystemServiceLog(description="获取所有的考勤事务申请")
    public RespPageBean getTransactioninfoByPage(Integer page, Integer size, Employee employee, Date[] appdate) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Appatt> data = appattMapper.getTransactioninfoByPage(page, size, employee, appdate);
        Long total = appattMapper.getTransactioninfoTotal(employee, appdate);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
