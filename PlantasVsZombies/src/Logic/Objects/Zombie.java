package Logic.Objects;

public class Zombie {

	public static final int DAYOS = 1;
	public static final int RESS = 5;
	public static final float MOV = 1;
	private int vida;
	private boolean turno;
	private int x;
	private int y;
	public Zombie(int x, int y,boolean turno) {
		this.vida = RESS;
		this.x = x;
		this.y = y;
		this.turno = turno;
	}
	public boolean getturno() {
		return this.turno;
	}
	public void cambiarturno() {
		if(turno == false)
			turno = true;
		else turno = false;
	}
	public void changesoles() {
		if(turno == false)
			this.turno = true;
		else this.turno = false;
	}
	public boolean comprobarPos(int a, int b) {
		if(a == x && b == y && vida > 0)
			return true;
		else return false;	
	}
	public int getvida() {
		return this.vida;
	}
	public String intToString(){
		int vida = getvida();
		if(vida == 1)
			return "1";
		else if(vida == 2)
			return "2";
		else if (vida == 3)
			return "3";
		else if(vida == 4)
			return "4";
		else return "5";
	}
	public void mover() {
		
		this.y -= MOV;
	}
	public int getY() {
		return this.y;
	}
	public int getX() {
		return this.x;
	}
	public int restarvida() {
	this.vida -= DAYOS;
		return this.vida;
	}
}
