package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravition;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {
		private String Typetag = "nlug";
		private String desc = "Ley de Newton de la gravitacion universal";
	
	public NewtonUniversalGravitationBuilder() {
		super.typeTag = this.Typetag;
		super.desc = this.desc;
	}
	@Override
	protected GravityLaws createTheInstace(JSONObject data) {
		return  new NewtonUniversalGravition();
	}
	/*protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("data", "");
		return data;
	}*/

}
