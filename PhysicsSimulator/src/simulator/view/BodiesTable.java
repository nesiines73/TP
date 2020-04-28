package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

@SuppressWarnings("serial")
public class BodiesTable extends JPanel {
	
	BodiesTable(Controller ctrl) {
		BodiesTableModel model = new BodiesTableModel(ctrl);
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),"Bodies",TitledBorder.LEFT, TitledBorder.TOP));
		JTable table = new JTable(model);
		table.setMaximumSize(new Dimension(850,100));
		JScrollPane scroll= new JScrollPane(table);
		scroll.setMaximumSize(new Dimension(850,200));
		this.add(scroll);
		
	}
	
}
