package simulator.model;

import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector v;
	protected Vector a;
	protected Vector p;
	protected double m;
	
	public Body(String id,Vector v,Vector a,Vector p,double m) {
		this.id = id;
		this.v = v;
		this.a = a;
		this.p = p;
		this.m = m;
	}
	public String getId() {
		return this.id;
	}
	public Vector getVelocity() {
		return new Vector(v);
	}
	public Vector getAcceleration() {
		return new Vector(a);
	}
	public Vector getPosition() {
		return new Vector(p);
	}
	public double getMass() {
		return m;		
	}
	public void setVelocity(Vector v) {
		this.v = new Vector(v);
	}
	public void SetAccelerarion(Vector a) {
		this.a = new Vector(a);
	}
	public void SetPosition(Vector p) {
		this.p = new Vector(p);
	}
	public void move(double t) {
		this.p = p.plus(v.scale(t).plus((a.scale((t*t)/2))));
		this.v =  v.plus(a.scale(t));
	}
	public String toString() {
		return "{  " + "\"id\": " + "\"" +id + "\"" + ", "+ "\"mass\": " + m + ", "+ "\"pos\": " + p +", " + "\"vel\": " + v + ", "+ "\"acc\": "+ a + " }";
	}
}

