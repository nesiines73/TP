package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {
	PhysicsSimulator _sim;
	Factory<Body> _bodiesFactory;
	Factory<GravityLaws> _LawsFactory;
	
	public Controller(PhysicsSimulator _sim,Factory<Body> _bodiesFactory,Factory<GravityLaws> _LawsFactory) {
		this._sim = _sim;
		this._bodiesFactory = _bodiesFactory;
		this._LawsFactory = _LawsFactory;
	}
	public void reset() {
		_sim.reset();
	}
	public void setDeltaTime(double dt) {
		_sim.setDeltaTime(dt);
	}
	public void addObserver(SimulatorObserver o) {
		_sim.addObserver(o);		
	}
	
	public void run(int n) {
		for(int i = 0; i< n; ++i) _sim.advance();
	}
	public Factory<GravityLaws> getGravityLawsFactory(){
		return this._LawsFactory;
	}
	public void setGravityLaws(JSONObject info) {
		_sim.setGravityLaws(_LawsFactory.createInstance(info));
	}
	public void loadBodies(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInput.getJSONArray("bodies");
		for(int i=0; i < bodies.length(); ++i) {
			_sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		}
	}
	
	public void run(int steps, OutputStream out) {
		PrintStream p = (out == null) ? null : new PrintStream(out);
		p.append("{" + "\"states\": " + "[" );
		for(int i = 0;i < steps-1;i++) {
		p.append(_sim.toString() + ",");
		_sim.advance();
		
	}
		_sim.advance();
		p.append(_sim.toString() + "]  }");
		out = p;
	}		
}
