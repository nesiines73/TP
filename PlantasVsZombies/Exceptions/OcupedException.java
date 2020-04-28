package Exceptions;

@SuppressWarnings("serial")
public class OcupedException extends ExecuteException {
	private static String NoPos = "Invalid Pos";
	public OcupedException() {
		super(NoPos);
	}
	
}
