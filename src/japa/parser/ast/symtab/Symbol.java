package japa.parser.ast.symtab;

public abstract class Symbol {

	protected String name;
	protected SymtabType type;
	
	public Symbol(String name, SymtabType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public SymtabType getType() {
		return type;
	}
}
