/*
 * Copyright (C) 2016 Philip Washington Sorst <philip@sorst.net>
 * and individual contributors as indicated
 * by the @authors tag.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
