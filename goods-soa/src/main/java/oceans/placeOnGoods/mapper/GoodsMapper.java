package oceans.placeOnGoods.mapper;

import oceans.placeOnGoods.model.Goods;
import oceans.util.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//标注数据访问层,从xml的namespace注入bean，注入到Service层
public interface GoodsMapper {

    /**
     * 增加
     *
     * @param goods
     */
    void insertGoods(Goods goods);

    /**
     * 删除
     *
     * @param goods
     */
    void deleteGoods(Goods goods);

    /**
     * 批量删除
     *
     * @param goods
     */
    void deleteBatch(Goods goods);

    /**
     * 修改
     *
     * @param goods
     */
    void updateGoods(Goods goods);

    /**
     * 单项详情
     *
     * @param goods
     * @return
     */
    Goods selectGoods(Goods goods);


    /**
     * 分页列表页
     *
     * @param page
     * @return
     */
    List<Goods> findGoodsPage(PageBean<Goods> page);

    /**
     * 分页总记录数
     *
     * @param page
     * @return
     */
    int findGoodsPageCount(PageBean<Goods> page);

}



