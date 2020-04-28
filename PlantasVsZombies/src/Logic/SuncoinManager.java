package Logic;
public class SuncoinManager {
	private int soles;
	public SuncoinManager(){
		this.soles = 100;
		
	}
	public boolean restarSoles() {
		if(soles-20 >=0 ) {
			this.soles -= 20;
			return true;
		}
		else return false;
	}
	public boolean restarSoleP() {
		if(soles-50 >= 0) {
		this.soles -= 50;
		return true;
		}
		else return false;
	}
	public int getSoles() {
		return this.soles;
	}
	public int generarsoles(int x){
		this.soles += x;
		return this.soles;
		
	}
}

