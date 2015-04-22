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
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.script.runtine.ScriptState;
import dcc.com.agent.util.ListMap;

import java.util.List;

public class ScriptNode extends Node {
    public BlockStatementNode blockNode;
    public TypeNode returnType;
    public String functionName;
    public ListMap<String, Symbol> parameters;

    public ScriptNode(BlockStatementNode blockNode) {
        this(null, null, null, blockNode);
    }

    public ScriptNode(TypeNode returnType, String functionName, ListMap<String, Symbol> parameters,
                      BlockStatementNode blockNode) {
        this.returnType = returnType;
        this.functionName = functionName;
        this.parameters = parameters;
        this.blockNode = blockNode;
    }

    public boolean checkArgumentTypes(List<TypeNode> argumentTypes) {
        int numArgs = argumentTypes.size();
        int numParameters = parameters.size();
        if (numArgs == numParameters) {
            for (int i = 0; i < numParameters; i++) {
                TypeNode parameterType = parameters.get(i).type;
                TypeNode argumentType = argumentTypes.get(i);
                if (!parameterType.isCompatibleType(argumentType))
                    // No match on this parameter's type and argument type
                    return false;
            }

            // Everything matches for this function
            return true;
        } else
            // No match because number of parameters and arguments do not match
            return false;
    }
    public void run(ScriptState scriptState, List<Value> argumentValues) throws AgentServerException {
        // Initialize parameter values from arguments
        int numParameters = parameters == null ? 0 : parameters.size();
        int numArguments = argumentValues == null ? 0 : argumentValues.size();
        if (numParameters != numArguments)
            throw new AgentServerException("Incorrect number of arguments for function '" + functionName + "' - expected " + numParameters + " but got " + numArguments);
        SymbolValues symbolValues = null;
        for (int i = 0; i < numParameters; i++) {
            Symbol parameterSymbol = parameters.get(i);
            if (symbolValues == null) {
                String categoryName = parameterSymbol.symbolTable.categoryName;
                symbolValues = new SymbolValues(categoryName);
                scriptState.categorySymbolValues.put(categoryName, symbolValues);
            }
            Value argumentValue = argumentValues.get(i);

            // Initialize the variable
            symbolValues.put(parameterSymbol, argumentValue);
        }
        // TODO: Deal with parameter values
        // - Add a paramter
        // - Add initial values
        blockNode.run(scriptState);
    }
}
