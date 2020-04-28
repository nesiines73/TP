package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class MassLosingBodyBuilder extends Builder<Body> {
		private	String Typetag = "mlb";
		private String desc = "Cuerpo que pierde masa";
		private String id;
		private Vector vectp;
		private Vector vectv;
		private double mass;
	
	public MassLosingBodyBuilder(){
		super.typeTag = this.Typetag;
		super.desc = this.desc;
	}
	@Override
	protected Body createTheInstace(JSONObject data) {
		
		 id = data.getString("id");
		double[] p  = jsonArrayTodoubleArray(data.getJSONArray("pos"));
		double[] v = jsonArrayTodoubleArray(data.getJSONArray("vel"));
		 vectp = new Vector(p);
		 vectv = new Vector(v);
		 mass = data.getDouble("mass");
		Vector ac = new Vector(0);
		return  new Body(id,vectv,ac,vectp,mass);	
		}

	private double[] jsonArrayTodoubleArray(JSONArray jsonArray) {
		double [] a;
		a = new double[jsonArray.length()];
		for(int i = 0;i < jsonArray.length();i++)
			a[i] = jsonArray.getDouble(i);
		return a;
	}
	protected JSONObject createData() {
		JSONObject data2 = new JSONObject();
		data2.put("id", "Id del cuerpo");
		data2.put("pos","Vector con la posicion del cuerpo");
		data2.put("vel", "Velocidad del cuerpo");
		data2.put("mass", "Masa del cuerpo");
		data2.put("freq", "Frecuencia del cuerpo");
		data2.put("factor", "Factor");
		return data2;
	}
}
