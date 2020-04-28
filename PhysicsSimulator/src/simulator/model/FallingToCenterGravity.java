package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws {
	private final double g = -9.81;
	public void apply(List<Body> bodies) {
		for(int i = 0; i < bodies.size(); i++) {
			Body planet = bodies.get(i);
			
			planet.SetAccelerarion(planet.getPosition().direction().scale(g));
		}
}
}
