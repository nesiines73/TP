package Logic;
import Logic.Objects.*;
import Logic.Lists.*;
import java.util.Random;
public class Game {
	public final int dimX =4;
	 public	final int dimY=8;
	 public static final int solinicio = 50;
	 private int numerozombies;
	 private Random aleatorio;
	 private String Difficulty;
	 private SunflowerList Sunflower;
	 private PeaShooterList PeaShooter;
	 private SuncoinManager Suncoin;
	 private ZombieList Zombie;
	 private ZombieManager Zombis;
	 int numZom;
		public Game(String Difficulty, Random aleatorio) {
			this.Difficulty = Difficulty;
			this.aleatorio = aleatorio;
			 PeaShooter = new PeaShooterList();
			 Sunflower = new SunflowerList();
			this.Difficulty = getDiff();
			Zombis = new ZombieManager(this.Difficulty,this.aleatorio);
			 Zombie = new ZombieList(Zombis.getnumeroZomb());
			 Suncoin = new SuncoinManager();
			numerozombies= Zombis.getnumeroZomb();
			 
			
		}
		public void updateturnZombie() {
			Zombie.updatetun();
		}
		public int getMuertos() {
			return Zombie.zombiesmuertos();
			}
	
		public void reset() {
			 PeaShooter = new PeaShooterList();
			 Sunflower = new SunflowerList();
			Zombis = new ZombieManager(this.Difficulty,this.aleatorio);
			 Zombie = new ZombieList(Zombis.getnumeroZomb());
			 Suncoin = new SuncoinManager();
			numerozombies= Zombis.getnumeroZomb();
		}
		public void bajarvidaZombie() {
			for(int i = 0;i < dimX;i++) {
				for(int j = 0; j <dimY;j++) {
			PeaShooter pea =  PeaShooter.getPos(i,j);
			if(pea != null) {
				Zombie.bajarvida(i, j);
			}
				}
			}
		}
		public int getnumerozombies() {
			return this.numerozombies;
		}
		public String getDiff() {
			if(Difficulty.equals("HARD") || Difficulty.equals("MEDIUM")|| Difficulty.equals("EASY"))
			return this.Difficulty;
			else {
				Difficulty = "EASY";
				return this.Difficulty;
			}
		}
		public void GotZombie() {
			int y = 7;
			Zombie zom;
			int z = Zombis.getfrecuenciamax(), x;
			if(Zombis.Aleatorio(z) && this.numerozombies > 0 ) {
				
				x = Zombis.getXZombie();
				zom = Zombie.getPos(x, y);
				while(zom != null) {
					x = Zombis.getXZombie();
					zom = Zombie.getPos(x, y);
				}
				Zombie.addZ(x, dimY);
				this.numerozombies--;
				}
			}
		public void addZombie() {
			Zombie.getPos(7, Zombis.getXZombie());
		}
		public void ActualizarGirasoles() {
			Sunflower.changesoles();
		}
		public int ManageSol() {
			int Sol =Suncoin.generarsoles(Sunflower.getsoles());
			return Sol;
		}
		public int getsolManager() {
			return Suncoin.getSoles();
		}
		public boolean mover() {
			int cont = Zombie.getcont();
			for(int i = 0; i < cont; i++) {
			int	x = Zombie.getx(i);
			int	y = Zombie.gety(i);
			PeaShooter pea =  PeaShooter.getPos(x, y-1);
			 Sunflower sun = Sunflower.getPos(x, y-1);
			 Zombie zom = Zombie.getPos(x, y-1);
			 Zombie zum = Zombie.getPos(x, y);
			 if(zum != null && y == 0 && Zombie.getvida(i)>0)
				 return true;
			 else if(pea == null && sun == null && zom == null && Zombie.getvida(i) > 0 && Zombie.geturno(i)) {
				 Zombie.moverZombie(i);
			 }
			 else if(pea != null && Zombie.getvida(i) > 0) {
				 PeaShooter.bajarvida(x, y-1);
			 }
			 else if(sun !=null && Zombie.getvida(i) > 0) {
				 Sunflower.bajarvida(x, y-1);
			 }
			}
			return false;
		}
		public void add(String type,int x,int y) { //TODO PASAR STRING , X , Y. d:
			PeaShooter pea =  PeaShooter.getPos(x, y);
			 Sunflower sun = Sunflower.getPos(x, y);
			 Zombie zom = Zombie.getPos(x, y);
			if(type.equals("P")) {
				if(pea == null && sun == null && zom == null) {
					if(Suncoin.restarSoleP()){
					PeaShooter.addP(x, y);
					}
					else System.out.println("No dispone de soles suficientes");
				}
				else System.out.println("Posicion No valida");
				}
			else if(type.equals("S")) {
				if(pea == null && sun == null && zom == null) {
				if(Suncoin.restarSoles()) {
				Sunflower.addS(x,y);
			}
			else System.out.println("Nos dispone de soles suficentes");
		}
				else System.out.println("Posicion No valida");
			}
		}
	 public String getpos(int x,int y) {
		 PeaShooter pea =  PeaShooter.getPos(x, y);
		 Sunflower sun = Sunflower.getPos(x, y);
		 Zombie zom = Zombie.getPos(x, y);
		 if(pea != null) {
			 return "P" + " [" + pea.intToString() + "]";
		 }
		 else if(sun != null)
			 return "S" + " [" + sun.intToString() + "]";
		 else if(zom != null)
			 return "Z"+ " [" + zom.intToString() + "]";
		 return " ";
	 }
}