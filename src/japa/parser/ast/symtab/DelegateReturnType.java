package japa.parser.ast.symtab;

public class DelegateReturnType implements SymtabType {

	String name;

	public DelegateReturnType(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
