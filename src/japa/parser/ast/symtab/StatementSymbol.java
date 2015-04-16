package japa.parser.ast.symtab;

public class StatementSymbol extends ScopedSymbol{

	public StatementSymbol(String name, Scope enclosingScope) {
		super(name,null,enclosingScope);
	}

}
