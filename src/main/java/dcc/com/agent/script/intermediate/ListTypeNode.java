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

import dcc.com.agent.script.runtime.value.ListValue;
import dcc.com.agent.script.runtime.value.Value;

public class ListTypeNode extends TypeNode {
    public static ListTypeNode one = new ListTypeNode(ObjectTypeNode.one);

    public TypeNode elementType;

    public ListTypeNode(TypeNode elementType) {
        this.elementType = elementType;
    }

    public Value create(List<Value> argumentValues) {
        return new ListValue(ObjectTypeNode.one, argumentValues);
    }

    public Value getDefaultValue() {
        return new ListValue(elementType, new ArrayList<Value>());
    }

    public String toString() {
        return "list<" + elementType.toString() + ">";
    }

    public boolean isCompatibleType(TypeNode other) {
        return other instanceof ListTypeNode || other.getClass() == ObjectTypeNode.class;
    }
}
