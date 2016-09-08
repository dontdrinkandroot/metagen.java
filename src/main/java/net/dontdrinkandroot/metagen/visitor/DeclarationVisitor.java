package net.dontdrinkandroot.metagen.visitor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DeclarationVisitor extends AbstractTypeVisitor<String>
{
	@Override
	public String visitPrimitive(PrimitiveType t, ProcessingEnvironment env)
	{
		TypeElement boxedElement = env.getTypeUtils().boxedClass(t);
		return boxedElement.getQualifiedName().toString();
	}

	@Override
	public String visitDeclared(DeclaredType t, ProcessingEnvironment env)
	{
		return t.toString();
	}
}
