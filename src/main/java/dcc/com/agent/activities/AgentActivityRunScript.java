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

package dcc.com.agent.activities;

import org.json.JSONException;


import dcc.com.agent.agentserver.AgentInstance;
import dcc.com.agent.agentserver.AgentServerException;
import dcc.com.agent.agentserver.RuntimeException;
import dcc.com.agent.script.intermediate.SymbolException;
import dcc.com.agent.script.parser.ParserException;
import dcc.com.agent.script.parser.tokenizer.TokenizerException;

public class AgentActivityRunScript extends AgentActivity {
    public String scriptName;

    public AgentActivityRunScript(AgentInstance agent, long when, String description, String scriptName) throws RuntimeException {
        super(agent, when, description);
        if (!agent.agentDefinition.scripts.containsKey(scriptName))
            throw new RuntimeException("Undefined script name, '" + scriptName + "' for agent " + agent.name);
        this.scriptName = scriptName;
    }

    public boolean performActivity() throws SymbolException, RuntimeException, AgentServerException, JSONException {
        startActivity();

        // Run the named script
        try {
            agent.runScript(scriptName);
        } catch (TokenizerException e) {
            gotException(e);
            return false;
        } catch (ParserException e) {
            gotException(e);
            return false;
        } catch (RuntimeException e) {
            gotException(e);
            return false;
        }

        if (scriptName.equals("init"))
            agent.ranInit = true;

        finishActivity();
        return true;
    }

}
