package oceans.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//跳转页面需要用controller，而不是RestController
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/index.do")
    @ResponseBody
    public String index() {
        String login = "欢迎来到星辰大海！";
        return login;
    }
}


