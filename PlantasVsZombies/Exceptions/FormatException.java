package Exceptions;

@SuppressWarnings("serial")
public class FormatException extends CommandParserException {
private static String  Fexcp = "The following characters are not numbers : ";
	public FormatException(String msg) {
		super(Fexcp + msg);
		// TODO Auto-generated constructor stub
	}

}
