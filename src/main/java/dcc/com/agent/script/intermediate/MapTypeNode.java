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

package dcc.com.agent.script.intermediate;

import java.util.ArrayList;
import java.util.List;

import dcc.com.agent.script.runtime.value.FieldValue;
import dcc.com.agent.script.runtime.value.MapValue;
import dcc.com.agent.script.runtime.value.Value;

public class MapTypeNode extends TypeNode {
  public static MapTypeNode one = new MapTypeNode(StringTypeNode.one, ObjectTypeNode.one);

  public TypeNode keyType;
  public TypeNode entryType;

  public MapTypeNode(TypeNode keyType, TypeNode entryType){
    this.keyType = keyType;
    this.entryType = entryType;
  }

  public Value create(List<Value> argumentValues){
    return new MapValue(ObjectTypeNode.one, argumentValues);
  }
  
  public Value getDefaultValue(){
    return new MapValue(entryType, (List<Value>)(Object)new ArrayList<FieldValue>());
  }
  
  public String toString(){
    return "map<" + keyType.toString() + ", " + entryType.toString() + ">";
  }

  public boolean isCompatibleType(TypeNode other){
    return other instanceof MapTypeNode || other.getClass() == ObjectTypeNode.class;
  }

}
