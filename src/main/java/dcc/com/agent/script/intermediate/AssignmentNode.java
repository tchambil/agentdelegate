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
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.script.runtine.ScriptState;

public class AssignmentNode extends ExpressionNode {
    public ReferenceNode variable;
    public ExpressionNode node;

    public AssignmentNode(ReferenceNode variable, ExpressionNode node) {
        this.variable = variable;
        this.node = node;
    }

    public Value evaluateExpression(ScriptState scriptState) throws AgentServerException {
        scriptState.countNodeExecutions();
        Value nodeValueNode = node.evaluateExpression(scriptState);
        variable.putValue(scriptState, nodeValueNode);
        //scriptState.categorySymbolValues.get(variable.symbol.symbolTable.categoryName).put(variable.symbol, nodeValueNode);
        return nodeValueNode;
    }

}
