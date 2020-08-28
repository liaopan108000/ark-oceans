package oceans.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

public class CommonMethod {

    /**
     * 获取用户真实的IP地址
     * @param request
     * @return
     */
    public static String getRequestIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 从redis从获取个人信息数据
     * @param token 个人信息的token
     * @param restTemplate 进行请求的分发
     * @return 个人信息数据
     */
    public static JSONObject getUserData(String token, RestTemplate restTemplate) {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://base-soa/redis/get?key={1}", String.class, token);
        String body = entity.getBody();
        if (body != null) {
            return JSON.parseObject(body);
        } else {
            return null;
        }
    }
}
