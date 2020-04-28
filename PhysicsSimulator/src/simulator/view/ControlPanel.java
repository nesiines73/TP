package simulator.view;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements SimulatorObserver {
	private Controller _ctrl;
	private JButton fichero;
	private JButton dialogo;
	private volatile Thread _thread;
	private JButton off;
	private JButton play;
	private JFormattedTextField dtTime;
	private JSpinner steps;
	private JSpinner delay;

	
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
		private void initGUI() {
			fichero = new JButton();
			ImageIcon open = new ImageIcon("resources/icons/open.png");
			fichero.setIcon(open);
			this.add(fichero);
			fichero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JFileChooser a = new JFileChooser();
					int selection = a.showOpenDialog(fichero);
					if(selection == JFileChooser.APPROVE_OPTION) {
						File input = a.getSelectedFile();
						FileInputStream in = null;
						try {
							in = new FileInputStream(input);
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(null, "Error", "Error Box" , 0);
						}
						_ctrl.reset();
						try {
						_ctrl.loadBodies(in);
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Error", "Error Box" , 0);
						}
					}
				}
			});
			
			this.add(Box.createRigidArea(new Dimension(15,0))); 
			ImageIcon atom = new ImageIcon("resources/icons/physics.png");
			dialogo = new JButton();
			dialogo.setIcon(atom);
			this.add(dialogo);
			
			dialogo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
 				List<JSONObject> laws = _ctrl.getGravityLawsFactory().getInfo();
				String[] law = new String[laws.size()];
				JSONObject[] lw = new JSONObject[laws.size()];
				for(int i = 0; i< laws.size();i++) {
					JSONObject aux = laws.get(i);
					law[i] = i+1 + ". " +aux.getString("desc")+"(" + aux.getString("type") + ")";
					lw[i] = aux;
				
				}
				
				String resp = (String) JOptionPane.showInputDialog(dialogo, "Select gravity laws to be used", "Selector", JOptionPane.DEFAULT_OPTION, null,law, law[0]);
				if(resp != null) {
					int pos =Integer.parseInt("" + resp.charAt(0));
			
				_ctrl.setGravityLaws(lw[pos-1]);
				}
			}
				
			});
			this.add(Box.createRigidArea(new Dimension(15,0))); 
			ImageIcon run = new ImageIcon("resources/icons/run.png");
			play = new JButton();
			play.setIcon(run);
			this.add(play);
			this.add(Box.createRigidArea(new Dimension(15,0)));
		

			ImageIcon stop = new ImageIcon("resources/icons/stop.png");
			JButton pause = new JButton();
			pause.setIcon(stop);
			this.add(pause);	
			this.add(Box.createRigidArea(new Dimension(15,0))); 
		
			delay = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
			this.add(new JLabel("Delay: "));
			this.add(delay);
			this.add(Box.createRigidArea(new Dimension(15,0)));
			
			
			steps = new JSpinner(new SpinnerNumberModel(10000,0,1000000,100));
			this.add(new JLabel("Steps: "));
			this.add(steps);
			this.add(Box.createRigidArea(new Dimension(15,0)));
			
			this.add(new JLabel("DeltaTime: "));
		
			
			dtTime = new JFormattedTextField ();
			this.add(dtTime);
			this.add(Box.createRigidArea(new Dimension(15,0)));
			
			off = new JButton();
			ImageIcon exit = new ImageIcon("resources/icons/exit.png");
			off.setIcon(exit);
			this.add(off);
			off.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					int opcion = JOptionPane.showConfirmDialog(off, "Seguro que quieres salir?", "exit", 0);
					if(opcion == 0) System.exit(0);	
			}});
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
 				if(_thread == null) {
 					
					try {
 					
 			
 					enable();
 					_thread = new Thread(new Runnable() {
 						public void run() {
 							
 							_ctrl.setDeltaTime((double)dtTime.getValue());				
 			 				run_sim((int) steps.getValue(), Long.parseLong(delay.getValue().toString()));
 			 				_thread = null;
 						}
 					}
 							
 							
 							);
 					_thread.start();
 				}catch(Exception e) {
 					off.setEnabled(true);
 					JOptionPane.showMessageDialog(null, "Error", "Error Box" , 0);
 				}
 				}
 				
				
			}
			});
			pause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if(_thread != null) _thread.interrupt();
					enable();
					
				}});
		}		
// other private/protected methods
// ...
		public void enable() {
			play.setEnabled(!play.isEnabled());
			delay.setEnabled(!delay.isEnabled());
			dialogo.setEnabled(!dialogo.isEnabled());
			fichero.setEnabled(!fichero.isEnabled());
			dtTime.setEnabled(!dtTime.isEnabled());
			steps.setEnabled(!steps.isEnabled());
			
		}
		@SuppressWarnings("static-access")
		private void run_sim(int n, long delayy) {
			while ( n>0 && !_thread.isInterrupted()) {
				try {
					_ctrl.run(1);
					_thread.sleep(delayy);
				
					
				} catch (Exception e) {
				return ;
			
				}
				n--;
			}
			//en la hebra de swing!! invokeLater
			enable();
	}
	// SimulatorObserver methods
	// ...	
		@Override
		public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					dtTime.setValue(dt);
				}
				});
			
		
		}
		@Override
		public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					dtTime.setValue(dt);
				}
				});
		}
		@Override
		public void onBodyAdded(List<Body> bodies, Body b) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onAdvance(List<Body> bodies, double time) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onDeltaTimeChanged(double dt) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					dtTime.setValue(dt);
				}
				});
		
		}
		@Override
		public void onGravityLawChanged(String gLawsDesc) {
			// TODO Auto-generated method stub
			
		}
}
