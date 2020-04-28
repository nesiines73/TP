package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
		 String Typetag = "ftcg";
		 String desc = "Cayendo hacia el centro";
	
		public FallingToCenterGravityBuilder() {
			super.typeTag = this.Typetag;
			super.desc = this.desc;
		}		
				

	@Override
	protected GravityLaws createTheInstace(JSONObject JSONObject) {
		return new FallingToCenterGravity();
	}
	/*protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("data", "");
		return data;
	}*/
}
