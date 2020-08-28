package oceans.placeOnGoods.controller;

import com.alibaba.fastjson.JSON;
import oceans.placeOnGoods.mapper.GoodsMapper;
import oceans.placeOnGoods.model.Goods;
import oceans.util.PageBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class GoodsController {

    @Resource
    private GoodsMapper goodsMapper;


    /**
     * 后台商品列表分页
     *
     * @return
     */
    @RequestMapping(value = "/goods/Goods/findGoodsPage")
    public Map<String, Object> findGoodsPage(@RequestParam(value = "param") String param,@RequestParam(value = "pageSize") String pageSize,@RequestParam(value = "pageNo") String pageNo) {

        Goods goods = JSON.parseObject(param, Goods.class);

        PageBean<Goods> pageBean = new PageBean<>();
        pageBean.setBean(goods);
        pageBean.setPageSize(Integer.parseInt(pageSize));
        pageBean.setPagestart(Integer.parseInt(pageNo));

        //总条数
        int count = goodsMapper.findGoodsPageCount(pageBean);
        //分页数据
        List<Goods> list = goodsMapper.findGoodsPage(pageBean);

        //返回所有商品信息
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Goods good : list) {

            Map<String, Object> maps = new HashMap<>();
            maps.put("id", good.getId());
            maps.put("name", good.getName());
            maps.put("price", good.getPrice());
            maps.put("status", good.getStatus());

            mapList.add(maps);
        }
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("total", count);
        resMap.put("rows", mapList);
        return resMap;
    }


    /**
     * 商品详情
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/goods/Goods/selectGoods")
    public Map<String, Object> selectGoodsDetail(@RequestParam(value = "param") String param) {

        Map<String, Object> map = new HashMap<>();

        Goods goods = JSON.parseObject(param, Goods.class);
        Goods resGoods = goodsMapper.selectGoods(goods);

        if (null != resGoods) {
            map.put("id", resGoods.getId());
            map.put("name", resGoods.getName());
            map.put("price", resGoods.getPrice());
            map.put("status", resGoods.getStatus());
        }
        return map;
    }


    /**
     * 商品修改
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/goods/Goods/updateGoods")
    public Map<String, Object> updateGoods(@RequestParam(value = "param") String param) {

        Goods goods = JSON.parseObject(param, Goods.class);

        Map<String, Object> map = new HashMap<>();

        goodsMapper.updateGoods(goods);

        map.put("info", "1");
        map.put("inform", "成功");

        return map;
    }

    /**
     * 删除商品
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/goods/Goods/deleteGoods")
    public Map<String, Object> deleteGoods(@RequestBody Map<String,String> param) {
        Map<String, Object> resultMap = new HashMap<>();

        List<Goods> goodsBatch = new ArrayList<>();//商品

        List<String> id = Arrays.asList(param.get("id").split(","));

        for (String ids :id){
            Goods goods = new Goods();
            goods.setId(Long.valueOf(ids));
            goodsBatch.add(goods);
        }

        //批量删除
        Goods goods = new Goods();
        goods.setBatch(goodsBatch);
        goodsMapper.deleteBatch(goods);

        return resultMap;
    }

    /**
     * 商品新增
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/goods/Goods/insertGoods")
    public Map<String, Object> insertGoods(@RequestParam(value = "param") String param) {

        Goods goods = JSON.parseObject(param, Goods.class);

        Map<String, Object> map = new HashMap<>();

        goodsMapper.insertGoods(goods);

        map.put("info","1");
        map.put("inform","成功");
        return map;
    }

}
