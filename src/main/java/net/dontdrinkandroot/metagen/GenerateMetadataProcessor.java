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
			this.processTypeElement(element);
		}
		return true;
	}

	private void processTypeElement(TypeElement typeElement)
	{
		PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();
		try {
			JavaFileObject fileObject =
					this.processingEnv.getFiler().createSourceFile(typeElement.getQualifiedName() + "_");
			BufferedWriter bw = new BufferedWriter(fileObject.openWriter());
			bw.append("package ");
			bw.append(packageElement.getQualifiedName());
			bw.append(";\n");
			bw.append(String.format(
					"@%s(%s.class)\n",
					CLASSSTRING_STATIC_METAMODEL,
					typeElement.getQualifiedName()
			));
			bw.append(String.format("public abstract class %s {\n", typeElement.getSimpleName() + "_"));
			for (Element enclosedElement : typeElement.getEnclosedElements()) {
				if (enclosedElement.getKind() == ElementKind.FIELD) {
					TypeMirror typeMirror = enclosedElement.asType();
					AttributePrototype attributePrototype = typeMirror.accept(new AttributePrototypeVisitor(), null);
					if (null != attributePrototype)
						if (attributePrototype.isSingular()) {
							bw.append(String.format(
									"\tpublic static volatile %s<%s, %s> %s;\n",
									CLASSSTRING_SINGULAR_ATTRIBUTE,
									typeElement.getQualifiedName(),
									attributePrototype.getDefinition(),
									enclosedElement.getSimpleName()
							));
						} else {
							switch (attributePrototype.getType()) {

								case SET:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;\n",
											CLASSSTRING_SET_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case LIST:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;\n",
											CLASSSTRING_LIST_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case COLLECTION:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;\n",
											CLASSSTRING_COLLECTION_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
								case MAP:
									bw.append(String.format(
											"\tpublic static volatile %s<%s, %s> %s;\n",
											CLASSSTRING_MAP_ATTRIBUTE,
											typeElement.getQualifiedName(),
											attributePrototype.getDefinition(),
											enclosedElement.getSimpleName()
									));
									break;
							}
						}
				}
			}
			bw.append("}");
			bw.newLine();
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
