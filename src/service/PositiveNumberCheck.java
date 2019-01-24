package service;

import java.math.BigDecimal;

public class PositiveNumberCheck implements Validator {

	@Override
	public boolean validate(String str) {
		if (new BigDecimal(str).compareTo(BigDecimal.ZERO) > 0) {
			return true;
		}
		return false;
	}
}
