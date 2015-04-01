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

package dcc.com.agent.script.runtime.value;

import java.util.List;


import dcc.com.agent.agentserver.RuntimeException;
import dcc.com.agent.script.intermediate.BooleanTypeNode;
import dcc.com.agent.script.intermediate.TypeNode;
import dcc.com.agent.script.runtine.ScriptState;

public class BooleanValue extends Value {

    static public BooleanValue create(boolean value) {
        if (value)
            return TrueValue.one;
        else
            return FalseValue.one;
    }

    public TypeNode getType() {
        return BooleanTypeNode.one;
    }

    public Object getValue() {
        return false;
    }

    public boolean getBooleanValue() {
        return false;
    }

    public long getLongValue() {
        return 0;
    }

    public double getDoubleValue() {
        return 0.0;
    }

    public String getStringValue() {
        return Boolean.toString(false);
    }

    public Value getNamedValue(ScriptState scriptState, String name) throws RuntimeException {
        return super.getNamedValue(scriptState, name);
    }

    public Value getMethodValue(ScriptState scriptState, String name, List<Value> arguments) throws RuntimeException {
        return super.getMethodValue(scriptState, name, arguments);
    }

    public Value add(Value otherValue) {
        return BooleanValue.create(getBooleanValue() || otherValue.getBooleanValue());
    }

    public String toString() {
        return "<boolean-value-node>";
    }

    public String toText() {
        return toString();
    }

    public String toXml() {
        return toString();
    }

    public String getTypeString() {
        return "boolean";
    }

    public boolean equals(Value valueNode) {
        return valueNode instanceof BooleanValue && this == valueNode;
    }

    public Value negateValue() {
        return BooleanValue.create(!getBooleanValue());
    }

}
