package net.dontdrinkandroot.metagen.visitor;

import net.dontdrinkandroot.metagen.prototype.AttributePrototype;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import java.util.List;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototypeVisitor extends AbstractTypeVisitor<AttributePrototype>
{
	@Override
	public AttributePrototype visitPrimitive(PrimitiveType t, ProcessingEnvironment env)
	{
		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), env));
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
}
