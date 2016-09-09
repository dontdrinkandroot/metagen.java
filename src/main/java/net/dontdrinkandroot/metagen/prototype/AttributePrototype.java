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
package net.dontdrinkandroot.metagen.prototype;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototype
{
    public enum Type
    {
        SINGULAR("javax.persistence.metamodel.SingularAttribute"),
        SET("javax.persistence.metamodel.SetAttribute"),
        LIST("javax.persistence.metamodel.ListAttribute"),
        COLLECTION("javax.persistence.metamodel.CollectionAttribute"),
        MAP("javax.persistence.metamodel.MapAttribute");

        private final String attributeClass;

        private Type(String attributeClass)
        {
            this.attributeClass = attributeClass;
        }

        public String getAttributeClass()
        {
            return this.attributeClass;
        }
    }

    private Type type;

    private String definition;

    public AttributePrototype(Type type, String definition)
    {
        this.type = type;
        this.definition = definition;
    }

    public Type getType()
    {
        return this.type;
    }

    public String getDefinition()
    {
        return this.definition;
    }

    public boolean isSingular()
    {
        return this.type == Type.SINGULAR;
    }
}
