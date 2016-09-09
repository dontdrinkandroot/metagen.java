metagen.java
============

Generate javax.persistence.metamodel classes for arbitrary classes

[![Build Status](https://travis-ci.org/dontdrinkandroot/metagen.java.svg?branch=master)](https://travis-ci.org/dontdrinkandroot/metagen.java)

Usage
-----

Simply add the `@net.dontdrinkandroot.metagen.GenerateMetadata` annotation to the classes for which you want metadata to be generated.

### Example

Input file:

```java
package net.sorst.metagen.test;
import net.dontdrinkandroot.metagen.GenerateMetadata;
@GenerateMetadata
public class PropertyTestClass
{
    private byte primitiveByteField;
}
```

Generated file:

```java
package net.dontdrinkandroot.metagen.test;
@javax.annotation.Generated(value = "net.dontdrinkandroot.metagen.GenerateMetadataProcessor")
@javax.persistence.metamodel.StaticMetamodel(net.dontdrinkandroot.metagen.test.PropertyTestClass.class)
public abstract class PropertyTestClass_ {
	public static volatile javax.persistence.metamodel.SingularAttribute<net.dontdrinkandroot.metagen.test.PropertyTestClass, java.lang.Byte> primitiveByteField;
}
```

Maven
-----

This project is not yet available via Maven Central. In the meantime you can include the dontdrinkandroot repository:

```xml
<repositories>
    <repository>
        <id>dontdrinkandroot</id>
        <url>https://maven.dontdrinkandroot.net/content/groups/public</url>
    </repository>
</repositories>
```

You can include the dependency by adding the following to your pom.xml:

```xml
<dependency>
    <groupId>net.dontdrinkandroot</groupId>
    <artifactId>metagen</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

License
-------

Copyright (C) 2016 Philip Washington Sorst <philip@sorst.net>
and individual contributors as indicated
by the @authors tag.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.