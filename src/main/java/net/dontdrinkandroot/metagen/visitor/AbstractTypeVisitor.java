package net.dontdrinkandroot.metagen.visitor;

import javax.lang.model.type.*;
import javax.lang.model.util.AbstractTypeVisitor8;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AbstractTypeVisitor<R, B> extends AbstractTypeVisitor8<R, B>
{
	@Override
	public R visitIntersection(IntersectionType t, B b)
	{
		return null;
	}

	@Override
	public R visitPrimitive(PrimitiveType t, B b)
	{
		return null;
	}

	@Override
	public R visitNull(NullType t, B b)
	{
		return null;
	}

	@Override
	public R visitArray(ArrayType t, B b)
	{
		return null;
	}

	@Override
	public R visitDeclared(DeclaredType t, B b)
	{
		return null;
	}

	@Override
	public R visitError(ErrorType t, B b)
	{
		return null;
	}

	@Override
	public R visitTypeVariable(TypeVariable t, B b)
	{
		return null;
	}

	@Override
	public R visitWildcard(WildcardType t, B b)
	{
		return null;
	}

	@Override
	public R visitExecutable(ExecutableType t, B b)
	{
		return null;
	}

	@Override
	public R visitNoType(NoType t, B b)
	{
		return null;
	}

	@Override
	public R visitUnion(UnionType t, B b)
	{
		return null;
	}
}
