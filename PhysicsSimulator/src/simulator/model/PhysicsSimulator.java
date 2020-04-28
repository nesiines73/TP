package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	private GravityLaws _gravityLaws;
	private List<Body> _bodies = new ArrayList();
	private double _dt;
	private double _time=0;
	
	public PhysicsSimulator(GravityLaws _gravityLaws,double _dt) {
		this._gravityLaws = _gravityLaws;
		this._dt = _dt;
		if(_gravityLaws == null || _dt <= 0) throw new IllegalArgumentException();
	}
	
	
	public void addBody(Body b) {
		boolean existe = false;
		Body a;
		for(int i = 0;i< _bodies.size();i++) {
			a = _bodies.get(i);
			if(a.id.equalsIgnoreCase (b.id)) existe = true; break;
		}
		if(!existe) _bodies.add(b);
		else throw new IllegalArgumentException();
	}
	public void advance() {
		_gravityLaws.apply(_bodies);
		for(int i=0; i<_bodies.size();++i) {
				Body bodi = _bodies.get(i);
				bodi.move(_dt);
		}
		_time += _dt;
	}
	public String toString() {
		String json = null;
		for(int i=0; i < _bodies.size()-1; ++i) {
			Body bodi = _bodies.get(i);
			if(json !=null)
			json = json + " " + bodi.toString() + ",";
			else json = bodi.toString() +",";
		}
		Body bodi = _bodies.get(_bodies.size()-1);
		json = json + " " + bodi.toString();
		return "{ \"time\": " + _time +  ", " +  "\"bodies\":" + " [ "+ json +"]" +"}";
}
}