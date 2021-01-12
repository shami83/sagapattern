package org.javaonfly.tutorial.saga.coreapi.event;


import org.javaonfly.tutorial.saga.coreapi.OrderStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderConfirmedEvent {
	
	
	private final String orderId;
    private final String productId;
    private final String productType;
    private final Long price;
    private final Long totalPrice;
    private final Long qty;
    private final String orderStatus;

    public OrderConfirmedEvent(String orderId, String itemType, Long price,Long qty,Long totalPrice,String productId) {
        this.orderId = orderId;
        this.productId=productId;
        this.productType = itemType;
        this.price = price;
        this.orderStatus = OrderStatus.ORDER_CONFIRMED.getStatusValue();
        this.qty= qty;
        this.totalPrice = totalPrice;
    }
    

}
