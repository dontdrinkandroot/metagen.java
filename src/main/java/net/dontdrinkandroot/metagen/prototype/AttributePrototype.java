package net.dontdrinkandroot.metagen.prototype;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototype
{
	public enum Type
	{
		SINGULAR,
		SET,
		LIST,
		COLLECTION,
		MAP;
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
