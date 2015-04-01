package dcc.com.agent.script.parser.tokenizer.token;

import dcc.com.agent.script.intermediate.DateTypeNode;
import dcc.com.agent.script.intermediate.TypeNode;


public class DateKeywordToken extends TypeKeywordToken {

  public String toString(){
    return "date";
  }

  public TypeNode getTypeNode(){
    return DateTypeNode.one;
  }

}
