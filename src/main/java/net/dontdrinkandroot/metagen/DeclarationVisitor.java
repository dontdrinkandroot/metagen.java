package net.dontdrinkandroot.metagen;

import javax.lang.model.type.*;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DeclarationVisitor implements TypeVisitor<String, Void>
{
	@Override
	public String visit(TypeMirror t, Void aVoid)
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
	public String visitPrimitive(PrimitiveType t, Void aVoid)
	{
		switch (t.getKind()) {
			case BYTE:
				return Byte.class.getCanonicalName();
			case SHORT:
				return Short.class.getCanonicalName();
			case INT:
				return Integer.class.getCanonicalName();
			case LONG:
				return Long.class.getCanonicalName();
			case FLOAT:
				return Float.class.getCanonicalName();
			case DOUBLE:
				return Double.class.getCanonicalName();
			case CHAR:
				return Character.class.getCanonicalName();
			case BOOLEAN:
				return Boolean.class.getCanonicalName();
		}
		throw new RuntimeException(String.format("Couldn't extract primitive wrapper for %s", t.getKind()));
	}

	@Override
	public String visitNull(NullType t, Void aVoid)
	{
		System.out.println("visitNull(NullType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitArray(ArrayType t, Void aVoid)
	{
		System.out.println("visitArray(ArrayType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitDeclared(DeclaredType t, Void aVoid)
	{
		return t.toString();
	}

	@Override
	public String visitError(ErrorType t, Void aVoid)
	{
		System.out.println("visitError(ErrorType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitTypeVariable(TypeVariable t, Void aVoid)
	{
		System.out.println("visitTypeVariable(TypeVariable t, Void aVoid)");
		return null;
	}

	@Override
	public String visitWildcard(WildcardType t, Void aVoid)
	{
		System.out.println("visitWildcard(WildcardType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitExecutable(ExecutableType t, Void aVoid)
	{
		System.out.println("visitExecutable(ExecutableType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitNoType(NoType t, Void aVoid)
	{
		System.out.println("visitNoType(NoType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitUnknown(TypeMirror t, Void aVoid)
	{
		System.out.println("visitUnknown(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public String visitUnion(UnionType t, Void aVoid)
	{
		System.out.println("visitUnion(UnionType t, Void aVoid)");
		return null;
	}

	@Override
	public String visitIntersection(IntersectionType t, Void aVoid)
	{
		System.out.println("visitIntersection(IntersectionType t, Void aVoid)");
		return null;
	}
}
