package oceans.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestJsonUtil {


    /**
     * 获取Ajax请求的post请求的json数据，且传送的数据必须是json数据
     * @param request
     * @return
     */
    public static JSONObject getAjaxJsonData(HttpServletRequest request) throws Exception{
        InputStream intput = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(intput, "UTF-8");
        String value = IOUtils.toString(inputStreamReader);
        JSONObject json = JSONObject.parseObject(value);
        return json;
    }

    /**
     * 获取request请求的参数
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestData(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, Object> map = new HashMap<>();
        if (enumeration != null){
            while (enumeration.hasMoreElements()){
                String key = enumeration.nextElement();
                map.put(key, request.getParameter(key));
            }
        }
        return map;
    }

}
