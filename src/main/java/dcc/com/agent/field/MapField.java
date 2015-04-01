package dcc.com.agent.field;

import org.json.JSONException;
import org.json.JSONObject;


import dcc.com.agent.script.intermediate.IntegerTypeNode;
import dcc.com.agent.script.intermediate.MapTypeNode;
import dcc.com.agent.script.intermediate.Symbol;
import dcc.com.agent.script.intermediate.SymbolTable;
import dcc.com.agent.script.intermediate.TypeNode;
import dcc.com.agent.script.runtime.value.IntegerValue;
import dcc.com.agent.script.runtime.value.MapValue;
import dcc.com.agent.script.runtime.value.Value;

public class MapField extends Field {

    public MapField(SymbolTable symbolTable, String name) {
        this.symbol = new Symbol(symbolTable, name, MapTypeNode.one);
        this.label = name;
    }

    public MapField(SymbolTable symbolTable, String name, String label) {
        this.symbol = new Symbol(symbolTable, name, MapTypeNode.one);
        this.label = label;
    }

    public MapField(SymbolTable symbolTable, String name, String label, String description, String compute) {
        this.symbol = new Symbol(symbolTable, name, MapTypeNode.one);
        this.label = label;
        this.description = description;
        this.compute = compute;
    }

    public Field clone() {
        return new MapField(symbol.symbolTable, symbol.name, label, description, compute);
    }

    public Object getDefaultValue() {
        return null;
    }

    public Value getDefaultValueNode() {
        return new MapValue();
    }

    public TypeNode getType() {
        return MapTypeNode.one;
    }

    public static Field fromJson(SymbolTable symbolTable, JSONObject fieldJson) {
        String type = fieldJson.optString("type");
        if (type == null || !type.equals("map"))
            return null;
        String name = fieldJson.has("name") ? fieldJson.optString("name") : null;
        String label = fieldJson.has("label") ? fieldJson.optString("label") : null;
        String description = fieldJson.has("description") ? fieldJson.optString("description") : null;
        String compute = fieldJson.has("compute") ? fieldJson.optString("compute") : null;
        return new MapField(symbolTable, name, label, description, compute);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", "int");
        if (symbol.name != null)
            json.put("name", symbol.name);
        if (label != null)
            json.put("label", label);
        if (description != null)
            json.put("description", description);
        if (compute != null)
            json.put("compute", compute);
        return json;
    }

    public String toString() {
        return "[Map field symbol: " + symbol + " label: " + label +
                " description: '" + description + "'" + " compute: (" + compute + ")" +
                "]";
    }

}
