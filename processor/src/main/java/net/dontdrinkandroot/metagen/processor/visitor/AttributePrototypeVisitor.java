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

import net.dontdrinkandroot.metagen.processor.prototype.AttributePrototype;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.PrimitiveType;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototypeVisitor extends AbstractTypeVisitor<AttributePrototype>
{
    @Override
    public AttributePrototype visitPrimitive(PrimitiveType t, ProcessingEnvironment env)
    {
        String classString = t.accept(new DeclarationVisitor(), env);
        return new AttributePrototype(classString, classString);
    }

    @Override
    public AttributePrototype visitArray(ArrayType t, ProcessingEnvironment env)
    {
        String classString = String.format("%s[]", t.getComponentType().accept(new DeclarationVisitor(), env));
        return new AttributePrototype(classString, classString);
    }

    @Override
    public AttributePrototype visitDeclared(DeclaredType t, ProcessingEnvironment env)
    {
        return new AttributePrototype(
                t.accept(new DeclarationVisitor(), env),
                ((TypeElement) t.asElement()).getQualifiedName().toString()
        );
    }
}
