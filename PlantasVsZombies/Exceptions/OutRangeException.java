package Exceptions;

@SuppressWarnings("serial")
public class OutRangeException extends ExecuteException {
private static String OutRanged = "Posicion fuera del tablero";
	public OutRangeException() {
		super(OutRanged);
		// TODO Auto-generated constructor stub
	}

}
