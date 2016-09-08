package net.dontdrinkandroot.metagen;

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
@SupportedAnnotationTypes("net.dontdrinkandroot.metagen.GenerateMetadata")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GenerateMetadataProcessor extends AbstractProcessor
{

	public static final String CLASSSTRING_STATIC_METAMODEL = "javax.persistence.metamodel.StaticMetamodel";
	public static final String CLASSSTRING_SINGULAR_ATTRIBUTE = "javax.persistence.metamodel.SingularAttribute";
	public static final String CLASSSTRING_SET_ATTRIBUTE = "javax.persistence.metamodel.SetAttribute";
	public static final String CLASSSTRING_LIST_ATTRIBUTE = "javax.persistence.metamodel.ListAttribute";
	public static final String CLASSSTRING_COLLECTION_ATTRIBUTE = "javax.persistence.metamodel.CollectionAttribute";
	public static final String CLASSSTRING_MAP_ATTRIBUTE = "javax.persistence.metamodel.MapAttribute";

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
	{
		Field field;
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
			bw.append("package ");
			bw.append(this.getPackageName(typeElement));
			bw.append(";\n");
			bw.append(String.format(
					"@%s(%s.class)",
					CLASSSTRING_STATIC_METAMODEL,
					typeElement.getQualifiedName()
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
					if (null != attributePrototype)
						if (attributePrototype.isSingular()) {
							bw.append(String.format(
									"\tpublic static volatile %s<%s, %s> %s;",
									CLASSSTRING_SINGULAR_ATTRIBUTE,
									typeElement.getQualifiedName(),
									attributePrototype.getDefinition(),
									enclosedElement.getSimpleName()
							));
						} else {
							switch (attributePrototype.getType()) {

								case SET:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;",
											CLASSSTRING_SET_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case LIST:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;",
											CLASSSTRING_LIST_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case COLLECTION:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;",
											CLASSSTRING_COLLECTION_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case MAP:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;",
											CLASSSTRING_MAP_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
							}
						}
				}
				bw.newLine();
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
