package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	private GravityLaws _gravityLaws;
	private List<Body> _bodies = new ArrayList<Body>();
	private List<SimulatorObserver> _observerList = new ArrayList<SimulatorObserver>();        
	private double _dt;
	private double _time=0;
	
	public PhysicsSimulator(GravityLaws _gravityLaws,double _dt) {
		this._gravityLaws = _gravityLaws;
		this._dt = _dt;
		if(_gravityLaws == null || _dt <= 0) throw new IllegalArgumentException();
	}
	
	public void reset() {
		_time = 0;
		_bodies.clear();
		for(SimulatorObserver o : _observerList) o.onReset(_bodies, _time, _dt,_gravityLaws.toString() );
	}
	
	public void addObserver(SimulatorObserver o) {
		_observerList.add(o);
		 o.onRegister(_bodies, _time, _dt,_gravityLaws.toString());
	}
	
	public void setDeltaTime(double dt) {
		try {
			this._dt = dt;
			for(SimulatorObserver o : _observerList) {
				o.onDeltaTimeChanged(_dt);
			}
		}
		catch(Exception ex) {
			throw new IllegalArgumentException("El tiempo no tiene un valor valido");
		}
	}
	public void setGravityLaws(GravityLaws gravityLaws) {
			try{
				if(gravityLaws != null)
					this._gravityLaws = gravityLaws;
				else throw new IllegalArgumentException("Valor Nulo");
				for(SimulatorObserver o : _observerList) {
					o.onGravityLawChanged(gravityLaws.toString());
				}
			}
		catch(Exception ex) {
			throw new IllegalArgumentException("La gravityLaw no tiene un valor valido");
		}
		
	}

	public void addBody(Body b) {
		boolean existe = false;

		if(_bodies.contains(b)) {
			existe = true;
		
		}
		if(!existe) _bodies.add(b);
		else throw new IllegalArgumentException();
		for(SimulatorObserver o : _observerList) {
			o.onBodyAdded(_bodies, b);
		}
	}
	public void advance() {
		_gravityLaws.apply(_bodies);
		for(int i=0; i<_bodies.size();++i) {
				Body bodi = _bodies.get(i);
				bodi.move(_dt);
		}
		_time += _dt;
		for(SimulatorObserver o : _observerList) {
			o.onAdvance(_bodies, _time);
		}
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