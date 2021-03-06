package com.emperorws.hrmanagement.service;

import com.emperorws.hrmanagement.logger.SystemServiceLog;
import com.emperorws.hrmanagement.mapper.AttelogdayMapper;
import com.emperorws.hrmanagement.model.Attelogday;
import com.emperorws.hrmanagement.model.Employee;
import com.emperorws.hrmanagement.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: EmperorWS
 * @Date: 2020/3/12 21:19
 * @Description: 每日考勤管理服务层
 **/
@Service
public class AttelogdayService {
    @Autowired
    AttelogdayMapper attelogdayMapper;

    @SystemServiceLog(description="获取员工的每日考勤数据")
    public RespPageBean getAttelogdayByPage(Integer page, Integer size, Employee employee, Date[] clockinday) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Attelogday> data = attelogdayMapper.getAttelogdayByPage(page, size, employee,clockinday);
        Long total = attelogdayMapper.getTotal(employee,clockinday);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @SystemServiceLog(description="修改旧的员工每日考勤数据")
    public Integer updateAttelogday(Attelogday attelogday){
        return attelogdayMapper.updateByPrimaryKeySelective(attelogday);
    }

    @SystemServiceLog(description="删除旧的员工每日考勤数据")
    public Integer deleteAttelogdayById(Integer attelogdayid){
        return attelogdayMapper.deleteByPrimaryKey(attelogdayid);
    }

    @SystemServiceLog(description="批量删除旧的员工每日考勤数据")
    public Integer deleteAttelogdays(List<Attelogday> attelogdays){
        return attelogdayMapper.deleteAttelogdays(attelogdays);
    }
}
