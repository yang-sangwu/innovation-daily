package com.daily.controller;

import com.daily.config.R;
import com.daily.service.StudentSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author a1002
 */
@RestController
@RequestMapping("/studentSign")
@Slf4j
public class StudentSignController {
    @Resource
    private StudentSignService studentSignService;

    /**
     * 查询某个用户下的所有签到签退情况
     */
    @GetMapping("/querySignByUserId")
    public R querySignByUserId(@RequestParam Map<String, Object> map) {
        return studentSignService.querySignByUserId(map);
    }

    /**
     * 用户签到
     */
    @PostMapping("/addSign")
    public R addSign(@RequestParam Map<String, Object> map) {
        return studentSignService.addSign(map);
    }

    /**
     * 用户签退
     */
    @PutMapping("/addOut")
    public R addOut(@RequestParam Map<String, Object> map) {
        return studentSignService.addOut(map);
    }
}
