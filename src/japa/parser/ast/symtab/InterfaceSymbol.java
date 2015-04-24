package japa.parser.ast.symtab;

import java.util.ArrayList;

public class InterfaceSymbol extends ScopedSymbol implements SymtabType{

	private ArrayList<String> methodsToImplement = new ArrayList<String>();
	
	public InterfaceSymbol(String name, SymtabType type, Scope enclosingScope, int line) {
		super(name, type, enclosingScope, line);
	}
	
	public void addMethodToImplement(String s) {
		methodsToImplement.add(s);
	}
	
	public ArrayList<String> getMethodsToImplement() {
		return methodsToImplement;
	}

}
