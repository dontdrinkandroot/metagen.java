package net.dontdrinkandroot.metagen.visitor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.*;
import javax.lang.model.util.AbstractTypeVisitor8;
import javax.tools.Diagnostic;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AbstractTypeVisitor<R> extends AbstractTypeVisitor8<R, ProcessingEnvironment>
{
	@Override
	public R visitIntersection(IntersectionType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitIntersection()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitPrimitive(PrimitiveType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitPrimitive()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitNull(NullType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitNull()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitArray(ArrayType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitArray()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitDeclared(DeclaredType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitDeclared()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitError(ErrorType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitError()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitTypeVariable(TypeVariable t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitTypeVariable()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitWildcard(WildcardType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitWildcard()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitExecutable(ExecutableType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitExecutable()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitNoType(NoType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitNoType()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}

	@Override
	public R visitUnion(UnionType t, ProcessingEnvironment processingEnvironment)
	{
		processingEnvironment.getMessager()
				.printMessage(
						Diagnostic.Kind.ERROR,
						"Unhandled visitUnion()",
						processingEnvironment.getTypeUtils().asElement(t)
				);
		return null;
	}
}
