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
