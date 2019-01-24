package service;

public class MinLengthValidator implements Validator{

	@Override
	public boolean validate(String str) {
		return str.length() > 0;
	}
	

}
