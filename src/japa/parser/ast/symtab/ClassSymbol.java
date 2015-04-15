package japa.parser.ast.symtab;

public class ClassSymbol extends ScopedSymbol implements SymtabType {

	public ClassSymbol(String name, Scope enclosingScope) {
		super(name,null,enclosingScope);
	}

}
