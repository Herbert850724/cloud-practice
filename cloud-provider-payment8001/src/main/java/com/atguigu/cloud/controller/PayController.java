package com.atguigu.cloud.controller;

import com.atguigu.cloud.entites.PayDTO;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pay")
@Tag(name="支付微服務模塊",description ="支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/add")
    @Operation(summary = "新增",description = "新增支付流水方法，Json串做參數")
    public ResultData<String> addPay(@RequestBody Pay pay){
        int i = payService.add(pay);
        log.info("成功插入紀錄，返回值:{}",i);
        return ResultData.success("返回值:"+i);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "刪除",description = "刪除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        log.info("成功刪除紀錄id:{}",id);
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping("/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        log.info("成功修改紀錄:{}",payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);

        int i = payService.update(pay);
        return ResultData.success("返回值:"+i);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查詢",description = "查詢支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if(id < 0) {
            throw new RuntimeException("ID不能是負數");
        }
        log.info("查詢紀錄id:{}",id);
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/get")
    @Operation(summary = "查詢",description = "查詢支付流水方法")
    public ResultData<List<Pay>> getAll(){
        log.info("查詢所有紀錄");
        List<Pay> list = payService.getAll();
        return ResultData.success(list);
    }
}
