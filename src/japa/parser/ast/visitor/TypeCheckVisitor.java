package japa.parser.ast.visitor;

import japa.parser.ast.BlockComment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.LineComment;
import japa.parser.ast.Node;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.TypeParameter;
import japa.parser.ast.body.AnnotationDeclaration;
import japa.parser.ast.body.AnnotationMemberDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.DelegateDeclaration;
import japa.parser.ast.body.EmptyMemberDeclaration;
import japa.parser.ast.body.EmptyTypeDeclaration;
import japa.parser.ast.body.EnumConstantDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.InitializerDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.ArrayAccessExpr;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CastExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.InstanceOfExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.IntegerLiteralMinValueExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.LongLiteralMinValueExpr;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.SuperExpr;
import japa.parser.ast.expr.SuperMemberAccessExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.EmptyStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.symtab.BuiltInTypeSymbol;
import japa.parser.ast.symtab.DelegateSymbol;
import japa.parser.ast.symtab.MethodSymbol;
import japa.parser.ast.symtab.Scope;
import japa.parser.ast.symtab.Symbol;
import japa.parser.ast.symtab.SymtabType;
import japa.parser.ast.symtab.TypeSymbol;
import japa.parser.ast.symtab.VariableSymbol;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;
import japa.parser.ast.type.WildcardType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se701.A2SemanticsException;

public class TypeCheckVisitor implements VoidVisitor<Object>{

	private void printModifiers(int modifiers) {
        if (ModifierSet.isPrivate(modifiers)) {
        }
        if (ModifierSet.isProtected(modifiers)) {
        }
        if (ModifierSet.isPublic(modifiers)) {
        }
        if (ModifierSet.isAbstract(modifiers)) {
        }
        if (ModifierSet.isStatic(modifiers)) {
        }
        if (ModifierSet.isFinal(modifiers)) {
        }
        if (ModifierSet.isNative(modifiers)) {
        }
        if (ModifierSet.isStrictfp(modifiers)) {
        }
        if (ModifierSet.isSynchronized(modifiers)) {
        }
        if (ModifierSet.isTransient(modifiers)) {
        }
        if (ModifierSet.isVolatile(modifiers)) {
        }
    }

    private void printMembers(List<BodyDeclaration> members, Object arg) {
        for (BodyDeclaration member : members) {
            member.accept(this, arg);
        }
    }

    private void printMemberAnnotations(List<AnnotationExpr> annotations, Object arg) {
        if (annotations != null) {
            for (AnnotationExpr a : annotations) {
                a.accept(this, arg);
            }
        }
    }

    private void printAnnotations(List<AnnotationExpr> annotations, Object arg) {
        if (annotations != null) {
            for (AnnotationExpr a : annotations) {
                a.accept(this, arg);
            }
        }
    }

