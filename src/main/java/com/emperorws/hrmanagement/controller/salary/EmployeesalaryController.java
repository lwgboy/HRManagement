package com.emperorws.hrmanagement.controller.salary;

import com.emperorws.hrmanagement.logger.SystemControllerLog;
import com.emperorws.hrmanagement.model.*;
import com.emperorws.hrmanagement.service.EmployeesalaryService;
import com.emperorws.hrmanagement.service.SpeaddService;
import com.emperorws.hrmanagement.service.WelfareService;
import com.emperorws.hrmanagement.utils.EmpSalPOIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: EmperorWS
 * @Date: 2020/3/1 18:52
 * @Description: 员工薪资配置控制层
 **/
@RestController
@RequestMapping("/salary/employee")
public class EmployeesalaryController {
    @Autowired
    EmployeesalaryService employeesalaryService;
    @Autowired
    WelfareService welfareService;
    @Autowired
    SpeaddService speaddService;

    @GetMapping("/")
    @SystemControllerLog(description="获取所有的员工薪资配置信息")
    public RespPageBean getEmployeesalaryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Employee employee) {
        return employeesalaryService.getEmployeesalaryByPage(page, size, employee);
    }

    @GetMapping("/find")
    @SystemControllerLog(description="获取所有的员工工号和姓名信息，方便智能提示")
    public Boolean getEmpByWorkid(Integer workid){
        return employeesalaryService.getEmpSalByWorkid(workid);
    }

    @DeleteMapping("/{esid}")
    @SystemControllerLog(description="删除员工的薪资配置信息")
    public RespBean deleteEmployeesalaryById(@PathVariable Integer esid){
        if (employeesalaryService.deleteEmpSalById(esid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PostMapping("/")
    @SystemControllerLog(description="添加员工的薪资配置信息")
    public RespBean addEmployeesalary(@RequestBody Employeesalary employeesalary){
        if (employeesalaryService.addEmpSal(employeesalary) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    @SystemControllerLog(description="更新员工的薪资配置信息")
    public RespBean updateEmployeesalary(@RequestBody Employeesalary employeesalary){
        if (employeesalaryService.updateEmpSal(employeesalary) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/deleteempsals")
    @SystemControllerLog(description="批量删除员工的薪资配置信息")
    public RespBean deleteEmployeesalarys(@RequestBody List<Employeesalary> employeesalary){
        if(employeesalaryService.deleteEmpSals(employeesalary)==employeesalary.size()){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PostMapping("/import")
    @SystemControllerLog(description="批量导入员工的薪资配置信息")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employeesalary> list = EmpSalPOIUtils.excel2Employeesalary(file, (List<Welfare>)welfareService.getAllWelfareByPage(null,null,null).getData(),(List<Speadd>) speaddService.getAllSpeaddByPage(null,null,null).getData());
        if (employeesalaryService.addEmpSals(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }

    @PostMapping("/export")
    @SystemControllerLog(description="批量导出员工的薪资配置信息")
    public ResponseEntity<byte[]> exportData(@RequestBody List<Employeesalary> list) {
        return EmpSalPOIUtils.employeesalary2Excel(list);
    }
}
