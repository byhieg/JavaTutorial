package cn.byhieg.annotationstutorial;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
public class APTProcessor extends AbstractProcessor{

    //类名的前缀、后缀
    public static final String SUFFIX = "AutoGenerate";
    public static final String PREFIX = "byhieg_";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(typeElement)) {
                //打印消息
                Messager messager = processingEnv.getMessager();
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing:" + e.toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing:" + e.getSimpleName());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing:" + e.getEnclosedElements().toString());

                //获取注解
                APTAnnotation aptAnnotation = e.getAnnotation(APTAnnotation.class);

                //获取元素名并将其首字母大写
                String name = e.getSimpleName().toString();
                char c = Character.toUpperCase(name.charAt(0));
                name = String.valueOf(c + name.substring(1));

                //包裹注解元素的元素, 也就是其父元素, 比如注解了成员变量或者成员函数, 其上层就是该类
                Element enclosingElement = e.getEnclosingElement();
                String enclosingQualifiedname;
                if (enclosingElement instanceof PackageElement) {
                    enclosingQualifiedname = ((PackageElement) enclosingElement).getQualifiedName().toString();
                } else {
                    enclosingQualifiedname = ((TypeElement) enclosingElement).getQualifiedName().toString();
                }
                try {
                    //生成包名
                    String generatePackageName = enclosingQualifiedname.substring(0, enclosingQualifiedname.lastIndexOf("."));

                    // 生成的类名
                    String genarateClassName = PREFIX + enclosingElement.getSimpleName() + SUFFIX;

                    //创建Java 文件
                    JavaFileObject f = processingEnv.getFiler().createSourceFile(genarateClassName);

                    // 在控制台输出文件路径
                    messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + f.toUri());
                    Writer w = f.openWriter();
                    try {
                        PrintWriter pw = new PrintWriter(w);
                        pw.println("package " + generatePackageName + ";");
                        pw.println("\npublic class " + genarateClassName + " { ");
                        pw.println("\n    /** 打印值 */");
                        pw.println("    public static void print" + name + "() {");
                        pw.println("        // 注解的父元素: " + enclosingElement.toString());
                        pw.println("        System.out.println(\"代码生成的路径: " + f.toUri() + "\");");
                        pw.println("        System.out.println(\"注解的元素: " + e.toString() + "\");");
                        pw.println("        System.out.println(\"注解的版本: " + aptAnnotation.version() + "\");");
                        pw.println("        System.out.println(\"注解的作者: " + aptAnnotation.author() + "\");");
                        pw.println("        System.out.println(\"注解的日期: " + aptAnnotation.date() + "\");");

                        pw.println("    }");
                        pw.println("}");
                        pw.flush();
                    } finally {
                        w.close();
                    }
                } catch (IOException e1) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e1.toString());
                }
            }
        }
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }
}
