package net.dontdrinkandroot.metagen.model;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class Attribute<X, Y>
{
    private String name;

    private Class<X> declaringClass;

    private Class<Y> attributeClass;

    public Attribute(String name, Class<X> declaringClass, Class<Y> attributeClass)
    {
        this.name = name;
        this.declaringClass = declaringClass;
        this.attributeClass = attributeClass;
    }

    public String getName()
    {
        return this.name;
    }

    public Class<X> getDeclaringClass()
    {
        return this.declaringClass;
    }

    public Class<Y> getAttributeClass()
    {
        return this.attributeClass;
    }
}
