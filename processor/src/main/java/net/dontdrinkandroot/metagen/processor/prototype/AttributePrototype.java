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
package net.dontdrinkandroot.metagen.processor.prototype;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototype
{

    private String definition;

    private String classString;

    public AttributePrototype(String definition, String classString)
    {
        this.definition = definition;
        this.classString = classString;
    }

    public String getDefinition()
    {
        return this.definition;
    }

    public String getClassString()
    {
        return this.classString;
    }
}
