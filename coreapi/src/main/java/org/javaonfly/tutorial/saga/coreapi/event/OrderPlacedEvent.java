package org.javaonfly.tutorial.saga.coreapi.event;



import org.javaonfly.tutorial.saga.coreapi.OrderStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderPlacedEvent {
	
	
	private final String orderId;
	private final String productId;
	private final String productType;
	private final Long price;
	private final Long totalPrice;
	private final Long qty;
	private final String orderStatus;

    public OrderPlacedEvent(String orderId, String itemType, Long price,Long qty,Long totalPrice,String productId) {
        this.orderId = orderId;
        this.productId=productId;
        this.productType = itemType;
        this.price = price;
        this.orderStatus = OrderStatus.ORDER_PLACED.getStatusValue();
        this.qty= qty; // for sake of brevity force fully assign 0;
        this.totalPrice = totalPrice;
    }
  
}
