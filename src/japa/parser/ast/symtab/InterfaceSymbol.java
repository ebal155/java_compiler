package japa.parser.ast.symtab;

public class InterfaceSymbol extends ScopedSymbol{

	public InterfaceSymbol(String name, SymtabType type, Scope enclosingScope, int line) {
		super(name, type, enclosingScope, line);
	}

}
