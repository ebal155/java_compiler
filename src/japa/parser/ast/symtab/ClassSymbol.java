package japa.parser.ast.symtab;

public class ClassSymbol extends ScopedSymbol implements SymtabType {

	public ClassSymbol(String name, Scope enclosingScope, int line) {
		super(name,null,enclosingScope, line);
	}

}
