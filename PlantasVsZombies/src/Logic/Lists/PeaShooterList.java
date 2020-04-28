package Logic.Lists;
import Logic.Objects.PeaShooter;

public class PeaShooterList {
	public final int MAX = 32;
	private PeaShooter[] peaList;
	public int cont;
	
	public  PeaShooterList() {
		peaList = new PeaShooter[MAX];
		for (int i = 0;i<32;i++)
			peaList[i] = null;
		cont=0;
	}
	public  void addP(int x,int y) {
		PeaShooter pea = new PeaShooter(x,y);
		peaList[cont] = pea;
		cont++;
	}
	public PeaShooter[] getList(){
		return peaList;
	}
	public PeaShooter getPos(int x, int y) {
		for(int i = 0; i < cont; i++) {
		if(peaList[i].comprobarPos(x,y)) {
			return peaList[i];
		}
	}
		return null;
	}
	public void bajarvida(int x , int y) {
		int i = 0;
		while(i < cont) {
			if(peaList[i].comprobarPos(x, y)) {
				peaList[i].restarvida();
			}
				i++;
		}
	}
}
