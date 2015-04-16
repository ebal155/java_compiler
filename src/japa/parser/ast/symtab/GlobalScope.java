package japa.parser.ast.symtab;

import java.util.HashMap;

public class GlobalScope implements Scope {

	private HashMap<String, Symbol> symbols = new HashMap<String, Symbol>();
	
	public GlobalScope() {
		define(new BuiltInTypeSymbol("int"));
		define(new BuiltInTypeSymbol("boolean"));
		define(new BuiltInTypeSymbol("float"));
		define(new BuiltInTypeSymbol("double"));
		define(new BuiltInTypeSymbol("byte"));
		define(new BuiltInTypeSymbol("short"));
		define(new BuiltInTypeSymbol("char"));
		
		define(new ClassSymbol("String",null));
		
	}
	
	public String getScopeName() {
		return "GlobalScope";
	}


	public void define(Symbol symbol) {
		symbols.put(symbol.getName(), symbol);
	}

	public Symbol resolve(String name) {
		return symbols.get(name);
	}

	@Override
	public Scope getEnclosingScope() {
		return null;
	}
	
}
