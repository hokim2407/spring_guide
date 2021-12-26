package com.hokim.Scheduling_Tasks;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//주석이 달린 클래스가 "구성요소"임을 나타냄 ->이러한 클래스는 주석 기반 구성 및 클래스 경로 스캐닝을 사용할 때 자동 감지
// 메인 클래스의 @SpringBootApplication 안에 @ComponentScan이 포함되어 있으므로 @Component가 달려있는 항목은 시작시 자동 실행됨
public class Task {
    private  static final Logger log = LoggerFactory.getLogger(Task.class);
    private  static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // fixedRate 간격으로 실행되도록 스케줄링하는 어노테이션
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.info("현재 시각 : {}", dateFormat.format(new Date()) );
    }

}
