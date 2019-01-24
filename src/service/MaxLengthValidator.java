package service;

public class MaxLengthValidator implements Validator{

	@Override
	public boolean validate(String str) {
		return str.length() < 21;
	}
}
