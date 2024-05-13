package com.atguigu.cloud.controller;


import com.atguigu.cloud.entites.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/consumer/pay")
@Tag(name = "客戶端支付微服務模塊",description = "客戶端CRUD")
public class OrderController {
    //public static final String PAYMENT_SRV_URL = "http://localhost:8001"; //地址先寫死，後續處理
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";//服務註冊中心上的微服務名稱

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/add")
    @Operation(summary = "新增",description = "客戶端新增支付流水方法，Json串做參數")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        log.info("成功插入紀錄，返回值:{}",payDTO);
        return restTemplate.postForObject(PAYMENT_SRV_URL + "/pay/add",payDTO,ResultData.class);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "刪除",description = "客戶端刪除支付流水方法")
    public ResultData deleteOrder(@PathVariable("id") Integer id){
        log.info("成功刪除紀錄id:{}",id);
        return restTemplate.exchange(PAYMENT_SRV_URL + "/pay/del/"+id,HttpMethod.DELETE,null,ResultData.class).getBody();
    }

    @PutMapping("/update")
    @Operation(summary = "修改",description = "客戶端修改支付流水方法")
    public ResultData updateOrder(@RequestBody PayDTO payDTO){
        log.info("成功修改紀錄:{}",payDTO);
          return restTemplate.exchange(PAYMENT_SRV_URL + "/pay/update", HttpMethod.PUT, new HttpEntity(payDTO), ResultData.class).getBody();

    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查詢",description = "客戶端查詢支付流水方法")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        log.info("查詢紀錄id:{}",id);
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/get")
    @Operation(summary = "查詢",description = "客戶端查詢支付流水方法")
    public ResultData<List> getAllOrder(){
        log.info("查詢所有紀錄");
        return restTemplate.getForObject(PAYMENT_SRV_URL + "/pay/get", ResultData.class);
    }
}
