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

import org.json.JSONException;
import org.json.JSONObject;


import dcc.com.agent.script.intermediate.IntegerTypeNode;
import dcc.com.agent.script.intermediate.MoneyTypeNode;
import dcc.com.agent.script.intermediate.ObjectTypeNode;
import dcc.com.agent.script.intermediate.Symbol;
import dcc.com.agent.script.intermediate.SymbolTable;
import dcc.com.agent.script.intermediate.TypeNode;
import dcc.com.agent.script.runtime.value.FloatValue;
import dcc.com.agent.script.runtime.value.StringValue;
import dcc.com.agent.script.runtime.value.Value;

public class MoneyField extends Field {
  public double defaultValue;
  public double minValue;
  public double maxValue;
  public int nominalWidth;

  public MoneyField(SymbolTable symbolTable, String name){
    this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
    this.label = name;
    minValue = 0;
    maxValue = Double.MAX_VALUE;
  }

  public MoneyField(SymbolTable symbolTable, String name, String label){
    this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
    this.label = label;
    minValue = 0;
    maxValue = Double.MAX_VALUE;
  }

  public MoneyField(SymbolTable symbolTable, String name, String label, String description,
      double defaultValue, double minValue, double maxValue, int nominalWidth, String compute){
    this.symbol = new Symbol(symbolTable, name, IntegerTypeNode.one);
    this.label = label;
    this.description = description;
    this.defaultValue = defaultValue;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.nominalWidth = nominalWidth;
    this.compute = compute;
  }

  public Field clone(){
    return new MoneyField(symbol.symbolTable, symbol.name, label, description, defaultValue,
        minValue, maxValue, nominalWidth, compute);
  }

  public Object getDefaultValue(){
    return defaultValue;
  }

  public Value getDefaultValueNode(){
    return new FloatValue(defaultValue);
  }

  public TypeNode getType(){
    return MoneyTypeNode.one;
  }

  public static Field fromJson(SymbolTable symbolTable, JSONObject fieldJson){
    String type = fieldJson.optString("type");
    if (type == null || ! type.equals("money"))
      return null;
    String name = fieldJson.has("name") ? fieldJson.optString("name") : null;
    String label = fieldJson.has("label") ? fieldJson.optString("label") : null;
    String description = fieldJson.has("description") ? fieldJson.optString("description") : null;
    double defaultValue = fieldJson.has("default_value") ? fieldJson.optDouble("default_value") : 0;
    double minValue = fieldJson.has("min_value") ? fieldJson.optDouble("min_value") : Double.MIN_VALUE;
    double maxValue = fieldJson.has("max_value") ? fieldJson.optDouble("max_value") : Double.MAX_VALUE;
    int nominalWidth = fieldJson.has("nominal_width") ? fieldJson.optInt("nominal_width") : 0;
    String compute = fieldJson.has("compute") ? fieldJson.optString("compute") : null;
    return new MoneyField(symbolTable, name, label, description, defaultValue, minValue, maxValue, 
        nominalWidth, compute);
  }

  public JSONObject toJson() throws JSONException {
    JSONObject json = new JSONObject();
    json.put("type", "money");
    if (symbol.name != null)
      json.put("name", symbol.name);
    if (label != null)
      json.put("label", label);
    if (description != null)
      json.put("description", description);
    if (defaultValue != 0)
      json.put("default_value", defaultValue);
    if (minValue != 0)
      json.put("min_value", minValue);
    if (maxValue != Double.MAX_VALUE)
      json.put("max_value", maxValue);
    if (nominalWidth != 0)
      json.put("nominal_width", nominalWidth);
    if (compute != null)
      json.put("compute", compute);
    return json;
  }
  
  public String toString(){
    return "[Money field symbol: " + symbol + " label: " + label +
        " description: '" + description + "'" + " default value: " + defaultValue +
        " min value: " + minValue + " max value: " + maxValue +
        " nominal width: " + nominalWidth + " compute: (" + compute + ")" +
        "]";
  }
}
