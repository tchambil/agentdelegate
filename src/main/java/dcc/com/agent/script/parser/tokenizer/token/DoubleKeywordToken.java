package dcc.com.agent.script.parser.tokenizer.token;

import dcc.com.agent.script.intermediate.FloatTypeNode;
import dcc.com.agent.script.intermediate.TypeNode;


public class DoubleKeywordToken extends TypeKeywordToken {

  public String toString(){
    return "double";
  }

  public TypeNode getTypeNode(){
    return FloatTypeNode.one;
  }

}
