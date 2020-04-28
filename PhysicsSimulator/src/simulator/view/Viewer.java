package simulator.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class Viewer extends JComponent implements SimulatorObserver {
	// ...
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	public Viewer(Controller ctrl) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),"Viewer",TitledBorder.LEFT, TitledBorder.TOP));
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
	// TODO add border with title
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		
		addKeyListener(new KeyListener() {
		// ...
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyChar()) {
					case '-':
						_scale = _scale*1.1;
						break;
					case '+':
						_scale = Math.max(1000.0, _scale / 1.1);
						break;
					case '=':
						autoScale();
						break;
					case 'h':
						_showHelp = !_showHelp;
						break;
					default:
				}
				repaint();
		}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		});
	
		addMouseListener(new MouseListener() {
			// ...
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//use ’gr’ to draw not ’g’
		//calculate the center
		Color blue=new Color(0,0,255);
		Color red = new Color(255,0,0);
		Color black = new Color(0,0,0);
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		gr.setColor(red);
		gr.drawString("+", _centerX, _centerY);
		for(Body b: _bodies) {
			gr.setColor(blue);
			Vector pos = b.getPosition();
			double x = pos.coordinate(0);
			double y = pos.coordinate(1);	
			gr.fillOval( _centerX + (int) (x/_scale), _centerY - (int) (y/_scale), 10, 10 );
			gr.setColor(black);
			gr.drawString(b.getId(), _centerX + (int) (x / _scale), _centerY - (int) (y / _scale));
		}	
		if(_showHelp) {
			gr.setColor(red);
			gr.drawString("h: toogle help, +:zoom-in, -zoom-out, =: fit",10,30);
			gr.drawString("Scaling ratio : " + _scale,10,42);
		}
	}
	//other private/protected methods
	//...
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
			Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
		(double) getHeight()));
		_scale = max > size ? 4.0*max / size : 1.0;	
	}
	
	//SimulatorObserver methods
	//...
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
			});
	}
	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
			});
	}
	
	public void onBodyAdded(List<Body> bodies, Body b) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				autoScale();
				repaint();
			}
			});
	}
	
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
			});
	}
	
	public void onDeltaTimeChanged(double dt) {
	}
	
	public void onGravityLawChanged(String gLawsDesc) {
	}
}