package Exceptions;

@SuppressWarnings("serial")
public class FindCommandException extends CommandParserException{
	private static String NoCommand = "Unknown command name : ";

	public FindCommandException(String command) {
		super(NoCommand + command);
	}

}
