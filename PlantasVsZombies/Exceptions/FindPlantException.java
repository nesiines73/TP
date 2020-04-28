package Exceptions;

@SuppressWarnings("serial")
public class FindPlantException extends CommandParserException {
private static String noplant = "Unknown plant name : ";
	public FindPlantException(String plant) {
		super(noplant + plant);
	}

}
