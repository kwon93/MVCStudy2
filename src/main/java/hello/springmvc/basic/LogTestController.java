package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //return값을 restAPI 처럼 반환시킴 string을 view Name 이 아닌 http message body 에 바로 출력
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); // 내 클래스로 지정

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "spring";
        log.trace("trace log = {}",name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}",name);
        return "ok";
    }

}
