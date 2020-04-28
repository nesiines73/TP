package PlantasVsZombies;
import Logic.*;
import Control.*;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random aleatorio;
		// TODO Auto-generated method stub
		if(args.length == 2)
		 aleatorio = new Random(Integer.parseInt(args[1]));
		else  aleatorio = new Random();
		Game game = new Game (args[0], aleatorio);
		Controller c = new Controller(game);
		c.run();
		

	}

}
