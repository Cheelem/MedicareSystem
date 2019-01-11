package medicare.model;

import org.json.JSONObject;

public interface Model {
	public boolean loadFromJSON(JSONObject json);

	public JSONObject convertToJSON();
}
