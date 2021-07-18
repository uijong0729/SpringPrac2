package com.example.demo.order;

public record Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
	
	public int calculatePrice() {
		return itemPrice - discountPrice;
	}
	
	@Override
	public String toString() {
		return """
				<Order>
				Id = %d
				name = %s
				price = %d
				""".formatted(memberId, itemName, calculatePrice());
	}
}
