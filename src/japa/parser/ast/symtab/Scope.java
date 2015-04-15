package japa.parser.ast.symtab;

public interface Scope {
	public String getScopeName();
	public Scope getEnclosingScope();
	public void define(Symbol symbol);
	public Symbol resolve(String name);
}
