package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body{
	private double lossFactor;
	private double lossFrequency;
	private double c;
	public MassLossingBody(String id, Vector v, Vector a, Vector p, double m,double lossFactor,double lossFrequency) {
		super(id, v, a, p, m);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
	}
	public void move(double t) {
		while(c <= t) {
			super.move(lossFrequency);
			this.m = m*(1-lossFactor);
			c += lossFrequency;
		}
		c = 0.0;
	}

}
