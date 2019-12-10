package com.elifserttas.todolist.enumator;

public enum TodoType {
	WILL("Todo"), DOING("Start"), DONE("Finish");

	private final String displayValue;

	private TodoType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
