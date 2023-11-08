package org.autoframework.web;

public enum TimeOuts {
	
	TIMEOUT_PICO(3),
	TIMEOUT_NANO(5),
	TIMEOUT_MICRO(10),
	TIMEOUT_MINI(15),
	TIMEOUT_SMALL(30),
	TIMEOUT_LONG(60),
	TIMEOUT_DOUBLE_LONG(120);
	
	private int value;

	TimeOuts(int value) {
		this.value = value;
	}
	
	public int getTimeOutValue() {
		return value;
	}
}
