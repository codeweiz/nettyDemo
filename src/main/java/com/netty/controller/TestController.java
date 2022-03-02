package com.netty.controller;

import com.netty.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouwei
 */
@RestController
@RequestMapping("/push")
public class TestController {

    @Autowired
    PushMsgService pushMsgService;

    @GetMapping("/{uid}")
    public void pushOne(@PathVariable String uid) {
        pushMsgService.pushMsgToOne(uid, "hello");
    }

    @GetMapping("/all")
    public void pushAll() {
        pushMsgService.pushMsgToAll("hello");
    }

    @GetMapping("/count")
    public int getChannelCount() {
        return pushMsgService.getChannelCount();
    }

}
