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
					"@javax.persistence.metamodel.StaticMetamodel(%s.class)\n",
					typeElement.getQualifiedName()
			));
			bw.append(String.format("public abstract class %s {\n", typeElement.getSimpleName() + "_"));
			for (Element enclosedElement : typeElement.getEnclosedElements()) {
				if (enclosedElement.getKind() == ElementKind.FIELD) {
					bw.append(String.format(
							"\tpublic static volatile javax.persistence.metamodel.SingularAttribute<%s, %s> %s;\n",
							typeElement.getQualifiedName(),
							enclosedElement.asType(),
							enclosedElement.getSimpleName()
					));
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
