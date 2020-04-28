package Logic;
import java.util.Random;
public class ZombieManager {
	private int frecuencia;
	private int numeroAletorioSpawn;
	private Random aleatorio;
	private int numeroZomb;
	private int frecuenciamax;
	public final static int frecuenciaINSANE = 5;
	public final static int frecuenciaHARD= 3;
	public final static int frecuenciaEASY = 2;
	public final static int zombiesINSANE = 10;
	public final static int zombiesHARD = 5;
	public final static int zombiesEASY = 3;
	public final static int maxFIL = 5;
	

	public ZombieManager(String Difficulty,Random aleatorio) {
		this.aleatorio = aleatorio;
		numeroZomb = StringtoInt(Difficulty);
		frecuenciamax = StringtoInt2(Difficulty);
			
	}
	public int getfrecuenciamax() {
		return frecuenciamax;
	}
	public void getfrecuencia() {
		frecuencia = aleatorio.nextInt(10);
	}
	public int getnumeroZomb() {
		return this.numeroZomb;
	}
	public int getXZombie(){
		numeroAletorioSpawn = aleatorio.nextInt(maxFIL-1);
		return this.numeroAletorioSpawn;
	}
	public int StringtoInt(String Difficulty) {
		if(Difficulty.equals("HARD"))
			return zombiesHARD;
		else if (Difficulty.equals("INSANE"))
			return zombiesINSANE;
		else return zombiesEASY;
	}
	public int StringtoInt2(String Difficulty) {
		if(Difficulty.equals("HARD"))
			return frecuenciaHARD;
		else if (Difficulty.equals("INSANE"))
			return frecuenciaINSANE;
		else return frecuenciaEASY;
	}
public boolean Aleatorio(int x) {
	getfrecuencia();
	if(frecuencia <= x)
		return true;	
	else return false;

		
	
}
}
