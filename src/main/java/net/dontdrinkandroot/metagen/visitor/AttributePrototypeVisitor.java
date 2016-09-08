package net.dontdrinkandroot.metagen.visitor;

import net.dontdrinkandroot.metagen.prototype.AttributePrototype;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.*;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototypeVisitor implements TypeVisitor<AttributePrototype, ProcessingEnvironment>
{
	@Override
	public AttributePrototype visit(TypeMirror t, ProcessingEnvironment env)
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
	public AttributePrototype visitPrimitive(PrimitiveType t, ProcessingEnvironment env)
	{
		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), env));
	}

	@Override
	public AttributePrototype visitNull(NullType t, ProcessingEnvironment env)
	{
		System.out.println("visitNull(NullType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitArray(ArrayType t, ProcessingEnvironment env)
	{
		return new AttributePrototype(
				AttributePrototype.Type.SINGULAR,
				String.format("%s[]", t.getComponentType().accept(new DeclarationVisitor(), env))
		);
	}

	@Override
	public AttributePrototype visitDeclared(DeclaredType t, ProcessingEnvironment env)
	{
		List<? extends TypeMirror> typeArguments = t.getTypeArguments();
		if (typeArguments.size() == 0) {
			return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), env));
		}
		//TODO: Check if this is a plural attribute
		if (t.toString().startsWith("java.util.List")) {
			return new AttributePrototype(AttributePrototype.Type.LIST, t.accept(new DeclarationVisitor(), env));
		}
		if (t.toString().startsWith("java.util.Set")) {
			return new AttributePrototype(AttributePrototype.Type.SET, t.accept(new DeclarationVisitor(), env));
		}
		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), env));
	}

	@Override
	public AttributePrototype visitError(ErrorType t, ProcessingEnvironment env)
	{
		System.out.println("visitError(ErrorType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitTypeVariable(TypeVariable t, ProcessingEnvironment env)
	{
		System.out.println("visitTypeVariable(TypeVariable t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitWildcard(WildcardType t, ProcessingEnvironment env)
	{
		System.out.println("visitWildcard(WildcardType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitExecutable(ExecutableType t, ProcessingEnvironment env)
	{
		System.out.println("visitExecutable(ExecutableType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitNoType(NoType t, ProcessingEnvironment env)
	{
		System.out.println("visitNoType(NoType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitUnknown(TypeMirror t, ProcessingEnvironment env)
	{
		System.out.println("visitUnknown(TypeMirror t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitUnion(UnionType t, ProcessingEnvironment env)
	{
		System.out.println("visitUnion(UnionType t, Void aVoid)");
		return null;
	}

	@Override
	public AttributePrototype visitIntersection(IntersectionType t, ProcessingEnvironment env)
	{
		System.out.println("visitIntersection(IntersectionType t, Void aVoid)");
		return null;
	}
}
