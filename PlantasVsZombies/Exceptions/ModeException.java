package Exceptions;

@SuppressWarnings("serial")
public class ModeException extends CommandParserException {
private static String NoMode = "Unkown print mode : ";
	public ModeException(String command) {
		super(NoMode+command);
		// TODO Auto-generated constructor stub
	}

}
