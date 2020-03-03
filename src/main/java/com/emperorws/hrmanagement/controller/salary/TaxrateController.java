package com.emperorws.hrmanagement.controller.salary;

import com.emperorws.hrmanagement.model.RespBean;
import com.emperorws.hrmanagement.model.Taxrate;
import com.emperorws.hrmanagement.service.TaxrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: EmperorWS
 * @Date: 2020/2/28 17:57
 * @Description: 税率表控制层
 **/
@RestController
@RequestMapping("/salary/taxrate")
public class TaxrateController {
    @Autowired
    TaxrateService taxrateService;

    @GetMapping("/")
    public List<Taxrate> getAllTaxrate(){
        return taxrateService.getAllTaxrate();
    }

    @DeleteMapping("/{trid}")
    public RespBean deleteTaxrateById(@PathVariable Integer trid){
        if (taxrateService.deleteTaxrateById(trid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PostMapping("/")
    public RespBean addTaxrate(@RequestBody Taxrate taxrate){
        if (taxrateService.addTaxrate(taxrate) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateTaxrate(@RequestBody Taxrate taxrate){
        if (taxrateService.updateTaxrate(taxrate) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/deletetrs")
    public RespBean deleteTaxrates(@RequestBody List<Taxrate> taxrates){
        if(taxrateService.deleteTaxrates(taxrates)==taxrates.size()){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
