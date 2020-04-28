package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.control.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	// ...
	Controller _ctrl;	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JToolBar toolBar = new JToolBar();
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		ControlPanel control = new ControlPanel(_ctrl);
		toolBar.add(control);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);	
		JPanel secondPanel = new JPanel();
		Viewer v = new Viewer(_ctrl);
		BodiesTable b = new BodiesTable(_ctrl);
		BoxLayout layout = new BoxLayout(secondPanel, BoxLayout.Y_AXIS);
		b.setPreferredSize(new Dimension(850, 550));
		v.setPreferredSize(new Dimension(850, 950));
		secondPanel.setLayout(layout);
		secondPanel.add(b);
		secondPanel.add(v);
		
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		mainPanel.add(new StatusBar(_ctrl),BorderLayout.PAGE_END);
		 	
		super.setSize(1000, 950);
		super.setVisible(true);
	}
	// other private/protected methods
	//
}