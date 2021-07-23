package com.example.demo.web;

import com.example.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    // MyLogger 는 Request Scope 이므로 서버를 기동시키는 시점에는 주입할 수 없다.
    private final MyLogger logger;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String s = request.getRequestURL().toString();
        logger.setRequestURL(s);
        logger.log("controller Test");
        logDemoService.logic("test id");
        return "OK";
    }
}
