package org.javaonfly.tutorial.saga.inventorymanagement.aggregrator;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.javaonfly.tutorial.saga.coreapi.command.OrderReviewCommand;
import org.javaonfly.tutorial.saga.coreapi.event.OrderReviewedEvent;

@Aggregate
public class InventoryAgrregrator {

	@AggregateIdentifier
	private String inventoryId;
	private String productId;
	private Long qty=10L;
	private String orderId;
	

	@CommandHandler
	public InventoryAgrregrator(OrderReviewCommand orderRevieweCommand) {
		boolean isOrderApproved = !(orderRevieweCommand.getQty() > this.qty);
		System.out.println("javaonFly :: Reviewing the Order :: " + orderRevieweCommand);
		System.out.println("JavaOnFly :: Inventory has required quantity? " + isOrderApproved);
		AggregateLifecycle.apply(new OrderReviewedEvent(orderRevieweCommand.getInventoryId(),orderRevieweCommand.getProductId(),
				orderRevieweCommand.getOrderId(), orderRevieweCommand.getProductType(), orderRevieweCommand.getPrice(),
				orderRevieweCommand.getQty(), orderRevieweCommand.getTotalPrice(), orderRevieweCommand.getOrderStatus(),
				isOrderApproved));
	}

	@EventSourcingHandler
	protected void on(OrderReviewedEvent orderReviewedEvent) {
		this.inventoryId = orderReviewedEvent.getInventoryId();
		this.orderId = orderReviewedEvent.getOrderId();
		this.productId = orderReviewedEvent.getProductId();
		this.qty = orderReviewedEvent.isApproved()?qty - orderReviewedEvent.getQty():qty;
		
	}

}
