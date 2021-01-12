package org.javaonfly.turorial.saga.ordermanagement.demo.aggregator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.javaonfly.tutorial.saga.coreapi.command.OrderCancelledCommand;
import org.javaonfly.tutorial.saga.coreapi.command.OrderConfirmedCommand;
import org.javaonfly.tutorial.saga.coreapi.command.OrderPlacedCommand;
import org.javaonfly.tutorial.saga.coreapi.event.OrderCancelledEvent;
import org.javaonfly.tutorial.saga.coreapi.event.OrderConfirmedEvent;
import org.javaonfly.tutorial.saga.coreapi.event.OrderPlacedEvent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Aggregate
public class OrderAggregrator {

	@AggregateIdentifier
	private String orderId;
	private String productId;
	private String productType;
	private Long price;
	private Long totalPrice;
	private Long qty;
	private String orderStatus;

	@CommandHandler
	public OrderAggregrator(OrderPlacedCommand orderPlacedCommand) {
		System.out.println("javaonFly :: Placing the Order :: " + orderPlacedCommand);
		AggregateLifecycle.apply(new OrderPlacedEvent(orderPlacedCommand.getOrderId(),
				orderPlacedCommand.getProductType(), orderPlacedCommand.getPrice(), orderPlacedCommand.getQty(),
				orderPlacedCommand.getTotalPrice(), orderPlacedCommand.getProductId()));
	}

	@EventSourcingHandler
	protected void on(OrderPlacedEvent orderPlacedEvent) {
		this.orderId = orderPlacedEvent.getOrderId();
		this.productType = orderPlacedEvent.getProductType();
		this.price = orderPlacedEvent.getPrice();
		this.totalPrice = orderPlacedEvent.getTotalPrice();
		this.qty = orderPlacedEvent.getQty();
		this.orderStatus = orderPlacedEvent.getOrderStatus();
	}

	@CommandHandler
	public void on(OrderCancelledCommand orderCancelledCommand) {
		// TODO:: Necessary rollback from DB if handling or anything related to Local
		// transaction
		// then broad casting Ordercancelled Event to other system if they want to do
		// any compensating transactions.
		System.out.println("javaonFly :: Cacelling the Order :: " + orderCancelledCommand);
		AggregateLifecycle.apply(
				new OrderCancelledEvent(orderCancelledCommand.getOrderId(), orderCancelledCommand.getProductType(),
						orderCancelledCommand.getPrice(), orderCancelledCommand.getQty(),
						orderCancelledCommand.getTotalPrice(), orderCancelledCommand.getProductId()));
	}

	@EventSourcingHandler
	protected void on(OrderCancelledEvent orderCancelledEvent) {
		this.orderId = orderCancelledEvent.getOrderId();
		this.productType = orderCancelledEvent.getProductType();
		this.price = orderCancelledEvent.getPrice();
		this.totalPrice = orderCancelledEvent.getTotalPrice();
		this.qty = orderCancelledEvent.getQty();
		this.orderStatus = orderCancelledEvent.getOrderStatus();
	}

	@CommandHandler
	public void on(OrderConfirmedCommand orderConfirmedCommand) {
		// TODO:: Necessary stuff, if handling or anything related to Local
		// transaction
		// then broad casting Orderconfirmed Event to other system if they want to do
		// any transactions.
		System.out.println("javaonFly :: Confirming the Order :: " + orderConfirmedCommand);
		AggregateLifecycle.apply(
				new OrderConfirmedEvent(orderConfirmedCommand.getOrderId(), orderConfirmedCommand.getProductType(),
						orderConfirmedCommand.getPrice(), orderConfirmedCommand.getQty(),
						orderConfirmedCommand.getTotalPrice(), orderConfirmedCommand.getProductId()));
	}

	@EventSourcingHandler
	protected void on(OrderConfirmedEvent orderConfirmedEvent) {
		this.orderId = orderConfirmedEvent.getOrderId();
		this.productType = orderConfirmedEvent.getProductType();
		this.price = orderConfirmedEvent.getPrice();
		this.totalPrice = orderConfirmedEvent.getTotalPrice();
		this.qty = orderConfirmedEvent.getQty();
		this.orderStatus = orderConfirmedEvent.getOrderStatus();
	}

}
