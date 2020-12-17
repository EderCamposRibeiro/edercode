package br.com.eder.store.models;

import java.math.BigDecimal;

public class Payment {
	
	private BigDecimal value;

	public Payment(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	

}
