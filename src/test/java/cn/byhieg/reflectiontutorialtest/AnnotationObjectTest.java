package cn.byhieg.reflectiontutorialtest;

import cn.byhieg.reflectiontutorial.AnnotationObject;
import cn.byhieg.reflectiontutorial.MyAnnotation;
import junit.framework.TestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by byhieg on 17/1/9.
 * Mail to byhieg@gmail.com
 */
public class AnnotationObjectTest extends TestCase {

    public void testAnnotation() throws Exception{
        Class clz = AnnotationObject.class;
        Annotation[] annotationsInClass = clz.getAnnotations();
        for (Annotation annotation : annotationsInClass){
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value:" + myAnnotation.value());
            }
        }

        Method method = clz.getMethod("doSomeThing");
        Annotation[] annotationsInMethod = method.getDeclaredAnnotations();
        for (Annotation annotation : annotationsInMethod){
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value:" + myAnnotation.value());
            }
        }

        Field field = clz.getField("field");
        Annotation[] annotationsInField = field.getDeclaredAnnotations();
        for (Annotation annotation : annotationsInField){
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value:" + myAnnotation.value());
            }
        }

        Method method1 = clz.getMethod("doOtherThing",String.class);
        Annotation[][] annotationInParam = method1.getParameterAnnotations();
        Class[] params = method1.getParameterTypes();
        int i = 0;
        for (Annotation[] annotations: annotationInParam){
            Class para = params[i++];
            for (Annotation annotation : annotations){
                if(annotation instanceof MyAnnotation){
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("param: " + para.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value :" + myAnnotation.value());
                }

            }
        }

    }
}