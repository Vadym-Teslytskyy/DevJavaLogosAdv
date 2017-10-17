package ua.entity;

import java.util.ArrayList;
import java.util.List;

public enum OrderStatus {

	READY, IN_PROGRES, ACCEPTED, IS_PAYED;
	
	public static List<OrderStatus> toOrderStatusConverter(List<String> statuses) {
		List<OrderStatus> orderStatus = new ArrayList<>();
		for (String string : statuses) {
			orderStatus.add(OrderStatus.valueOf(string));
		}
		return orderStatus;
	}
}
