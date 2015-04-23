package japa.parser.ast.symtab;

public class ClassType implements SymtabType{

	private String name;
	
	public ClassType(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
