package japa.parser.ast.symtab;

import se701.A2SemanticsException;

public class ClassSymbol extends ScopedSymbol implements SymtabType {

	ClassSymbol parent;
	
	public ClassSymbol(String name, Scope enclosingScope, int line) {
		super(name,null,enclosingScope, line);
	}
	
	public void setParent(ClassSymbol parent) {
		//To prevent from setting the parent as itself
		if (parent != this) {
			this.parent = parent;
		}else{
			throw new A2SemanticsException("Classes cannot extend themselves");
		}
	}

	@Override
	public Symbol resolve(String name) {
		// if the symbol exists in the current scope, return it
		Symbol s = symbols.get(name);
		if (s != null) {
			return s;
		}

		// check superclass scope first
		if (parent != null) {
			if (parent.resolve(name) != null) {
				return parent.resolve(name);
			}
		}
		
		// otherwise look in the enclosing scope, if there is one
		if (enclosingScope != null) {
			return enclosingScope.resolve(name);
		}
		
		// otherwise it doesn't exist
		return null;
	}
}
