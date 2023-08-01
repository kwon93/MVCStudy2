package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);

        response.getWriter().write("ok");
    }


    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer resopnseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}",messageBody);

        resopnseWriter.write("ok");
    }


    @PostMapping("/request-body-string-v3") //message Body 정보를 편리하게 조회 할 수 있다.
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String httpEntityBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();

        log.info("messageBody = {}",httpEntityBody);

        return new HttpEntity<>("ok"); //응답도 가능. 뷰 조회 X
    }

    @PostMapping("/request-body-string-re") //HttpEntity 상속받은 클래스 RequestEntity, ResponseEntity
    public ResponseEntity<String> requestBodyStringRE(RequestEntity<String> httpEntity) throws IOException {

        String httpEntityBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();

        log.info("messageBody = {}",httpEntityBody);

        return new ResponseEntity<String>("ok",HttpStatus.OK); //상태코드를 포함해 응답 가능
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody = {}",messageBody);

        return "ok";
    }

}
