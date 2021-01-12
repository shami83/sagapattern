package org.javaonfly.turorial.saga.ordermanagement.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.javaonfly.turorial.saga.ordermanagement.demo.dto.OrderPlacedDTO;
import org.javaonfly.turorial.saga.ordermanagement.demo.service.OrderService;
import org.javaonfly.tutorial.saga.coreapi.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/{productId}/{qty}")
	public String createOrder(@PathVariable() String productId, @PathVariable() Long qty) throws InterruptedException, ExecutionException {
		OrderPlacedDTO orderCreateDTO = new OrderPlacedDTO();
		orderCreateDTO.setOrderStatus(OrderStatus.ORDER_PLACED.getStatusValue());
		orderCreateDTO.setProductId(productId);
		orderCreateDTO.setPrice(200L);
		orderCreateDTO.setProductType("Pen Drive");
		orderCreateDTO.setQty(qty);
		CompletableFuture<String> value =  orderService.placeOrder(orderCreateDTO);
		return value.get();
	}
}
