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
package net.dontdrinkandroot.metagen.processor.visitor;

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
