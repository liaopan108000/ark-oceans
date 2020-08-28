package oceans.placeOnGoods.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "goods-soa")
public interface GoodsService {


    @RequestMapping(value = "/goods/Goods/selectGoods", method = RequestMethod.GET)
    Map<String, Object> selectGoods(@RequestParam("param") String param);


    @RequestMapping(value = "/goods/Goods/deleteGoods", method = RequestMethod.GET)
    Map<String, Object> deleteGoods(@RequestParam("param") String param);


    @RequestMapping(value = "/goods/Goods/insertGoods", method = RequestMethod.GET)
    Map<String, Object> insertGoods(@RequestParam("param") String param);


    @RequestMapping(value = "/goods/Goods/updateGoods", method = RequestMethod.GET)
    Map<String, Object> updateGoods(@RequestParam("param") String param);


    @RequestMapping(value = "/goods/Goods/findGoodsPage", method = RequestMethod.GET)
    Map<String, Object> findGoodsPage(@RequestParam("param") String param, @RequestParam("pageSize") String pageSize, @RequestParam("pageNo") String pageNo);
}
