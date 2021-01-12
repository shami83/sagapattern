package org.javaonfly.tutorial.saga.coreapi.saga;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.javaonfly.tutorial.saga.coreapi.command.OrderCancelledCommand;
import org.javaonfly.tutorial.saga.coreapi.command.OrderConfirmedCommand;
import org.javaonfly.tutorial.saga.coreapi.command.OrderReviewCommand;
import org.javaonfly.tutorial.saga.coreapi.event.OrderCancelledEvent;
import org.javaonfly.tutorial.saga.coreapi.event.OrderConfirmedEvent;
import org.javaonfly.tutorial.saga.coreapi.event.OrderPlacedEvent;
import org.javaonfly.tutorial.saga.coreapi.event.OrderReviewedEvent;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderWorkflowSaga {

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderPlacedEvent orderPlacedEvent) {
		String inventoryId = UUID.randomUUID().toString();
		System.out.println("JavaOnfly :: Order Saga workflow Started");
		SagaLifecycle.associateWith("inventoryId", inventoryId);
		System.out.println("JavaOnFly ::: Printing order :: " + orderPlacedEvent);
		// send the command to distribute systems
		commandGateway.send(
				new OrderReviewCommand(inventoryId,orderPlacedEvent.getProductId(), orderPlacedEvent.getOrderId(), orderPlacedEvent.getProductType(),
						orderPlacedEvent.getPrice(), orderPlacedEvent.getQty(), orderPlacedEvent.getTotalPrice()));
	}

	@SagaEventHandler(associationProperty = "inventoryId")
	public void handle(OrderReviewedEvent orderReviewedEvent) {
		System.out.println("JavOnFly :: Saga Order Reviewd");
		SagaLifecycle.associateWith("orderId", orderReviewedEvent.getOrderId());
		if (orderReviewedEvent.isApproved()) {
			commandGateway.send(new OrderConfirmedCommand(orderReviewedEvent.getOrderId(),
					orderReviewedEvent.getProductType(), orderReviewedEvent.getPrice(), orderReviewedEvent.getQty(),
					orderReviewedEvent.getTotalPrice(), orderReviewedEvent.getProductId()));
		} else {
			commandGateway.send(new OrderCancelledCommand(orderReviewedEvent.getOrderId(),
					orderReviewedEvent.getProductType(), orderReviewedEvent.getPrice(), orderReviewedEvent.getQty(),
					orderReviewedEvent.getTotalPrice(),orderReviewedEvent.getProductId()));
		}

	

	}

	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderConfirmedEvent OrderConFirmedEvent) {
		SagaLifecycle.end();
	}

	@SagaEventHandler(associationProperty = "orderId")
	public void handle(OrderCancelledEvent orderCancelledEvent) {
		SagaLifecycle.end();
	}
}
