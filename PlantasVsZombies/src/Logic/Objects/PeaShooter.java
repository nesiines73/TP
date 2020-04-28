package Logic.Objects;

public class PeaShooter {
		public static final int DAYOP = 1;
		public static final int RESP= 3;
		public static final int COSTESP = 50;
		private int vida;
		private int x;
		private int y;
		public PeaShooter(int x, int y) {
			this.vida = RESP;
			this.x = x;
			this.y = y;
		}
		public boolean comprobarPos(int a, int b) {
			if(a == x && b == y && vida > 0)
				return true;
			else return false;	
		}
		public void restarvida() {
			this.vida -= DAYOP;
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
	}