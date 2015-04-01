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
import dcc.com.agent.script.runtime.value.BooleanValue;
import dcc.com.agent.script.runtime.value.FloatValue;
import dcc.com.agent.script.runtime.value.IntegerValue;
import dcc.com.agent.script.runtime.value.StringValue;
import dcc.com.agent.script.runtime.value.Value;
import dcc.com.agent.script.runtine.ScriptState;

public class SubtractNode extends BinaryExpressionNode {

    public SubtractNode(ExpressionNode leftNode, ExpressionNode rightNode) {
        super(leftNode, rightNode);
    }

    public Value evaluateExpression(ScriptState scriptState) throws AgentServerException {
        scriptState.countNodeExecutions();
        Value leftValueNode = leftNode.evaluateExpression(scriptState);
        Value rightValueNode = rightNode.evaluateExpression(scriptState);
        if (leftValueNode instanceof BooleanValue) {
            if (rightValueNode instanceof BooleanValue) {
                boolean leftValue = leftValueNode.getBooleanValue();
                boolean rightValue = rightValueNode.getBooleanValue();
                boolean sumValue = leftValue ^ rightValue;
                return BooleanValue.create(sumValue);
            } else if (rightValueNode instanceof IntegerValue) {
                long leftValue = leftValueNode.getLongValue();
                long rightValue = rightValueNode.getLongValue();
                long sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof FloatValue) {
                double leftValue = leftValueNode.getDoubleValue();
                double rightValue = rightValueNode.getDoubleValue();
                double sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof StringValue) {
                boolean leftValue = leftValueNode.getBooleanValue();
                String rightValue = rightValueNode.getStringValue();
                String sumValue = leftValue ? rightValue : "";
                return new StringValue(sumValue);
            } else
                return leftValueNode;
        } else if (leftValueNode instanceof IntegerValue) {
            if (rightValueNode instanceof BooleanValue) {
                long leftValue = leftValueNode.getLongValue();
                long rightValue = rightValueNode.getLongValue();
                long sumValue = leftValue - rightValue;
                return new IntegerValue(sumValue);
            } else if (rightValueNode instanceof IntegerValue) {
                long leftValue = leftValueNode.getLongValue();
                long rightValue = rightValueNode.getLongValue();
                long sumValue = leftValue - rightValue;
                return new IntegerValue(sumValue);
            } else if (rightValueNode instanceof FloatValue) {
                double leftValue = leftValueNode.getDoubleValue();
                double rightValue = rightValueNode.getDoubleValue();
                double sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof StringValue) {
                double leftValue = leftValueNode.getLongValue();
                String rightValue = rightValueNode.getStringValue();
                String sumValue = leftValue != 0 ? rightValue : "";
                return new StringValue(sumValue);
            } else
                return leftValueNode;
        } else if (leftValueNode instanceof FloatValue) {
            if (rightValueNode instanceof BooleanValue) {
                double leftValue = leftValueNode.getDoubleValue();
                double rightValue = rightValueNode.getDoubleValue();
                double sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof IntegerValue) {
                double leftValue = leftValueNode.getDoubleValue();
                double rightValue = rightValueNode.getDoubleValue();
                double sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof FloatValue) {
                double leftValue = leftValueNode.getDoubleValue();
                double rightValue = rightValueNode.getDoubleValue();
                double sumValue = leftValue - rightValue;
                return new FloatValue(sumValue);
            } else if (rightValueNode instanceof StringValue) {
                double leftValue = leftValueNode.getDoubleValue();
                String rightValue = rightValueNode.getStringValue();
                String sumValue = leftValue != 0 ? rightValue : "";
                return new StringValue(sumValue);
            } else
                return leftValueNode;
        } else if (leftValueNode instanceof StringValue) {
            if (rightValueNode instanceof BooleanValue) {
                String leftValue = leftValueNode.getStringValue();
                boolean rightValue = rightValueNode.getBooleanValue();
                String sumValue = rightValue ? leftValue : "";
                return new StringValue(sumValue);
            } else if (rightValueNode instanceof IntegerValue) {
                String leftValue = leftValueNode.getStringValue();
                long rightValue = rightValueNode.getLongValue();
                String sumValue = rightValue != 0 ? leftValue : "";
                return new StringValue(sumValue);
            } else if (rightValueNode instanceof FloatValue) {
                String leftValue = leftValueNode.getStringValue();
                double rightValue = rightValueNode.getDoubleValue();
                String sumValue = rightValue != 0.0 ? leftValue : "";
                return new StringValue(sumValue);
            } else if (rightValueNode instanceof StringValue) {
                String leftValue = leftValueNode.getStringValue();
                String rightValue = rightValueNode.getStringValue();
                // TODO: What should this really be: string - string??
                String sumValue = leftValue + rightValue;
                return new StringValue(sumValue);
            } else
                // TODO: Reconsider whether string plus null is only the string itself
                return leftValueNode;
        } else
            return leftValueNode;
    }
}
