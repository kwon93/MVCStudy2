package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestPramV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}",username,age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ){

        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    @RequestMapping({"/request-param-v3","/request-param-v4"})
    @ResponseBody
    public String requestParamV3(String username, int age){ //단순 타입이면 @requestParam 생략가능.

        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(
            @RequestParam(required = true) String username
            ,@RequestParam(required = false) int age){ //true면 무조건 있어야함,

        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username
            ,@RequestParam(required = false, defaultValue = "-1") int age){ //입력값이 없을 경우 기본값이 자동으로 들어감.

        log.info("username = {}, age = {}",username,age);

        return "ok";
    }

    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String , Objects> paramMap){

        log.info("username = {}, age = {}",paramMap.get("username"),paramMap.get("age"));

        return "ok";
    }

    /**
     *
     * @param helloData 객체를 생성한다. 해당 객체의 프로퍼티를 찾고 setter를 호출해서 파라미터값을 입력(바인딩)한다.
     * @return
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { //@ModelAttribute 생략이 가능
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
