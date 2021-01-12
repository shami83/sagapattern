package org.javaonfly.tutorial.saga.coreapi;

public enum OrderStatus {

	ORDER_PLACED("ORDER_PLACED"), ORDER_REVIEWED("ORDER_REVIEWED"), ORDER_CONFIRMED("ORDER_CONFIRMED"), ORDER_CANCELLED("ORDER_CANCELLED");

	private String statusValue;

	OrderStatus(String status) {
		this.statusValue = status;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

}
