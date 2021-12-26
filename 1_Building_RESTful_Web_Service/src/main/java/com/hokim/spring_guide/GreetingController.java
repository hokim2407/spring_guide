package com.hokim.spring_guide;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

// @RestController는 @Controller와 @ResponseBody를 모두 가지고 있음
@RestController
public class GreetingController {
    private static final String template = "Hello. %s!";

    //AtomicLong이란 말그대로 원자성을 가지고 있는 Long 타입 변수
    //값을 읽고 쓰는것을 동시에 할 수 있는 변수임
    private final AtomicLong counter
            = new AtomicLong();//초기값이 0인 AtomicLong 생성

    //url과 Get method를 맵핑
    @GetMapping("/greeting")
    public Greeting greeting(
            //쿼리에서 name의 값을 맵핑(이름은 params지만 실질적으로는 query)-기본값은 World
            @RequestParam(value = "name", defaultValue = "World")
                    String name) {

        //반환될때 Greeging을 JSON으로 변환해서 반환해야 하지만
        // Spring의 HTTP 변환 지원 덕분에 수동으로 설정할 필요 없음
        //( Jackson 2가 classPAth에 있어서 MappingJackson2HttpMessageConverter가 자동으로 수행된다고 함
        // classPath와 자동실행의 연관관계는 SpringGuideApplication의 어노테이션에 있음)
        return new Greeting(
                counter.incrementAndGet(), //증가시키고 값을 가져오는데 그 행위가 원자적으로 수행됨
                String.format(template, name) //name을 template에 맞춰 포맷팅
        );
    }

}
