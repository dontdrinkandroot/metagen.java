package net.dontdrinkandroot.metagen;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributePrototype
{
	enum Type
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
		return type;
	}

	public String getDefinition()
	{
		return definition;
	}

	public boolean isSingular()
	{
		return type == Type.SINGULAR;
	}
}
