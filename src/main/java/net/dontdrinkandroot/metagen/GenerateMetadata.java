package net.dontdrinkandroot.metagen;

import java.lang.annotation.*;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GenerateMetadata
{
}
