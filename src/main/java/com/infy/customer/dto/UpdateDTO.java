package com.infy.customer.dto;

import javax.validation.constraints.NotNull;

import com.infy.customer.utility.validator.ValidateBalanceAmount;

public class UpdateDTO {
	@NotNull(message = "customer.balance.amount.required")
	@ValidateBalanceAmount(message = "customer.balance.amount.invalid")
	Double balanceAmount;

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
