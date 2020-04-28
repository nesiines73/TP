package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements SimulatorObserver {
// ...
	private JLabel _currTime;     // for current time
	private JLabel _currLaws;     // for gravity laws
	private JLabel _numOfBodies;  // for number of bodies
	private double time;
	private String GLawsDesc;
	private int NumbOfBodies;
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
}
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		
		_currTime = new JLabel("Time: " +time);
		_currLaws = new JLabel("Laws: " + GLawsDesc);
		_numOfBodies = new JLabel("Bodies: " + NumbOfBodies);
		
		this.add(_currTime);
		this.add(Box.createRigidArea(new Dimension(15,0))); 
		this.add(_numOfBodies);
		this.add(Box.createRigidArea(new Dimension(15,0))); 
		this.add(_currLaws);
}
// other private/protected methods
// ...
// SimulatorObserver methods
// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText("Time: " +time);
				_currLaws.setText("Laws: " + gLawsDesc);
				_numOfBodies.setText("Bodies: " + bodies.size());
			}
			});
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText("Time: " +time);
				_currLaws.setText("Laws: " + gLawsDesc);
				_numOfBodies.setText("Bodies: " + bodies.size());
			}
			});

	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText("Time: " +time);
				_numOfBodies.setText("Bodies: " + bodies.size());
			}
			});
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText("Time: " +time);
				_numOfBodies.setText("Bodies: " + bodies.size());
			}
			});

	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currLaws.setText("Laws: " + gLawsDesc);
			}
			});
		

	}
}