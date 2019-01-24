package service;

public class StringValidator extends AbstractValidator{
	
	private Validator[] validators;
	
	public StringValidator(Validator[] validators) {
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
