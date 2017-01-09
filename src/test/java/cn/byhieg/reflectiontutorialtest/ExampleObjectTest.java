package cn.byhieg.reflectiontutorialtest;

import cn.byhieg.reflectiontutorial.ExampleObject;
import junit.framework.TestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by shiqifeng on 2017/1/9.
 * Mail byhieg@gmail.com
 */
public class ExampleObjectTest extends TestCase {

    public void testReflect()throws Exception{
        Class exampleObjectClass = ExampleObject.class;
        //得到类的名字
        String fullClassName = exampleObjectClass.getName();
        String simpleClassName = exampleObjectClass.getSimpleName();

        System.out.println(fullClassName);
        System.out.println(simpleClassName);

        //输出类的public方法
        Method[] methods = exampleObjectClass.getMethods();
        for (Method method : methods){
            System.out.println("method = "+ method.getName());
        }

        //得到类的关键字
        int modifiers = exampleObjectClass.getMethods()[0].getModifiers();
        System.out.println(Modifier.isPublic(modifiers));
        System.out.println(Modifier.isPrivate(modifiers));


        //得到包信息
        Package aPackage = exampleObjectClass.getPackage();
        System.out.println(aPackage);

        //得到父类
        Class superClass = exampleObjectClass.getSuperclass();
        System.out.println(superClass.getSimpleName());


        //得到接口
        Class[] classes = superClass.getInterfaces();
        System.out.println("父类的接口" + classes[0]);

        //构造器
        Constructor[] constructors = exampleObjectClass.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor.toString());
        }

        //得到变量
        Field[] fields = exampleObjectClass.getFields();
        for (Field field : fields){
            System.out.println(field.toString());
        }

        //得到注解
        Annotation[] annotations = exampleObjectClass.getMethod("doSomething").getAnnotations();
        for (Annotation annotation : annotations){
            System.out.println(annotation.toString());
        }
    }

}