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

import org.apache.log4j.Logger;
import org.json.JSONException;


import dcc.com.agent.agentserver.AgentCondition;
import dcc.com.agent.agentserver.AgentConditionStatus;
import dcc.com.agent.agentserver.AgentInstance;
import dcc.com.agent.agentserver.AgentServerException;
import dcc.com.agent.agentserver.RuntimeException;
import dcc.com.agent.script.intermediate.SymbolException;
import dcc.com.agent.script.parser.ParserException;
import dcc.com.agent.script.parser.tokenizer.TokenizerException;
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.util.NameValue;

public class AgentActivityTriggerInputChanged extends AgentActivity {
    static final Logger log = Logger.getLogger(AgentActivityTriggerInputChanged.class);

    public AgentInstance dataSource;
    long triggerInterval;

    public AgentActivityTriggerInputChanged(AgentInstance agent, AgentInstance dataSource) throws AgentServerException {
        super(agent, -1, "AgentActivityTriggerInputChanged for data source " + dataSource.agentDefinition.name);
        this.dataSource = dataSource;
        this.triggerInterval = agent.getTriggerInterval();
        this.when = System.currentTimeMillis() + triggerInterval;

    }

    public long computeTriggerInterval() throws AgentServerException {
        triggerInterval = agent.evaluateExpressionLong(agent.triggerIntervalExpression);
        return triggerInterval;
    }

    public long computeTriggerTime() throws AgentServerException {
        triggerInterval = agent.evaluateExpressionLong(agent.triggerIntervalExpression);
        return System.currentTimeMillis() + triggerInterval;
    }

    public boolean performActivity() throws SymbolException, RuntimeException, AgentServerException, JSONException {
        startActivity();

        // Find out what time it is
        long now = System.currentTimeMillis();

        // Record time of inputs changed event
        agent.lastInputsChanged = now;

        // Need to capture input values
        boolean captureInputValues = true;

        // Evaluate all conditions for agent
        boolean allConditionsMet = true;
        for (NameValue<AgentCondition> agentConditionNameValue : agent.agentDefinition.conditions) {
            // Get the next agent condition
            AgentCondition agentCondition = agentConditionNameValue.value;

            // Ignore if disabled
            if (agentCondition.enabled) {
                // Get current condition status
                AgentConditionStatus status = agent.conditionStatus.get(agentCondition.name);

                // Remember time of that we checked the trigger condition
                status.checkTime = now;

                // Count check interval hits
                status.checkHits++;

                // Evaluate the condition's condition expression
                try {
                    // Run the condition's script
                    Value conditionValue = agent.evaluateExpression(agentCondition.condition, captureInputValues);
                    captureInputValues = false;
                    log.info("Condition \"" + agentCondition.condition + "\" = " + conditionValue.toString());

                    // Record the expression value
                    status.conditionValue = conditionValue.getBooleanValue();
                } catch (AgentServerException e) {
                    // TODO: Record an exception status for the condition misfire
                    gotException(e);
                    return false;
                }

                // This is only a hit if condition expression evaluates to true
                if (status.conditionValue) {
                    // Count condition hits
                    status.hits++;

                    // Record time of condition hit
                    status.time = now;
                }

                // Keep track of whether all conditions are met
                if (!status.conditionValue)
                    allConditionsMet = false;
            }
        }

        // Record time of inputs changed event
        agent.lastInputsChanged = now;

        // Run 'inputs_changed' script, but only all condition expression evaluated to true
        if (allConditionsMet) {
            // Record time that trigger is ready to fire
            agent.lastTriggerReady = now;

            // Fire trigger, but only if its trigger_interval has been reached
            long delta = now - agent.lastTriggered;
            if (delta >= triggerInterval) {
                agent.lastTriggered = now;

                // Optionally run the 'inputs_changed' script
                String scriptName = "inputs_changed";
                if (agent.agentDefinition.scripts.containsKey(scriptName)) {
                    try {
                        // Run the condition's script - no need to re-capture input values
                        log.info("Running " + agent.name + ".inputs_changed - delta: " + delta + " ms. > trigger_interval: \"" + agent.triggerIntervalExpression + "\" (" + triggerInterval + " ms.)");
                        Value returnValueNode = agent.runScript(scriptName, captureInputValues);
                        // TODO: Should we do anything with return value?
                    } catch (TokenizerException e) {
                        // TODO: Record an exception status for the condition misfire
                        gotException(e);
                        return false;
                    } catch (ParserException e) {
                        gotException(e);
                        return false;
                    } catch (RuntimeException e) {
                        gotException(e);
                        return false;
                    }
                } else {
                    // No 'inputs_changed' script - still do state capture

                    // Optionally capture output field values for data source inputs
                    if (captureInputValues)
                        agent.captureDataSourceOutputValues();

                    // Capture state, if changed
                    agent.captureState();

                    // Trigger dependent instances if output values of this instance changed
                    agent.checkpointOutput();
                }
            } else {
                log.info("Skipping " + agent.name + ".inputs_changed  since delta: " + delta + " ms. < trigger_interval: \"" + agent.triggerIntervalExpression + "\" (" + triggerInterval + " ms.)");
            }
        }

        finishActivity();
        return true;
    }

}
