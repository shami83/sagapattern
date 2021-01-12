package org.javaonfly.turorial.saga.ordermanagement.demo.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.javaonfly.turorial.saga.ordermanagement.demo.dto.OrderPlacedDTO;
import org.javaonfly.tutorial.saga.coreapi.command.OrderPlacedCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private CommandGateway commandGateway;

	public CompletableFuture<String> placeOrder(OrderPlacedDTO orderpalcedDTO) {
		System.out.println("JavaOnFly :: Order DTO" + orderpalcedDTO); 
					
		  return commandGateway.send(new
		  OrderPlacedCommand(UUID.randomUUID().toString(),
		  orderpalcedDTO.getProductType(), orderpalcedDTO.getPrice(),
		  orderpalcedDTO.getQty(), orderpalcedDTO.getProductId()));
		 
	}

}
