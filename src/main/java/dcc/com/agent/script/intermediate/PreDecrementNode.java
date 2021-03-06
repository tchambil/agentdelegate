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


import dcc.com.agent.agentserver.AgentServerException;
import dcc.com.agent.agentserver.RuntimeException;
import dcc.com.agent.script.runtime.value.FalseValue;
import dcc.com.agent.script.runtime.value.FloatValue;
import dcc.com.agent.script.runtime.value.IntegerValue;
import dcc.com.agent.script.runtime.value.NullValue;
import dcc.com.agent.script.runtime.value.StringValue;
import dcc.com.agent.script.runtime.value.TrueValue;
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.script.runtine.ScriptState;

public class PreDecrementNode extends UnaryExpressionNode {

    public PreDecrementNode(ExpressionNode node) {
        super(node);
    }

    public Value evaluateExpression(ScriptState scriptState) throws AgentServerException {
        scriptState.countNodeExecutions();
        if (node instanceof VariableReferenceNode) {
            // Get current value of referenced variable
            VariableReferenceNode varRef = (VariableReferenceNode) node;
            Value originalValueNode = varRef.getValue(scriptState);

            // Decrement it and create value node with new value
            Value newValueNode = NullValue.one;
            if (originalValueNode instanceof FalseValue)
                newValueNode = originalValueNode;
            else if (originalValueNode instanceof TrueValue)
                newValueNode = FalseValue.one;
            else if (originalValueNode instanceof IntegerValue) {
                IntegerValue node = (IntegerValue) originalValueNode;
                newValueNode = new IntegerValue(node.value - 1);
            } else if (originalValueNode instanceof FloatValue) {
                FloatValue node = (FloatValue) originalValueNode;
                newValueNode = new FloatValue(node.value - 1.0);
            } else if (originalValueNode instanceof StringValue) {
                StringValue node = (StringValue) originalValueNode;
                // TODO: What should we really do here?
                String s = node.value;
                int len = s.length();
                if (len > 0)
                    s = s.substring(1);
                newValueNode = new StringValue(s);
            } else if (originalValueNode instanceof NullValue)
                newValueNode = new IntegerValue(-1);

            // Write new value back to variable store
            varRef.putValue(scriptState, newValueNode);

            // Return new value node
            return newValueNode;
        } else {
            // For non-variables, just return the expression value
            return node.evaluateExpression(scriptState);
        }
    }

}
