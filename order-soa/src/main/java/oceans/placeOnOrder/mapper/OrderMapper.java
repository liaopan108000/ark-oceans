package oceans.placeOnOrder.mapper;

import oceans.placeOnOrder.model.Order;
import oceans.util.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//标注数据访问层,从xml的namespace注入bean，注入到Service层
public interface OrderMapper {

    /**
     * 增加
     *
     * @param order
     */
    void insertOrder(Order order);

    /**
     * 删除
     *
     * @param order
     */
    void deleteOrder(Order order);

    /**
     * 修改
     *
     * @param order
     */
    void updateOrder(Order order);

    /**
     * 单项详情
     * @param order
     * @return
     */
    Order selectOrder(Order order);

    /**
     * 查询列表详情
     *
     * @param order
     * @return
     */
    List<Order> selectOrderList(Order order);


    /**
     * 分页列表页
     *
     * @param page
     * @return
     */
    List<Order> findOrderPage(PageBean<Order> page);

    /**
     * 分页总记录数
     *
     * @param page
     * @return
     */
    int findOrderPageCount(PageBean<Order> page);

}

