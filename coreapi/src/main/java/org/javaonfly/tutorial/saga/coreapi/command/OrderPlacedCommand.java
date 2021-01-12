package org.javaonfly.tutorial.saga.coreapi.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.javaonfly.tutorial.saga.coreapi.OrderStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OrderPlacedCommand {
	
	@TargetAggregateIdentifier
	private final String orderId;
	private final String productId;
	private final String productType;
	private final Long price;
	private final Long totalPrice;
	private final Long qty;
    	 final String orderStatus;

    public OrderPlacedCommand(String orderId, String itemType, Long price,Long qty,String productId) {
        this.orderId = orderId;
        this.productId = productId;
        this.productType = itemType;
        this.price = price!=null && price>0?price:0L;// for sake of brevity force fully assign 0;
        this.orderStatus = OrderStatus.ORDER_PLACED.getStatusValue();
        this.qty= qty !=null && qty>0 ?qty:1L; // for sake of brevity force fully assign 0;
        this.totalPrice = calculateTotalPrice();
    }
    
    
   private Long calculateTotalPrice() {
	   return price*qty;
   }
   

}
