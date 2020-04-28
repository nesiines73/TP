package Exceptions;

@SuppressWarnings("serial")
public abstract class CommandParserException extends Exception{

	public CommandParserException(String msg) {
		super(msg);
	}
}
