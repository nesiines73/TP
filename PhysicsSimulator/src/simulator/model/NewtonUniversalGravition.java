package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravition implements GravityLaws {
	private final double G = 6.67E-11;
	private double f;
	private Vector F;
	private Vector FTotal;
	
	@Override
	public void apply(List<Body> bodies) {		
		for(int i = 0; i < bodies.size(); i++) {		
			Body planet = bodies.get(i);
			FTotal = new Vector(planet.getPosition().dim());
			for(int j = 0; j < bodies.size(); j++) {
				if(i != j) {
					Body planet2 = bodies.get(j);
					f = G*((planet.getMass()*planet2.getMass())/(planet2.getPosition().distanceTo(planet.getPosition())*(planet2.getPosition().distanceTo(planet.getPosition()))));
					F = planet2.getPosition().minus(planet.getPosition()).direction().scale(f);
					FTotal = FTotal.plus(F);
				}
			}
			if(planet.getMass() != 0) {
			planet.SetAccelerarion(FTotal.scale(1/planet.getMass()));
		}
		}
	}//poner lo de si la masa es cero lo ponemos a cero directamente

}
