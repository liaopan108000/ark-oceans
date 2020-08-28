package oceans.placeOnIndex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//跳转页面需要用controller，而不是RestController
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/index.do")
    public String index() {
        //跳转login.html
        return "login";
    }
}


