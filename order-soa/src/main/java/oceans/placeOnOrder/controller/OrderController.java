package oceans.placeOnOrder.controller;

import oceans.placeOnOrder.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;


}
