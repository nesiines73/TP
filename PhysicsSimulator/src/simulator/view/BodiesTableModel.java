package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;


import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
// ...
	private String[] columnas = {"Id", "Mass", "Position", "Velocity", "Acceleration"};
	private List<Body> _bodies;
		BodiesTableModel(Controller ctrl) {
			_bodies = new ArrayList<Body>();
			ctrl.addObserver(this);
		}
	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	@Override
	public int getColumnCount() {
			return columnas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnas[column].toString();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Body a = _bodies.get(rowIndex);
		if(columnIndex == 0) return a.getId();
		else if(columnIndex == 1)return a.getMass();
		else if(columnIndex == 2) return a.getPosition();
		else if(columnIndex == 3) return a.getVelocity();
		else return a.getAcceleration();
		
	}
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
			});
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
			});
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies,Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
			});
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
			});
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

}
