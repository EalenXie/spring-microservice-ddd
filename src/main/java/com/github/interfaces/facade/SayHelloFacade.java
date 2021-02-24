package com.github.interfaces.facade;

import com.github.infrastructure.base.resp.RespBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EalenXie on 2018/8/30 18:10.
 * 此为Fa
 */
@RestController
public class SayHelloFacade {


    @GetMapping("/sayHello")
    public RespBody<String> sayHello() {
        return RespBody.ok("hello world");
    }

}
