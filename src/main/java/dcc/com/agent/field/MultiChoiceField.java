/**
 * Copyright 2012 John W. Krupansky d/b/a Base Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dcc.com.agent.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import dcc.com.agent.script.intermediate.IntegerTypeNode;
import dcc.com.agent.script.intermediate.ObjectTypeNode;
import dcc.com.agent.script.intermediate.StringTypeNode;
import dcc.com.agent.script.intermediate.Symbol;
import dcc.com.agent.script.intermediate.SymbolTable;
import dcc.com.agent.script.intermediate.TypeNode;
import dcc.com.agent.script.runtime.value.StringValue;
import dcc.com.agent.script.runtime.value.Value;

public class MultiChoiceField extends Field {
    public String defaultValue;
    public List<String> choices;
    public int nominalWidth;

    public MultiChoiceField(SymbolTable symbolTable, String name, String choices) {
        this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
        this.label = name;
        this.choices = Arrays.asList(choices.split(","));
    }

    public MultiChoiceField(SymbolTable symbolTable, String name, String label, String choices) {
        this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
        this.label = label;
        this.choices = Arrays.asList(choices.split(","));
    }

    public MultiChoiceField(SymbolTable symbolTable, String name, String label, String description,
                            String defaultValue, List<String> choices, int nominalWidth, String compute) {
        this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
        this.label = label;
        this.description = description;
        this.defaultValue = defaultValue;
        this.choices = choices;
        this.nominalWidth = nominalWidth;
        this.compute = compute;
    }

    public Field clone() {
        return new MultiChoiceField(symbol.symbolTable, symbol.name, label, description, defaultValue,
                choices, nominalWidth, compute);
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Value getDefaultValueNode() {
        return new StringValue(defaultValue);
    }

    public TypeNode getType() {
        return StringTypeNode.one;
    }

    public static Field fromJson(SymbolTable symbolTable, JSONObject fieldJson) {
        String type = fieldJson.optString("type");
        if (type == null || !type.equals("multi_choice_field"))
            return null;
        String name = fieldJson.has("name") ? fieldJson.optString("name") : null;
        String label = fieldJson.has("label") ? fieldJson.optString("label") : null;
        String description = fieldJson.has("description") ? fieldJson.optString("description") : null;
        String defaultValue = fieldJson.has("default_value") ? fieldJson.optString("default_value") : null;
        List<String> choices = new ArrayList<String>();
        if (fieldJson.has("choices")) {
            JSONArray choicesJson = fieldJson.optJSONArray("choices");
            int n = choicesJson.length();
            for (int i = 0; i < n; i++)
                choices.add(choicesJson.optString(i));
        }
        int nominalWidth = fieldJson.has("nominal_width") ? fieldJson.optInt("nominal_width") : 0;
        String compute = fieldJson.has("compute") ? fieldJson.optString("compute") : null;
        return new MultiChoiceField(symbolTable, name, label, description, defaultValue, choices,
                nominalWidth, compute);
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("type", "multi_choice");
        if (symbol.name != null)
            json.put("name", symbol.name);
        if (label != null)
            json.put("label", label);
        if (description != null)
            json.put("description", description);
        if (defaultValue != null)
            json.put("default_value", defaultValue);
        JSONArray choicesJson = new JSONArray();
        if (choices != null)
            for (String choice : choices)
                choicesJson.put(choice);
        json.put("choices", choicesJson);
        if (nominalWidth != 0)
            json.put("nominal_width", nominalWidth);
        if (compute != null)
            json.put("compute", compute);
        return json;
    }

    public String toString() {
        return "[Choice field symbol: " + symbol + " label: " + label +
                " description: '" + description + "'" + " default value: " + defaultValue +
                " nominal width: " + nominalWidth + " choices: " + choices.toString() +
                " compute: (" + compute + ")" +
                "]";
    }
}
