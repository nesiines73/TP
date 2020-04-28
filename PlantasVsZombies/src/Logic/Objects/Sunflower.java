package Logic.Objects;

public class Sunflower {
	public static final int DAYOS = 1;
	public static final int RESS = 1;
	public static final int COSTESS = 25;
	public static final int SOLES = 10;
	private int vida;
	private int x;
	private int y;
	private int soles;
	public Sunflower(int x, int y, int soles) {
		this.vida = RESS;
		this.x = x;
		this.y = y;
		this.soles = soles;
	}
	public int getTurnosol() {
		return this.soles;
	}
	public int getSoles() {
		return SOLES;
	}
	public boolean comprobarPos(int a, int b) {
		if(a == x && b == y && vida > 0)
			return true;
		else return false;	
	}
	public void changesoles() {
		if(soles == 0)
			this.soles = 1;
		else if(soles == 1)
			this.soles = 2;
		else if (soles == 2)
			this.soles = 3;
		else if (soles == 3)
			this.soles = 4;
		else if (soles == 4)
			this.soles = 2;
	}
	public int getvida()
	{
		return this.vida;
	}
	public String intToString()
	{
		int vida = getvida();
		if(vida == 1)
			return "1";
		else if(vida == 2)
			return "2";
		else
			return "3";
	}
	public void restarvida() {
		this.vida -= DAYOS;
	}
}