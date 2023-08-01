package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/request-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView view = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return view;
    }

    @RequestMapping("/request-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "helloModel~");
        return "/response/hello";
    }

    // * 권장하지 않는 방법 * (불명확함, 명시성이 떨어짐.)
    @RequestMapping("/response/hello") // '/'는 떼지고 논리적이름으로 반환된다.
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello~");
    }
}
