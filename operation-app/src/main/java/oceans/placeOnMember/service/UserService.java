package oceans.placeOnMember.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "member-soa")
public interface UserService {

    @RequestMapping(value = "/member/User/login")
    Map<String,Object> login(@RequestParam("param") String param);

}
