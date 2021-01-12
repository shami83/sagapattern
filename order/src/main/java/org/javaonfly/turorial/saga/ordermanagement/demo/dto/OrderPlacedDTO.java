package org.javaonfly.turorial.saga.ordermanagement.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderPlacedDTO {

	private String orderId;
	private String productId;
	private String productType;
	private Long price;
	private Long totalPrice;
	private Long qty;
	private String orderStatus;

}
