package net.dontdrinkandroot.metagen.visitor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.*;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DeclarationVisitor implements TypeVisitor<String, ProcessingEnvironment>
{
	@Override
	public String visit(TypeMirror t, ProcessingEnvironment env)
	{
		System.out.println("visit(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public String visit(TypeMirror t)
	{
		System.out.println("visit(TypeMirror t)");
		return null;
	}

	@Override
	public String visitPrimitive(PrimitiveType t, ProcessingEnvironment env)
	{
		TypeElement boxedElement = env.getTypeUtils().boxedClass(t);
		return boxedElement.getQualifiedName().toString();
	}

	@Override
	public String visitNull(NullType t, ProcessingEnvironment env)
	{
		System.out.println("visitNull(NullType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitArray(ArrayType t, ProcessingEnvironment env)
	{
		System.out.println("visitArray(ArrayType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType t, ProcessingEnvironment env)
	{
		return t.toString();
	}

	@Override
	public String visitError(ErrorType t, ProcessingEnvironment env)
	{
		System.out.println("visitError(ErrorType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable t, ProcessingEnvironment env)
	{
		System.out.println("visitTypeVariable(TypeVariable t, Void aVoid)");
		return null;
	}

	@Override
	public String visitWildcard(WildcardType t, ProcessingEnvironment env)
	{
		System.out.println("visitWildcard(WildcardType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitExecutable(ExecutableType t, ProcessingEnvironment env)
	{
		System.out.println("visitExecutable(ExecutableType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitNoType(NoType t, ProcessingEnvironment env)
	{
		System.out.println("visitNoType(NoType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror t, ProcessingEnvironment env)
	{
		System.out.println("visitUnknown(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public String visitUnion(UnionType t, ProcessingEnvironment env)
	{
		System.out.println("visitUnion(UnionType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType t, ProcessingEnvironment env)
	{
		System.out.println("visitIntersection(IntersectionType t, Void aVoid)");
		return null;
	}
}
