package cn.byhieg.annotationstutorial;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
public class AMethodProcess {

    public static void initMethod(Object object) throws InvocationTargetException, IllegalAccessException {
        if (object instanceof User) {
            Class clz = object.getClass();
            Method [] methods = clz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(AMethod.class)) {
                    if(Modifier.isPrivate(method.getModifiers())){
                        method.setAccessible(true);
                    }else {
                        AMethod aMethod =  method.getAnnotation(AMethod.class);
                        System.out.println(aMethod.method() + "时间为 " + aMethod.value());
                        method.invoke(object);
                    }
                }
            }
        }else {
            throw new RuntimeException("无法向下转型成指定类");
        }
    }
}
