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
        Class exampleObjectClass = Class.forName("cn.byhieg.reflectiontutorial.ExampleObject");
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

        Method method = exampleObjectClass.getMethod("setAge",int.class);
        System.out.println(method.getName());
        for (Class clz : method.getParameterTypes()){
            System.out.println("方法的参数" + clz.getName());
        }
        System.out.println(method.getReturnType().getName());

        System.out.println(method.invoke(exampleObjectClass.newInstance(),1));






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
        System.out.println("父类是不是抽象类 " + Modifier.isAbstract(superClass.getModifiers()));
        Field field2 = superClass.getDeclaredField("father");
        System.out.println(field2.getName());

        //得到接口
        Class[] classes = superClass.getInterfaces();
        System.out.println("父类的接口" + classes[0]);

        //构造器
        Constructor[] constructors = exampleObjectClass.getConstructors();
        for (int i = 0;i < constructors.length;i++){
            Class[] parameterTypes = constructors[i].getParameterTypes();
            System.out.println("构造器参数如下========================");
            for (Class clz : parameterTypes){
                System.out.println("参数类型 " + clz.toString());
            }
        }
        //得到构造器的参数类型
        Constructor constructor = exampleObjectClass.getConstructor(int.class,Integer.class);
        System.out.println(constructor.getParameterTypes()[0].toString());
        System.out.println(constructor.toString());

        Constructor constructor1 = exampleObjectClass.getConstructor(String.class);
        System.out.println(constructor1.newInstance("byhieg"));


        //得到变量
        Field[] fields = exampleObjectClass.getFields();
        for (Field field : fields){
            System.out.println("变量为： " + field.getName());
        }
        Field field1 = exampleObjectClass.getField("age");
        System.out.println("变量为:" + field1.toString());

        ExampleObject object = ((ExampleObject) constructor1.newInstance("byhieg"));
        System.out.println("原先的age是 " + object.age);
        field1.set(object,10);
        System.out.println("更改之后的age是" + object.age);

        //得到注解
        Annotation[] annotations = exampleObjectClass.getAnnotations();
        for (Annotation annotation : annotations){
            System.out.println(annotation.toString());
        }
    }

}