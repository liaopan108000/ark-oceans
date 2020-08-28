package oceans.placeOnOrder.mapper;

import oceans.placeOnOrder.model.OrderItem;
import oceans.util.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//标注数据访问层,从xml的namespace注入bean，注入到Service层
public interface OrderItemMapper {

    /**
     * 增加
     *
     * @param orderItem
     */
    void insertOrderItem(OrderItem orderItem);

    /**
     * 删除
     *
     * @param orderItem
     */
    void deleteOrderItem(OrderItem orderItem);

    /**
     * 修改
     *
     * @param orderItem
     */
    void updateOrderItem(OrderItem orderItem);

    /**
     * 单项详情
     * @param orderItem
     * @return
     */
    OrderItem selectOrderItem(OrderItem orderItem);

    /**
     * 查询列表详情
     *
     * @param orderItem
     * @return
     */
    List<OrderItem> selectOrderItemList(OrderItem orderItem);


    /**
     * 分页列表页
     *
     * @param page
     * @return
     */
    List<OrderItem> findOrderItemPage(PageBean<OrderItem> page);

    /**
     * 分页总记录数
     *
     * @param page
     * @return
     */
    int findOrderItemPageCount(PageBean<OrderItem> page);

}

