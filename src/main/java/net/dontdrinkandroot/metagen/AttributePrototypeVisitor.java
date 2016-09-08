package net.dontdrinkandroot.metagen;

import javax.lang.model.type.*;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototypeVisitor implements TypeVisitor<AttributePrototype, Void>
{
	@Override
	public AttributePrototype visit(TypeMirror t, Void aVoid)
	{
		System.out.println("visit(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visit(TypeMirror t)
	{
		System.out.println("visit(TypeMirror t)");
		return null;
	}

	@Override
	public AttributePrototype visitPrimitive(PrimitiveType t, Void aVoid)
	{
		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), null));
	}

	@Override
	public AttributePrototype visitNull(NullType t, Void aVoid)
	{
		System.out.println("visitNull(NullType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitArray(ArrayType t, Void aVoid)
	{
		return new AttributePrototype(
				AttributePrototype.Type.SINGULAR,
				String.format("%s[]", t.getComponentType().accept(new DeclarationVisitor(), null))
		);
	}

	@Override
	public AttributePrototype visitDeclared(DeclaredType t, Void aVoid)
	{
		List<? extends TypeMirror> typeArguments = t.getTypeArguments();
		if (typeArguments.size() == 0) {
			return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), null));
		}
		//TODO: Check if this is a plural attribute
		if (t.toString().startsWith("java.util.List")) {
			return new AttributePrototype(AttributePrototype.Type.LIST, t.accept(new DeclarationVisitor(), null));
		}
		if (t.toString().startsWith("java.util.Set")) {
			return new AttributePrototype(AttributePrototype.Type.SET, t.accept(new DeclarationVisitor(), null));
		}
		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), null));
	}

	@Override
	public AttributePrototype visitError(ErrorType t, Void aVoid)
	{
		System.out.println("visitError(ErrorType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitTypeVariable(TypeVariable t, Void aVoid)
	{
		System.out.println("visitTypeVariable(TypeVariable t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitWildcard(WildcardType t, Void aVoid)
	{
		System.out.println("visitWildcard(WildcardType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitExecutable(ExecutableType t, Void aVoid)
	{
		System.out.println("visitExecutable(ExecutableType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitNoType(NoType t, Void aVoid)
	{
		System.out.println("visitNoType(NoType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitUnknown(TypeMirror t, Void aVoid)
	{
		System.out.println("visitUnknown(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitUnion(UnionType t, Void aVoid)
	{
		System.out.println("visitUnion(UnionType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitIntersection(IntersectionType t, Void aVoid)
	{
		System.out.println("visitIntersection(IntersectionType t, Void aVoid)");
		return null;
	}

	private String extractPrimitiveWrapper(PrimitiveType t)
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
}
