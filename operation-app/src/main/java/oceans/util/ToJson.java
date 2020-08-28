package oceans.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by leaves on 2018/2/9.
 */
public class ToJson<T> {

    private Map<String ,Object> basePageObj = PagebeanTool.process(null,"");
    private List<T> dataList = new ArrayList<>();
    private String resultCode;//1成功，0失败
    private String resultMsg;//信息
    private Object resultObj = new Object();

    public JSONObject process(){
        JSONObject json = new JSONObject();
        json.put("basePageObj", getBasePageObj());
        json.put("dataList", getDataList());
        json.put("resultMsg", getResultMsg());
        json.put("resultObj", getResultObj());
        json.put("resultCode", getResultCode());
        return json;
    }

    public Map<String, Object> getBasePageObj() {
        return basePageObj;
    }

    public void setBasePageObj(Map<String, Object> basePageObj) {
        this.basePageObj = basePageObj;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
}
