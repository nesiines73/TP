package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	List<Builder<T>> _builders;
	List<JSONObject> _FactoryElements = new ArrayList();
	public BuilderBasedFactory(List<Builder<T>> builders){
		this._builders = builders;
		for(int i = 0; i < _builders.size(); i++) {
			_FactoryElements.add(_builders.get(i).getBuilderInfo());
		}		
	}
	@Override
	public T createInstance(JSONObject info)  {
		for(int i = 0; i < _builders.size(); i++) {
			Builder<T> b = _builders.get(i);
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