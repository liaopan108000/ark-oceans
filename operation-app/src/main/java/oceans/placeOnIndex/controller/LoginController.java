package oceans.placeOnIndex.controller;


import com.alibaba.fastjson.JSON;
import oceans.placeOnMember.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login.do")
    public String login() {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", "root");
        map.put("userPassword", "123456");
        String param = JSON.toJSONString(map);

        Map<String, Object> resultMap = userService.login(param);

        if(null != resultMap){
            return "/index.html";
        }else {
            return "/login.html";
        }
    }
}


