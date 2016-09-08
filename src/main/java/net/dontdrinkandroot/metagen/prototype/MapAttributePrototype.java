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
