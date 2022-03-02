package com.netty.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouwei
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获运行时异常，并返回给客户端
     * */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(RuntimeException ex) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 4006);
        map.put("msg", ex.getMessage());
        map.put("data", null);
        return map;
    }

}
