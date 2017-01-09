package cn.byhieg.reflectiontutorialtest;

import cn.byhieg.reflectiontutorial.GenericObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import junit.framework.TestCase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by byhieg on 17/1/9.
 * Mail to byhieg@gmail.com
 */
public class GenericObjectTest extends TestCase {

    public void testGeneric()throws Exception{
        Class clz = GenericObject.class;
        Method getMethod = clz.getMethod("getLists");
        Type genericType = getMethod.getGenericReturnType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) genericType);
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type : types){
                Class actualClz = ((Class) type);
                System.out.println("参数化类型为 ： " + actualClz);
            }
        }

        Method setMethod = clz.getMethod("setLists", List.class);
        Type[] genericParameterTypes = setMethod.getGenericParameterTypes();
        for (Type genericParameterType: genericParameterTypes){
            System.out.println("GenericParameterTypes为 ： " + genericParameterType.getTypeName());
            if(genericParameterType instanceof ParameterizedType){
                ParameterizedType parameterizedType = ((ParameterizedType) genericParameterType);
                System.out.println("ParameterizedType为 :" + parameterizedType.getTypeName());
                Type types[] = parameterizedType.getActualTypeArguments();
                for (Type type : types){
                    System.out.println("参数化类型为 ： " + ((Class) type).getName());
                }
            }

        }

        Field field = clz.getField("lists");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            Type [] types = parameterizedType.getActualTypeArguments();
            for (Type type1 : types) {
                System.out.println("参数化类型 ： " + ((Class) type1).getTypeName());
            }
        }

    }

}