package Logic.Lists;
import Logic.Objects.Sunflower;

public class SunflowerList {
	public final int MAX = 32;
	private Sunflower[] sunList;
	private int cont;
	
	public  SunflowerList() {
		sunList = new Sunflower[MAX];
		for (int i = 0;i<32;i++)
			sunList[i] = null;
		this.cont=0;
	}
	public  void addS(int x,int y) {
		int sol = 0;
		Sunflower sun = new Sunflower(x,y,sol);
		sunList[this.cont] = sun;
		this.cont++;
	}
	public Sunflower[] getList(){
		return sunList;
	}
	public Sunflower getPos(int x, int y) {
		for(int i = 0; i < this.cont; i++) {
		if(sunList[i].comprobarPos(x,y)) {
			return sunList[i];
		
		}
	}
		return null;
	}
	public int getsoles() {
	int aux=0,solestotales=0,produccion=10;
		for(int i =0;i<this.cont;i++) {
			if(sunList[i].getTurnosol() == 4 && sunList[i].getvida()>0)
			aux++;
		}
				for(int a = 0;a<aux;a++) 
					solestotales += produccion;
					
		
			return solestotales;
			
	}
	public void changesoles() {
		for(int a = 0;a<cont;a++) {
			sunList[a].changesoles();
			}
	}
	public void bajarvida(int x , int y) {
		int i = 0;
		while(i < this.cont) {
			if(sunList[i].comprobarPos(x, y)) {
				sunList[i].restarvida();
			}
				
			else {
				i++;
			}
		}
	}

}
