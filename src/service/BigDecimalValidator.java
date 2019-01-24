package service;

public class BigDecimalValidator extends AbstractValidator {
	
	Validator[] validators;
	
	public BigDecimalValidator(Validator[] validators) {
		this.validators = validators;
	}

	@Override
	public boolean validate(String str) {
		if(!assertNotNull(str)) {
			return false;
		}
		for(Validator element : validators) {
			if(!element.validate(str)) {
				return false;
			}
		}
		return true;
	}


}
