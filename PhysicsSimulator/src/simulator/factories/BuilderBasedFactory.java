package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	List<Builder<T>> _builders;
	List<JSONObject> _FactoryElements = new ArrayList<JSONObject>();
	public BuilderBasedFactory(List<Builder<T>> builders){
		this._builders = builders;
		for(Builder<T> _builders : _builders) _FactoryElements.add(_builders.getBuilderInfo());
	}
	
	@Override
	public T createInstance(JSONObject info)  {
		for(Builder<T> b : _builders) {
			T d  = b.createInstances(info);
			if(d != null) {
				return d;
			}
		}
		throw new IllegalArgumentException();		
	}

	@Override
	public List<JSONObject> getInfo() {
		return _FactoryElements;
		}
	}