package Exceptions;

@SuppressWarnings("serial")
public class LenthCommandException extends CommandParserException{
private static String lenthError = "El comando no acepta el siguiente numero de argumentos : ";
	public LenthCommandException(int length) {
		super(lenthError + length);
		// TODO Auto-generated constructor stub
	}

}
