package oceans.placeOnMember.controller;

import com.alibaba.fastjson.JSON;
import oceans.placeOnMember.mapper.UserMapper;
import oceans.placeOnMember.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//运营后台用户管理
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/member/User/login")
    public Map<String, Object> login(@RequestParam(value = "param") String param) {
        User user = JSON.parseObject(param, User.class);
        User users = userMapper.login(user);
        Map<String, Object> resultMap = new HashMap<>();
        if (null != users) {
            resultMap.put("userId", null == users.getUserId() ? "" : users.getUserId());
            resultMap.put("userName", null == users.getUserName() ? "" : users.getUserName());
            resultMap.put("userSex", null == users.getUserSex() ? "" : users.getUserSex());
            resultMap.put("userEmail", null == users.getUserEmail() ? "" : users.getUserEmail());
        }
        return resultMap;
    }

}
