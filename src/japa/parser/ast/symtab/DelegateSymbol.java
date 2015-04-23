package japa.parser.ast.symtab;

import java.util.ArrayList;

public class DelegateSymbol extends Symbol implements SymtabType{

	ArrayList<String> paramList = new ArrayList<String>();
	
	public DelegateSymbol(String name, SymtabType type, int line, ArrayList<String> paramList) {
		super(name, type, line);
		this.paramList = paramList;
	}
	
	public ArrayList<String> getParams() {
		return this.paramList;
	}

}
