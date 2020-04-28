package Logic.Lists;
import Logic.Objects.Zombie;
public class ZombieList {
	private Zombie[] zomList;
	private int cont;
	
	public  ZombieList(int numZom) {
		zomList = new Zombie[numZom];
		for (int i = 0;i<numZom;i++)
			zomList[i] = null;
		this.cont=0;
	}
	public  void addZ(int x,int y) {
		boolean turno = false;
		Zombie zom = new Zombie(x,y,turno);
		zomList[this.cont] = zom;
		this.cont++;
	}
	public Zombie[] getList(){
		return zomList;
	}
	public Zombie getPos(int x, int y) {
		for(int i = 0; i < cont; i++) {
		if(zomList[i].comprobarPos(x,y)) {
			return zomList[i];
		
		}
	}
		return null;
	}
	public void updatetun() {
		for(int i = 0; i < cont;i++) {
			zomList[i].cambiarturno();
		}
	}
	public boolean geturno(int i) {
		return zomList[i].getturno();
		
	}
	public void moverZombie(int i) {
			zomList[i].mover();
	}
	public int getx(int i) {
		return	zomList[i].getX();
			
		}
	public int gety(int i) {
		return	zomList[i].getY();
			
		}
	public int getcont() {
		return cont;
	}
	public boolean buscar(int x , int y) {
		boolean found = false;
		int i = 0;
		while(!found && i < this.cont) {
			if(zomList[i].comprobarPos(x, y))
				found = true;
			else {
				found = false;
				i++;
			}
		}
		return found;
	}
	public int getvida(int i) {
		return zomList[i].getvida();
	}
	public int zombiesmuertos() {
		int a =0;
		for(int i = 0;i< this.cont;i++) {
			if(zomList[i].getvida() <= 0) {
				a++;
			}
		}
		return a;
	}
	/*public void actualizaLista(int x) {
		for(int i = 0;i<this.cont;i++) {
			if(zomList[i].getvida() == 0) {
				for(int j = i; j<this.cont-1;j++) {

					zomList[j] = zomList[j+1];
					this.cont--;
				}
			}
		}
			
	}*/
	public int buscarZ(int x , int y) {
		int a = 0;
	for(int i = 0;i< this.cont;i++) {
		if(x == zomList[i].getX() && zomList[i].getvida() > 0 && zomList[i].getY() > y)
			return i;
		else a++;
	}
	return a+1;
	}
		public void bajarvida(int x,int y) {
			int i = buscarZ(x, y);
			if(i < this.cont) {
				zomList[i].restarvida();
			}
			
		}
	}
	
