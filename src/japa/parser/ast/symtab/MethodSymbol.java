package japa.parser.ast.symtab;

import java.awt.List;
import java.util.ArrayList;

public class MethodSymbol extends ScopedSymbol {

	ArrayList<String> paramList = new ArrayList<String>();
	
	public MethodSymbol(String name, SymtabType type, Scope enclosingScope,int line, ArrayList<String> paramList) {
		super(name, type, enclosingScope, line);
		this.paramList = paramList;
	}
	
	public ArrayList<String> getParams() {
		return this.paramList;
	}

}
