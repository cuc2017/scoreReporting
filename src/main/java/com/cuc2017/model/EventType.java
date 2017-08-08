package com.cuc2017.model;

public enum EventType {
	READY("Starting soon"), STARTED("Started"), GAVE_OVER("Game Over"), POINT_SCORED("Point Scored"), SCORED_BY(
			"Point Scored by"), HALF_TIME("Halftime"), TIME_OUT("Timeout");

	private final String name;

	private EventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
