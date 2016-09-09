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
public class MapAttributePrototype extends AttributePrototype
{
	private String valueDefinition;

	public MapAttributePrototype(String definition, String valueDefinition)
	{
		super(Type.MAP, definition);
		this.valueDefinition = valueDefinition;
	}

	public String getValueDefinition()
	{
		return this.valueDefinition;
	}
}
