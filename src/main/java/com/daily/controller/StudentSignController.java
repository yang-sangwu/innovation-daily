package com.daily.controller;

import com.daily.service.StudentSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author a1002
 */
@RestController
@RequestMapping("/studentSign")
@Slf4j
public class StudentSignController {
    @Resource
    private StudentSignService studentSignService;
}
