package japa.parser.ast.symtab;

public class BlockSymbol extends ScopedSymbol{

	public BlockSymbol(Scope enclosingScope) {
		super(null,null,enclosingScope);
	}

}
