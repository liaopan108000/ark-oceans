package oceans.placeOnGoods.controller;

import com.alibaba.fastjson.JSONObject;
import oceans.placeOnGoods.service.GoodsService;
import oceans.util.PageBean;
import oceans.util.PagebeanTool;
import oceans.util.ToJson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private GoodsService goodsService;


    /**
     * 列表分页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/findGoodsPage.do")
    @ResponseBody
    public void findGoodsPage(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String pageNo = "";//页数
        String pageSize = "";//条数
        String userToken = "";//userToken

        OutputStream out = null;
        JSONObject json = new JSONObject();
        ToJson<Map> toJson = new ToJson<Map>();
        try {
            out = response.getOutputStream();
            String requestStr = IOUtils.toString(request.getInputStream());
            if (StringUtils.isEmpty(requestStr)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("解析参数失败");
                throw new Exception();
            }
            JSONObject jsonObject = JSONObject.parseObject(requestStr);

            if (jsonObject.get("pageNo") != null) {
                pageNo = jsonObject.get("pageNo").toString();
            }
            if (StringUtils.isEmpty(pageNo)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("页码为空!");
                throw new Exception();
            }
            if (jsonObject.get("pageSize") != null) {
                pageSize = jsonObject.get("pageSize").toString();
            }
            if (StringUtils.isEmpty(pageSize)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("条数为空!");
                throw new Exception();
            }
            if (jsonObject.get("userToken") != null) {
                userToken = jsonObject.get("userToken").toString();
            }
            if (StringUtils.isEmpty(userToken)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("userToken为空!");
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }
            return;
        }

        try {
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> map = new HashMap<>();

            map.put("pageNo", pageNo);
            map.put("pageSize", pageSize);

            String param = JSONObject.toJSONString(map);

            data = goodsService.findGoodsPage(param, pageSize, pageNo);

            List<Map<String, Object>> rowList = (List<Map<String, Object>>) data.get("rows");

            Map<String, Object> process = new HashMap<>();

            if (null != data && data.size() > 0) {

                //分页开始
                PageBean<Map<String, Object>> pageBean = new PageBean<>();
                pageBean.setList(rowList);
                pageBean.setTotalRecords(Integer.parseInt(data.get("total").toString()));
                pageBean.setPageNo(Integer.parseInt(pageNo));
                pageBean.setPageSize(Integer.parseInt(pageSize));
                process = PagebeanTool.process(pageBean, pageNo);

                toJson.setBasePageObj(process);
                toJson.setResultMsg("请求成功");
                toJson.setResultCode("1");
            } else {
                toJson.setResultCode("0");
                toJson.setResultMsg("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            toJson.setResultCode("0");
            toJson.setResultMsg("获取数据失败");
        } finally {
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectGoods.do")
    @ResponseBody
    public void selectGoods(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //初始化
        String goodsId = "";//商品id
        String userToken = "";//userToken

        OutputStream out = null;
        JSONObject json = new JSONObject();
        ToJson<Map> toJson = new ToJson<Map>();
        try {
            out = response.getOutputStream();
            String requestStr = IOUtils.toString(request.getInputStream());
            if (StringUtils.isEmpty(requestStr)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("解析参数失败");
                throw new Exception();
            }
            JSONObject jsonObject = JSONObject.parseObject(requestStr);
            if (jsonObject.get("goodsId") != null) {
                goodsId = jsonObject.get("goodsId").toString();
            }
            if (StringUtils.isEmpty(goodsId)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("商品id为空!");
                throw new Exception();
            }
            if (jsonObject.get("userToken") != null) {
                userToken = jsonObject.get("userToken").toString();
            }
            if (StringUtils.isEmpty(userToken)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("userToken为空!");
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }
            return;
        }

        try {
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> map = new HashMap<>();

            map.put("id", goodsId);

            String param = JSONObject.toJSONString(map);

            data = goodsService.selectGoods(param);

            if (null != data && data.size() > 0) {

                toJson.setResultObj(data);
                toJson.setResultMsg("请求成功");
                toJson.setResultCode("1");
            } else {
                toJson.setResultCode("0");
                toJson.setResultMsg("请求失败");
            }

        } catch (
                Exception e)

        {
            e.printStackTrace();
            toJson.setResultCode("0");
            toJson.setResultMsg("获取数据失败");
        } finally

        {
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 删除
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteGoods.do")
    @ResponseBody
    public void deleteGoods(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //初始化
        String goodsId = "";//商品id
        String userToken = "";//userToken

        OutputStream out = null;
        JSONObject json = new JSONObject();
        ToJson<Map> toJson = new ToJson<Map>();
        try {
            out = response.getOutputStream();
            String requestStr = IOUtils.toString(request.getInputStream());
            if (StringUtils.isEmpty(requestStr)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("解析参数失败");
                throw new Exception();
            }
            JSONObject jsonObject = JSONObject.parseObject(requestStr);
            if (jsonObject.get("goodsId") != null) {
                goodsId = jsonObject.get("goodsId").toString();
            }
            if (StringUtils.isEmpty(goodsId)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("商品id为空!");
                throw new Exception();
            }
            if (jsonObject.get("userToken") != null) {
                userToken = jsonObject.get("userToken").toString();
            }
            if (StringUtils.isEmpty(userToken)) {
                toJson.setResultCode("0");
                toJson.setResultMsg("userToken为空!");
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                logger.error(e1.getMessage());
            }
            return;
        }

        try {
            Map<String, Object> data = new HashMap<>();

            Map<String, Object> map = new HashMap<>();

            map.put("id", goodsId);

            String param = JSONObject.toJSONString(map);

            data = goodsService.deleteGoods(param);

            if (null != data && data.size() > 0) {

                toJson.setResultObj(data);
                toJson.setResultMsg("请求成功");
                toJson.setResultCode("1");
            } else {
                toJson.setResultCode("0");
                toJson.setResultMsg("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            toJson.setResultCode("0");
            toJson.setResultMsg("删除失败");
        } finally {
            json = toJson.process();
            try {
                if (out != null) {
                    out.write(json.toString().getBytes("UTF-8"));
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
