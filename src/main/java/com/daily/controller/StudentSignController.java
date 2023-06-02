package com.daily.controller;

import com.daily.config.R;
import com.daily.service.StudentSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
