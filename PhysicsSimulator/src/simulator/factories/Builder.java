package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	String typeTag;
	String desc; 
	
	public T createInstances(JSONObject info) {
		T b = null;
		if((typeTag != null) && (typeTag.equalsIgnoreCase(info.getString("type")))) {
			try {
			b = createTheInstace(info.getJSONObject("data"));
			}catch(Exception ex) {
				throw new IllegalArgumentException();
			}
		}
		return b;
	}
	protected abstract T createTheInstace(JSONObject JSONObject);
	
	public JSONObject getBuilderInfo() {	
		JSONObject info = new JSONObject();
		info.put("type", typeTag);
		info.put("data", createData());
		info.put("desc", desc);
		return info;
	}
	protected JSONObject createData() {
		return new JSONObject();
	}
	//lo que se devuelve tiene esta forma:
	/* 
	 {
		 "type" : typeTag
		 "data" : este es dependiente del objeto a construir
		 "desc" : desc
	 }
	 */
}