    private void printTypeArgs(List<Type> args, Object arg) {
        if (args != null) {
            for (Iterator<Type> i = args.iterator(); i.hasNext();) {
                Type t = i.next();
                t.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    private void printTypeParameters(List<TypeParameter> args, Object arg) {
        if (args != null) {
            for (Iterator<TypeParameter> i = args.iterator(); i.hasNext();) {
                TypeParameter t = i.next();
                t.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    public void visit(Node n, Object arg) {
        throw new IllegalStateException(n.getClass().getName());
    }

    public void visit(CompilationUnit n, Object arg) {
    	
    	
        if (n.getPakage() != null) {
            n.getPakage().accept(this, arg);
        }
        if (n.getImports() != null) {
            for (ImportDeclaration i : n.getImports()) {
                i.accept(this, arg);
            }
        }
        if (n.getTypes() != null) {
            for (Iterator<TypeDeclaration> i = n.getTypes().iterator(); i.hasNext();) {
                i.next().accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    public void visit(PackageDeclaration n, Object arg) {
        printAnnotations(n.getAnnotations(), arg);
        n.getName().accept(this, arg);
    }

    public void visit(NameExpr n, Object arg) {
    }

    public void visit(QualifiedNameExpr n, Object arg) {
        n.getQualifier().accept(this, arg);
    }

    public void visit(ImportDeclaration n, Object arg) {
        if (n.isStatic()) {
        }
        n.getName().accept(this, arg);
        if (n.isAsterisk()) {
        }
    }

    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
    	if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());
        
        if (n.isInterface()) {
        	
        } else {
        	
        }

        printTypeParameters(n.getTypeParameters(), arg);

        if (n.getExtends() != null) {
            for (Iterator<ClassOrInterfaceType> i = n.getExtends().iterator(); i.hasNext();) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getImplements() != null) {
            for (Iterator<ClassOrInterfaceType> i = n.getImplements().iterator(); i.hasNext();) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getMembers() != null) {
            printMembers(n.getMembers(), arg);
        }
		
    }

    public void visit(EmptyTypeDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
    }

    public void visit(JavadocComment n, Object arg) {
    }

    public void visit(ClassOrInterfaceType n, Object arg) {
        if (n.getScope() != null) {
            n.getScope().accept(this, arg);
        }
        printTypeArgs(n.getTypeArgs(), arg);
    }

    public void visit(TypeParameter n, Object arg) {
        if (n.getTypeBound() != null) {
            for (Iterator<ClassOrInterfaceType> i = n.getTypeBound().iterator(); i.hasNext();) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    public void visit(PrimitiveType n, Object arg) {
        switch (n.getType()) {
            case Boolean:
                break;
            case Byte:
                break;
            case Char:
                break;
            case Double:
                break;
            case Float:
                break;
            case Int:
                break;
            case Long:
                break;
            case Short:
                break;
        }
    }

    public void visit(ReferenceType n, Object arg) {
        n.getType().accept(this, arg);
        for (int i = 0; i < n.getArrayCount(); i++) {
        }
    }

    public void visit(WildcardType n, Object arg) {
        if (n.getExtends() != null) {
            n.getExtends().accept(this, arg);
        }
        if (n.getSuper() != null) {
            n.getSuper().accept(this, arg);
        }
    }

    public void visit(FieldDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());
        n.getType().accept(this, arg);

        for (Iterator<VariableDeclarator> i = n.getVariables().iterator(); i.hasNext();) {
            VariableDeclarator var = i.next();
            
            Scope scope = n.getScope();
            String variableName = var.getId().toString();
            
            //Get the corresponding symbol of the variable with this name
            Symbol symbol = scope.resolve(variableName);
            String type = null;
            
            
            if (symbol.getType() != null){
            	type = symbol.getType().getName();
            }
            
            //pass this type on to the respective expression
            var.accept(this, type);
            if (i.hasNext()) {
            }
        }

    }

    public void visit(VariableDeclarator n, Object arg) {
        n.getId().accept(this, arg);
        if (n.getInit() != null) {
            n.getInit().accept(this, arg);
        }
    }

    public void visit(VariableDeclaratorId n, Object arg) {
        for (int i = 0; i < n.getArrayCount(); i++) {
        }
    }

    public void visit(ArrayInitializerExpr n, Object arg) {
        if (n.getValues() != null) {
            for (Iterator<Expression> i = n.getValues().iterator(); i.hasNext();) {
                Expression expr = i.next();
                expr.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    public void visit(VoidType n, Object arg) {
    	
    }

    public void visit(ArrayAccessExpr n, Object arg) {
        n.getName().accept(this, arg);
        n.getIndex().accept(this, arg);
    }

    public void visit(ArrayCreationExpr n, Object arg) {
        n.getType().accept(this, arg);
        printTypeArgs(n.getTypeArgs(), arg);

        if (n.getDimensions() != null) {
            for (Expression dim : n.getDimensions()) {
                dim.accept(this, arg);
            }
            for (int i = 0; i < n.getArrayCount(); i++) {
            }
        } else {
            for (int i = 0; i < n.getArrayCount(); i++) {
            }
            n.getInitializer().accept(this, arg);
        }
    }

    public void visit(AssignExpr n, Object arg) {
        n.getTarget().accept(this, arg);
                
        switch (n.getOperator()) {
            case assign:
                break;
            case and:
                break;
            case or:
                break;
            case xor:
                break;
            case plus:
                break;
            case minus:
                break;
            case rem:
                break;
            case slash:
                break;
            case star:
                break;
            case lShift:
                break;
            case rSignedShift:
                break;
            case rUnsignedShift:
                break;
        }
        
        Scope scope = n.getScope();
        String variableName = n.getTarget().toString();
        
        //Get the corresponding symbol of the variable with this name
        Symbol symbol = scope.resolve(variableName);
        String type = null;

        if (symbol.getType() != null){
        	type = symbol.getType().getName();
        }
                
        //Passing in type to do type-checking in expressions
        n.getValue().accept(this, type);
    }

    public void visit(BinaryExpr n, Object arg) {
    	
        n.getLeft().accept(this, arg);
        switch (n.getOperator()) {
            case or:
                break;
            case and:
                break;
            case binOr:
                break;
            case binAnd:
                break;
            case xor:
                break;
            case equals:
                break;
            case notEquals:
                break;
            case less:
                break;
            case greater:
                break;
            case lessEquals:
                break;
            case greaterEquals:
                break;
            case lShift:
                break;
            case rSignedShift:
                break;
            case rUnsignedShift:
                break;
            case plus:
                break;
            case minus:
                break;
            case times:
                break;
            case divide:
                break;
            case remainder:
                break;
        }
        n.getRight().accept(this, arg);
    }

    public void visit(CastExpr n, Object arg) {
        n.getType().accept(this, arg);
        n.getExpr().accept(this, arg);
    }

    public void visit(ClassExpr n, Object arg) {
        n.getType().accept(this, arg);
    }

    public void visit(ConditionalExpr n, Object arg) {
        n.getCondition().accept(this, arg);
        n.getThenExpr().accept(this, arg);
        n.getElseExpr().accept(this, arg);
    }

    public void visit(EnclosedExpr n, Object arg) {
        n.getInner().accept(this, arg);
    }

    public void visit(FieldAccessExpr n, Object arg) {
        n.getScope().accept(this, arg);
    }

    public void visit(InstanceOfExpr n, Object arg) {
        n.getExpr().accept(this, arg);
        n.getType().accept(this, arg);
    }

    public void visit(CharLiteralExpr n, Object arg) {
    	String type = null;
    	
  		if (!((arg instanceof ReferenceType) || (arg instanceof VoidType))) {
    	type = (String) arg;

	    	if (!type.equals("String") && !type.equals("char")) {
	    		throw new A2SemanticsException("Cannot assign char on line " + n.getBeginLine() + ", must assign a " + type);
	    	}
	  	}
    }

    public void visit(DoubleLiteralExpr n, Object arg) {
    	String type = null;
    	
    	if (!((arg instanceof ReferenceType) || (arg instanceof VoidType))) {
    		
        	type = (String) arg;
    		
	    	if (!type.equals("double")) {
	    		throw new A2SemanticsException("Cannot assign double on line " + n.getBeginLine() + ", must assign a " + type);
	    	}
    	}
    }

    public void visit(IntegerLiteralExpr n, Object arg) {
    	String type = null;
    	
    	if (!((arg instanceof ReferenceType) || (arg instanceof VoidType))) {
        	type = (String) arg;

	    	if (!type.equals("int") && !type.equals("double")) {
	    		throw new A2SemanticsException("Cannot assign int on line " + n.getBeginLine() + ", must assign a " + type);
	    	}
    	}
    }

    public void visit(LongLiteralExpr n, Object arg) {
    }

    public void visit(IntegerLiteralMinValueExpr n, Object arg) {
    }

    public void visit(LongLiteralMinValueExpr n, Object arg) {
    }

    public void visit(StringLiteralExpr n, Object arg) {
    	String type = null;
    	
    	if (!((arg instanceof ReferenceType) || (arg instanceof VoidType))) {
	    	type = (String) arg;
	    	if (!type.equals("String")) {
	    		throw new A2SemanticsException("Cannot assign \"" + n.getValue() + "\" on line " + n.getBeginLine() + ", must assign a " + type);
	    	}
    	}
    }

    public void visit(BooleanLiteralExpr n, Object arg) {
    	String type = null;
    	
    	if (!((arg instanceof ReferenceType) || (arg instanceof VoidType))) {
    		
        	type = (String) arg;
    		
	    	if (!type.equals("boolean")) {
	    		throw new A2SemanticsException("Cannot assign \"" + n.getValue() + "\" on line " + n.getBeginLine() + ", must assign a " + type);
	    	}
    	}
    }

    public void visit(NullLiteralExpr n, Object arg) {
    }

    public void visit(ThisExpr n, Object arg) {
        if (n.getClassExpr() != null) {
            n.getClassExpr().accept(this, arg);
        }
    }

    public void visit(SuperExpr n, Object arg) {
        if (n.getClassExpr() != null) {
            n.getClassExpr().accept(this, arg);
        }
    }

    public void visit(MethodCallExpr n, Object arg) {

    	String methodName = n.getName();
    	Scope scope = n.getRealScope();
    	//Get the type of the delegate object
    	Symbol symbol = scope.resolve(methodName);
    	String type = null;
    	if (symbol != null) {
    		type = scope.resolve(methodName).getType().getName();
    	}else{
    		throw new A2SemanticsException("The method " + methodName + " is not defined on line " + n.getBeginLine());
    	}
    	
    	ArrayList<String> params = null;
    	
    	//Resolve the type and check if it exists, and if it is delegate or a method
    	if (scope.resolve(methodName) instanceof VariableSymbol) {
	    	if (scope.resolve(type) != null) {
		    	if (scope.resolve(type) instanceof DelegateSymbol) {
		    		params = ((DelegateSymbol) scope.resolve(type)).getParams();
		    	}else if (scope.resolve(type) instanceof MethodSymbol) {
		    		params = ((MethodSymbol) scope.resolve(type)).getParams();
		    	}
	    	}
    	}else if (scope.resolve(methodName) instanceof MethodSymbol) {
    		params = (((MethodSymbol) scope.resolve(methodName)).getParams());
    	}
    		
    	
        if (n.getScope() != null) {
            n.getScope().accept(this, arg);
        }
        
        
        printTypeArgs(n.getTypeArgs(), arg);
        if (n.getArgs() != null && params != null) {
	        if (n.getArgs().size() == params.size()) {
	        	//Count to keep track of paramlist
	        	int count = 0;
		        if (n.getArgs() != null) {
		            for (Iterator<Expression> i = n.getArgs().iterator(); i.hasNext();) {
		                Expression e = i.next();
		                //Send the expected type of the parameter to the expression statement
		                e.accept(this, params.get(count));
		                count++;
		                if (i.hasNext()) {
		                }
		            }
		        }
	        }else{
	        	throw new A2SemanticsException("The parameters do not match for the " + n.getName() + " method/delegate on line " + n.getBeginLine());
	        }
        }
    }

    public void visit(ObjectCreationExpr n, Object arg) {
        if (n.getScope() != null) {
            n.getScope().accept(this, arg);
        }


        printTypeArgs(n.getTypeArgs(), arg);
        n.getType().accept(this, arg);

        if (n.getArgs() != null) {
            for (Iterator<Expression> i = n.getArgs().iterator(); i.hasNext();) {
                Expression e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getAnonymousClassBody() != null) {
            printMembers(n.getAnonymousClassBody(), arg);
        }
    }

    public void visit(SuperMemberAccessExpr n, Object arg) {
    }

    public void visit(UnaryExpr n, Object arg) {
        switch (n.getOperator()) {
            case positive:
                break;
            case negative:
                break;
            case inverse:
                break;
            case not:
                break;
            case preIncrement:
                break;
            case preDecrement:
                break;
        }

        n.getExpr().accept(this, arg);

        switch (n.getOperator()) {
            case posIncrement:
                break;
            case posDecrement:
                break;
        }
    }

    public void visit(ConstructorDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());

        printTypeParameters(n.getTypeParameters(), arg);
        if (n.getTypeParameters() != null) {
        }

        if (n.getParameters() != null) {
            for (Iterator<Parameter> i = n.getParameters().iterator(); i.hasNext();) {
                Parameter p = i.next();
                p.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getThrows() != null) {
            for (Iterator<NameExpr> i = n.getThrows().iterator(); i.hasNext();) {
                NameExpr name = i.next();
                name.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
        n.getBlock().accept(this, arg);
    }

    public void visit(MethodDeclaration n, Object arg) {

    	if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());

        printTypeParameters(n.getTypeParameters(), arg);
        if (n.getTypeParameters() != null) {
        }
        
        Scope scope = n.getScope();
        Symbol returnTypeSymbol = scope.resolve(n.getType().toString());
        //Check if the return type is a valid type
        if (returnTypeSymbol != null) {
        	if (!(returnTypeSymbol instanceof SymtabType)) {
        		//return type is in the symbol table but is not a type
        		throw new A2SemanticsException("The return type " + returnTypeSymbol.getName() + " is not defined " + 
        					" on line" + n.getBeginLine());
        	}
        }else{
        	//return type is not in the symbol table
    		throw new A2SemanticsException("The return type " + n.getType().toString() + " is not defined " + 
					" on line " + n.getBeginLine());
        }
        
        n.getType().accept(this, arg);     

        if (n.getParameters() != null) {
            for (Iterator<Parameter> i = n.getParameters().iterator(); i.hasNext();) {
                Parameter p = i.next();
                p.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        for (int i = 0; i < n.getArrayCount(); i++) {
        }

        if (n.getThrows() != null) {
            for (Iterator<NameExpr> i = n.getThrows().iterator(); i.hasNext();) {
                NameExpr name = i.next();
                name.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
        
        if (n.getBody() == null) {
        } else {
        	//Pass in return type to the block statement
        	if (!(n.getType().toString().equals("void"))) {
        		List<Statement> statements = n.getBody().getStmts();
        		boolean isReturnMissing = true;
	            for (int i = 0; i < statements.size(); i++) {
	            	if (statements.get(i) instanceof ReturnStmt) {
	            		isReturnMissing = false;
	            	}
	            }
	            
	            if (isReturnMissing) {
	        		throw new A2SemanticsException(n.getName() + " is missing a return statement of type " 
            				+ n.getType() + " on line " + n.getBeginLine());
	            }
        	}
            n.getBody().accept(this, n.getType().toString());

        }
        
		
    }

    public void visit(Parameter n, Object arg) {
        printAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());

        n.getType().accept(this, arg);
        if (n.isVarArgs()) {
        }
        n.getId().accept(this, arg);
    }

    public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
        if (n.isThis()) {
            printTypeArgs(n.getTypeArgs(), arg);
        } else {
            if (n.getExpr() != null) {
                n.getExpr().accept(this, arg);
            }
            printTypeArgs(n.getTypeArgs(), arg);
        }
        if (n.getArgs() != null) {
            for (Iterator<Expression> i = n.getArgs().iterator(); i.hasNext();) {
                Expression e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
    }

    public void visit(VariableDeclarationExpr n, Object arg) {
    	
    	printAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());
                
        n.getType().accept(this, arg);    
        
        for (Iterator<VariableDeclarator> i = n.getVars().iterator(); i.hasNext();) {
            VariableDeclarator v = i.next();
            Scope scope = n.getScope();
            String variableName = v.getId().toString();
            
            //Get the corresponding symbol of the variable with this name
            
            Symbol symbol = scope.resolve(variableName);
            String type = null;
            
            if (symbol.getType() != null){
            	type = symbol.getType().getName();
            }
                    
            //NEED TO FIX
            if (v.getInit() instanceof MethodCallExpr) {
            	
            	//Take out the brackets out of the method call
            	String methodName = v.getInit().toString().replace("()","");
            	
            	//Resovle if the method exists
            	Symbol methodSymbol = scope.getEnclosingScope().resolve(methodName);
            	String returnType = methodSymbol.getType().getName();
            	
            	if (type != returnType) {
            		throw new A2SemanticsException("Cannot assign " + returnType + " to a " + type + " at line " + n.getBeginLine());
            	}
            	
            }
            
            v.accept(this, type);
            if (i.hasNext()) {
            }
        }
    }

    public void visit(TypeDeclarationStmt n, Object arg) {
        n.getTypeDeclaration().accept(this, arg);
    }

    public void visit(AssertStmt n, Object arg) {
        n.getCheck().accept(this, arg);
        if (n.getMessage() != null) {
            n.getMessage().accept(this, arg);
        }
    }

    public void visit(BlockStmt n, Object arg) {
        if (n.getStmts() != null) {
            for (Statement s : n.getStmts()) {
                s.accept(this, arg);
            }
        }

    }

    public void visit(LabeledStmt n, Object arg) {
        n.getStmt().accept(this, arg);
    }

    public void visit(EmptyStmt n, Object arg) {
    }

    public void visit(ExpressionStmt n, Object arg) {
        n.getExpression().accept(this, arg);
    }

    public void visit(SwitchStmt n, Object arg) {
        n.getSelector().accept(this, arg);
        if (n.getEntries() != null) {
            for (SwitchEntryStmt e : n.getEntries()) {
                e.accept(this, arg);
            }
        }

    }

    public void visit(SwitchEntryStmt n, Object arg) {
        if (n.getLabel() != null) {
            n.getLabel().accept(this, arg);
        } else {
        }
        if (n.getStmts() != null) {
            for (Statement s : n.getStmts()) {
                s.accept(this, arg);
            }
        }
    }

    public void visit(BreakStmt n, Object arg) {
        if (n.getId() != null) {
        }
    }

    public void visit(ReturnStmt n, Object arg) {
    	Scope scope = n.getScope();
    	
    	String expectedReturnTypeString = arg.toString();
    	String actualReturnTypeString = null;
    	
    	//Check if returning a variable
    	if (n.getExpr() instanceof NameExpr) {
    		//Resolve the variable's name in the current scope and get the type of the symbol returned
	    	SymtabType actualReturnType = scope.resolve(n.getExpr().toString()).getType();
	    	
	    	if (actualReturnType instanceof TypeSymbol) {
	    		TypeSymbol temp = (TypeSymbol) actualReturnType;
	    		actualReturnTypeString = actualReturnType.getName();
	    	}else if (actualReturnType instanceof BuiltInTypeSymbol) {
	    		BuiltInTypeSymbol temp = (BuiltInTypeSymbol) actualReturnType;
	    		actualReturnTypeString = actualReturnType.getName();    		
	    	}
	    	
	    	if (!(actualReturnTypeString.equals(expectedReturnTypeString))) {
	    		throw new A2SemanticsException("invalid return type: " + actualReturnTypeString  + ", need " + expectedReturnTypeString);
	    	}
    	}else{
    		actualReturnTypeString = n.getExpr().toString();
    	}
    	
        if (n.getExpr() != null) {
        	if (arg instanceof VoidType) {
        		throw new A2SemanticsException("invalid return type " + actualReturnTypeString + ", need " + expectedReturnTypeString);
        	}
        	
            n.getExpr().accept(this, arg);
        }
    }

    public void visit(EnumDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());


        if (n.getImplements() != null) {
            for (Iterator<ClassOrInterfaceType> i = n.getImplements().iterator(); i.hasNext();) {
                ClassOrInterfaceType c = i.next();
                c.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getEntries() != null) {
            for (Iterator<EnumConstantDeclaration> i = n.getEntries().iterator(); i.hasNext();) {
                EnumConstantDeclaration e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
        if (n.getMembers() != null) {
            printMembers(n.getMembers(), arg);
        } else {
            if (n.getEntries() != null) {
            }
        }
    }

    public void visit(EnumConstantDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);

        if (n.getArgs() != null) {
            for (Iterator<Expression> i = n.getArgs().iterator(); i.hasNext();) {
                Expression e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }

        if (n.getClassBody() != null) {
            printMembers(n.getClassBody(), arg);
        }
    }

    public void visit(EmptyMemberDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
    }

    public void visit(InitializerDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        if (n.isStatic()) {
        }
        n.getBlock().accept(this, arg);
    }

    public void visit(IfStmt n, Object arg) {
        n.getCondition().accept(this, arg);
        
        n.getThenStmt().accept(this, arg);
        if (n.getElseStmt() != null) {
            n.getElseStmt().accept(this, arg);
        }
    }

    public void visit(WhileStmt n, Object arg) {
        n.getCondition().accept(this, arg);
        n.getBody().accept(this, arg);
    }

    public void visit(ContinueStmt n, Object arg) {
        if (n.getId() != null) {
        }
    }

    public void visit(DoStmt n, Object arg) {
        n.getBody().accept(this, arg);
        n.getCondition().accept(this, arg);
    }

    public void visit(ForeachStmt n, Object arg) {
        n.getVariable().accept(this, arg);
        n.getIterable().accept(this, arg);
        n.getBody().accept(this, arg);
    }

    public void visit(ForStmt n, Object arg) {
        if (n.getInit() != null) {
            for (Iterator<Expression> i = n.getInit().iterator(); i.hasNext();) {
                Expression e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
        if (n.getCompare() != null) {
            n.getCompare().accept(this, "int");
        }
        if (n.getUpdate() != null) {
            for (Iterator<Expression> i = n.getUpdate().iterator(); i.hasNext();) {
                Expression e = i.next();
                e.accept(this, arg);
                if (i.hasNext()) {
                }
            }
        }
        n.getBody().accept(this, arg);
    }

    public void visit(ThrowStmt n, Object arg) {
        n.getExpr().accept(this, arg);
    }

    public void visit(SynchronizedStmt n, Object arg) {
        n.getExpr().accept(this, arg);
        n.getBlock().accept(this, arg);
    }

    public void visit(TryStmt n, Object arg) {
        n.getTryBlock().accept(this, arg);
        if (n.getCatchs() != null) {
            for (CatchClause c : n.getCatchs()) {
                c.accept(this, arg);
            }
        }
        if (n.getFinallyBlock() != null) {
            n.getFinallyBlock().accept(this, arg);
        }
    }

    public void visit(CatchClause n, Object arg) {
        n.getExcept().accept(this, arg);
        n.getCatchBlock().accept(this, arg);

    }

    public void visit(AnnotationDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());

        if (n.getMembers() != null) {
            printMembers(n.getMembers(), arg);
        }
    }

    public void visit(AnnotationMemberDeclaration n, Object arg) {
        if (n.getJavaDoc() != null) {
            n.getJavaDoc().accept(this, arg);
        }
        printMemberAnnotations(n.getAnnotations(), arg);
        printModifiers(n.getModifiers());

        n.getType().accept(this, arg);
        if (n.getDefaultValue() != null) {
            n.getDefaultValue().accept(this, arg);
        }
    }

    public void visit(MarkerAnnotationExpr n, Object arg) {
        n.getName().accept(this, arg);
    }

    public void visit(SingleMemberAnnotationExpr n, Object arg) {
        n.getName().accept(this, arg);
        n.getMemberValue().accept(this, arg);
    }

    public void visit(NormalAnnotationExpr n, Object arg) {
        n.getName().accept(this, arg);
        for (Iterator<MemberValuePair> i = n.getPairs().iterator(); i.hasNext();) {
            MemberValuePair m = i.next();
            m.accept(this, arg);
            if (i.hasNext()) {
            }
        }
    }

    public void visit(MemberValuePair n, Object arg) {
        n.getValue().accept(this, arg);
    }

    public void visit(LineComment n, Object arg) {
    }

    public void visit(BlockComment n, Object arg) {
    }

	@Override
	public void visit(DelegateDeclaration n, Object arg) {
	}
}
