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

import net.dontdrinkandroot.metagen.prototype.AttributePrototype;
import net.dontdrinkandroot.metagen.prototype.MapAttributePrototype;

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

		//TODO: Check if this is a plural attribute
		if (t.toString().startsWith("java.util.List")) {
			return new AttributePrototype(AttributePrototype.Type.LIST, t.accept(new DeclarationVisitor(), env));
		}

		if (t.toString().startsWith("java.util.Set")) {
			return new AttributePrototype(AttributePrototype.Type.SET, t.accept(new DeclarationVisitor(), env));
		}

		if (t.toString().startsWith("java.util.Collection")) {
			return new AttributePrototype(AttributePrototype.Type.COLLECTION, t.accept(new DeclarationVisitor(), env));
		}

		if (t.toString().startsWith("java.util.Map")) {

			String valueDefinition = "?";
			if (typeArguments.size() == 2) {
				valueDefinition = typeArguments.get(1).accept(new DeclarationVisitor(), env);
			}
			return new MapAttributePrototype(t.accept(new DeclarationVisitor(), env), valueDefinition);
		}

		return new AttributePrototype(AttributePrototype.Type.SINGULAR, t.accept(new DeclarationVisitor(), env));
	}
}
