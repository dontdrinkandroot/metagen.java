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
package net.dontdrinkandroot.metagen.processor;

import net.dontdrinkandroot.metagen.model.Attribute;
import net.dontdrinkandroot.metagen.model.GenerateMetadata;
import net.dontdrinkandroot.metagen.processor.prototype.AttributePrototype;
import net.dontdrinkandroot.metagen.processor.visitor.AttributePrototypeVisitor;

import javax.annotation.Generated;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@SupportedAnnotationTypes("net.dontdrinkandroot.metagen.model.GenerateMetadata")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GenerateMetadataProcessor extends AbstractProcessor
{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        Field field;
        @SuppressWarnings("unchecked")
        Set<TypeElement> elements = (Set<TypeElement>) roundEnv.getElementsAnnotatedWith(GenerateMetadata.class);
        for (TypeElement element : elements) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing", element);
            this.processTypeElement(element);
        }
        return true;
    }

    private void processTypeElement(TypeElement typeElement)
    {
        try {
            JavaFileObject fileObject =
                    this.processingEnv.getFiler().createSourceFile(typeElement.getQualifiedName() + "_");
            BufferedWriter bw = new BufferedWriter(fileObject.openWriter());
            bw.append(String.format("package %s;", this.getPackageName(typeElement)));
            bw.newLine();
            bw.append(String.format(
                    "@%s(value = \"%s\")",
                    Generated.class.getCanonicalName(),
                    this.getClass().getCanonicalName()
            ));
            bw.newLine();
            String parentClassName = this.getQualifiedAnnotatedParentClassMetaName(typeElement);
            if (null == parentClassName) {
                bw.append(String.format("public abstract class %s {", this.getMetaClassName(typeElement)));
            } else {
                bw.append(String.format(
                        "public abstract class %s extends %s {",
                        this.getMetaClassName(typeElement),
                        parentClassName
                ));
            }
            bw.newLine();
            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD) {
                    TypeMirror typeMirror = enclosedElement.asType();
                    AttributePrototype attributePrototype =
                            typeMirror.accept(new AttributePrototypeVisitor(), this.processingEnv);
                    if (null != attributePrototype) {
                        bw.append(String.format(
                                "\tpublic static %s<%s, %s> %s =",
                                Attribute.class.getCanonicalName(),
                                typeElement.getQualifiedName(),
                                attributePrototype.getDefinition(),
                                enclosedElement.getSimpleName()
                        ));
                        bw.newLine();
                        bw.append(String.format(
                                "\t\tnew %s(\"%s\", %s.class, %s.class);",
                                Attribute.class.getCanonicalName(),
                                enclosedElement.getSimpleName(),
                                typeElement.getQualifiedName(),
                                attributePrototype.getClassString()
                        ));
                        bw.newLine();
                    }
                }
            }
            bw.append("}");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getMetaClassName(TypeElement typeElement)
    {
        return typeElement.getSimpleName() + "_";
    }

    private String getQualifiedAnnotatedParentClassMetaName(TypeElement element)
    {
        TypeMirror superclassMirror = element.getSuperclass();
        TypeElement superclassElement = (TypeElement) this.processingEnv.getTypeUtils().asElement(superclassMirror);
        GenerateMetadata annotation = superclassElement.getAnnotation(GenerateMetadata.class);
        if (null != annotation) {
            return this.getPackageName(superclassElement) + "." + this.getMetaClassName(superclassElement);
        }

        return null;
    }

    private String getPackageName(TypeElement element)
    {
        PackageElement packageElement = (PackageElement) element.getEnclosingElement();
        return packageElement.getQualifiedName().toString();
    }
}
