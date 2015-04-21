package japa.parser.ast.symtab;

public class BlockSymbol extends ScopedSymbol{

	public BlockSymbol(Scope enclosingScope, int line) {
		super(null,null,enclosingScope, line);
	}

}
