package com.emperorws.hrmanagement.service;

import com.emperorws.hrmanagement.logger.SystemServiceLog;
import com.emperorws.hrmanagement.mapper.ContractinfoMapper;
import com.emperorws.hrmanagement.model.Contractinfo;
import com.emperorws.hrmanagement.model.Employee;
import com.emperorws.hrmanagement.model.Employeechange;
import com.emperorws.hrmanagement.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: EmperorWS
 * @Date: 2020/2/22 16:14
 * @Description: 员工合同管理服务层
 **/
@Service
public class ContractinfoService {
    @Autowired
    ContractinfoMapper contractinfoMapper;

    @SystemServiceLog(description="获取员工的合同信息")
    public RespPageBean getContractinfoByPage(Integer page, Integer size, Contractinfo contractinfo, Date[] signdate){
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employeechange> data=contractinfoMapper.getContractinfoByPage(page,size,contractinfo,signdate);
        Long total=contractinfoMapper.getTotal(contractinfo,signdate);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @SystemServiceLog(description="添加员工的合同信息")
    public Integer addContractinfo(Contractinfo contractinfo){
        return contractinfoMapper.insertSelective(contractinfo);
    }
}
