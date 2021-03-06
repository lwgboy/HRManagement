package com.emperorws.hrmanagement.controller.employee;

import com.emperorws.hrmanagement.logger.SystemControllerLog;
import com.emperorws.hrmanagement.model.Contractinfo;
import com.emperorws.hrmanagement.model.RespBean;
import com.emperorws.hrmanagement.model.RespPageBean;
import com.emperorws.hrmanagement.service.ContractinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: EmperorWS
 * @Date: 2020/2/22 16:22
 * @Description: 员工合同管理控制层
 **/
@RestController
@RequestMapping("/employee/basic/contractinfo")
public class ContractinfoController {
    @Autowired
    ContractinfoService contractinfoService;

    @GetMapping("/")
    @SystemControllerLog(description="获取员工的合同信息")
    public RespPageBean getContractinfoByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Contractinfo contractinfo, Date[] signdate){
        return contractinfoService.getContractinfoByPage(page,size,contractinfo,signdate);
    }

    @PostMapping("/")
    @SystemControllerLog(description="添加员工的合同信息")
    public RespBean addContractinfo(@RequestBody Contractinfo contractinfo) {
        if (contractinfoService.addContractinfo(contractinfo) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
}
