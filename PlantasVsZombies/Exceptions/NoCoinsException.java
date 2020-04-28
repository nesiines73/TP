package Exceptions;

@SuppressWarnings("serial")
public class NoCoinsException extends ExecuteException{
private static String NoCoins = "Not enough coins";
	public NoCoinsException() {
		super(NoCoins);
		// TODO Auto-generated constructor stub
	}
	

}
