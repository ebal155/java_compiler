package japa.parser.ast.symtab;

public class MethodSymbol extends ScopedSymbol {

	public MethodSymbol(String name, SymtabType type, Scope enclosingScope) {
		super(name, type, enclosingScope);
	}

}
