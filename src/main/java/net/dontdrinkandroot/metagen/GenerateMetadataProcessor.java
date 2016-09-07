package net.dontdrinkandroot.metagen;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
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
		for (Element enclosedElement : typeElement.getEnclosedElements()) {
			if (enclosedElement.getKind() == ElementKind.FIELD) {
				Set<Modifier> modifiers = enclosedElement.getModifiers();
				StringBuilder sb = new StringBuilder();
				if (modifiers.contains(Modifier.PRIVATE)) {
					sb.append("private ");
				} else if (modifiers.contains(Modifier.PROTECTED)) {
					sb.append("protected ");
				} else if (modifiers.contains(Modifier.PUBLIC)) {
					sb.append("public ");
				}
				if (modifiers.contains(Modifier.STATIC))
					sb.append("static ");
				if (modifiers.contains(Modifier.FINAL))
					sb.append("final ");
				sb.append(enclosedElement.asType()).append(" ").append(enclosedElement.getSimpleName());

				System.out.println(sb);
			}
		}

		try {
			JavaFileObject fileObject =
					this.processingEnv.getFiler().createSourceFile(typeElement.getQualifiedName() + "_");
			BufferedWriter bw = new BufferedWriter(fileObject.openWriter());
			bw.append("package ");
			bw.append(packageElement.getQualifiedName());
			bw.append(";\n");
			bw.append(String.format("@javax.persistence.metamodel.StaticMetamodel(%s.class)\n", typeElement.getQualifiedName()));
			bw.append(String.format("public abstract class %s {\n", typeElement.getSimpleName() + "_"));
			bw.append("}");
			bw.newLine();
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
