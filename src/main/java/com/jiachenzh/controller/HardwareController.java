package com.jiachenzh.controller;

import com.jiachenzh.common.Result;
import com.jiachenzh.common.StatusCode;
import com.jiachenzh.service.HardWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName HardwareController
 * @Description 在此controller层提供所有硬件的接口
 * @Author
 * @Date 2019/3/5 19:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    private HardWareService hardWareService;

    /**
     * @Author
     * @Description 硬件三种操作的反馈方法
     * @Date 20:30 2019/3/5
     * @Param [UUID]
     * @throws
     * @return com.jiachenzh.common.Result
     */
    @GetMapping("/{UUID}")
    @ResponseBody
    public Result openDoor(@PathVariable String uuid){
        hardWareService.hardWareHandle(uuid);
        return new Result(true, StatusCode.OK);
    }



}
