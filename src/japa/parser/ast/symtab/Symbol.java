package japa.parser.ast.symtab;

public abstract class Symbol {

	protected String name;
	protected SymtabType type;
	protected int line;
	
	public Symbol(String name, SymtabType type, int line) {
		this.name = name;
		this.type = type;
		this.line = line;
	}
	
	public String getName() {
		return name;
	}
	
	public SymtabType getType() {
		return type;
	}
	
	public int getBeginLine() {
		return this.line;
	}
}
