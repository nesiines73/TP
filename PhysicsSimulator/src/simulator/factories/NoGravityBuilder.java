package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {
	private String Typetag = "ng";
	private String desc = "Sin Gravedad";
	
	public NoGravityBuilder() {
		super.typeTag = this.Typetag;
		super.desc = this.desc;
		
	}
	

	@Override
	protected GravityLaws createTheInstace(JSONObject JSONObject) {

		return new NoGravity();
	}
	/*protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("data", "");
		return data;
	}*/

}
