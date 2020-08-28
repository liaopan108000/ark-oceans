package oceans.placeOnOrder.controller;

import oceans.placeOnOrder.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemMapper orderItemMapper;

}
