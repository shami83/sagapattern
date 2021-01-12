package org.javaonfly.tutorial.saga.coreapi.event;
import org.javaonfly.tutorial.saga.coreapi.OrderStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderReviewedEvent {
	
	private final String inventoryId;
	private final String productId;
	private final String orderId;
	private final String productType;
	private final Long price;
	private final Long totalPrice;
	private final Long qty;
	private final String orderStatus;
	private boolean approved;

    public OrderReviewedEvent(String inventoryId,String productId,String orderId, String itemType, Long price, Long qty,Long totalPrice,String orderStatus,boolean approved) {
    	this.inventoryId = inventoryId;
    	this.productId = productId;
    	this.orderId = orderId;
        this.productType = itemType;
        this.price = price;
        this.orderStatus = OrderStatus.ORDER_REVIEWED.getStatusValue();
        this.qty= qty;
        this.totalPrice = totalPrice;
        this.approved=approved;
    }
    
	

}
