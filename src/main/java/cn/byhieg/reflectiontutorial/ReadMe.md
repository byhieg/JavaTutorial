


# 详解Java反射各种应用
Java除了给我们提供在编译期得到类的各种信息之外，还通过反射让我们可以在运行期间得到类的各种信息。通过反射获取类的信息，得到类的信息之后，就可以获取以下相关内容：
- Class对象
- 构造器
- 变量
- 方法
- 私有变量与私有方法
- 注解
- 泛型
- 数组

本文也将从上面几个方面来介绍Java反射。本文涉及的所有代码均在[反射代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/reflectiontutorial)
首先放出一个Java类作为反射的研究对象，类的内容如下：
```
public abstract class FatherObject implements Runnable{
    public void doSomething(){
        System.out.println("做事情......");
    }
}

public class ExampleObject extends FatherObject{
    public int age = 30;
    public String name = "byhieg";
    private Integer score = 60;

    public void printName(){
        System.out.println(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public ExampleObject(){

    }

    public ExampleObject(String name){

    }

    public ExampleObject(int age,Integer score){

    }

    @Override
    public void doSomething() {
        super.doSomething();
    }

    @Override
    public void run() {
        System.out.println("run......");
    }
}

```

## Class对象
我们应用会用到反射这个知识点，肯定是想要在运行时得到类的信息，根据类的那些信息去做一些特定的操作。那么，首先无疑就是得到类的信息，在JDK中提供了Class对象来保存类的信息。所以，反射的第一步就是得到Class对象。在JDK中提供了两种方式得到Class对象。
第一种，如果编写代码的时候，就知道Class的名字，可以直接用如下方式得到Class对象：
```
Class exampleObjectClass = ExampleObject.class;
```
第二种，如果在编写代码的时候，不知道类的名字，但是在运行时的时候，可以得到一个类名的字符串，可以用如下的方式获取Class对象：
```
Class exampleObjectClass = Class.forName("cn.byhieg.reflectiontutorial.ExampleObject");
```
注意，此方法需要有2个条件，第一，forName中的字符串必须是全限定名，第二，这个Class类必须在classpath的路径下面，因为该方法会抛出`ClassNotFoundException`的异常。

获取到这个Class对象之后，就可以得到类的各种信息，开头已经提及了一些信息，下面，说几个没提到的类的信息。

### 得到类的名字
类的名字有两种方式得到，一种是getName()，一种是getSimpleName()。第一种得到的是全限定名，第二种得到的是这个类的名字，不带包名。看下面的例子：Class对象，已经通过上面的代码得到了。
```
   String fullClassName = exampleObjectClass.getName();
   String simpleClassName = exampleObjectClass.getSimpleName();

   System.out.println(fullClassName);
   System.out.println(simpleClassName);
```
结果如下：
```
cn.byhieg.reflectiontutorial.ExampleObject
ExampleObject
```

### 得到类的包名、父类和实现的接口
类的包名和父类，可以通过如下代码得到。
```
  //得到包信息
    Package aPackage = exampleObjectClass.getPackage();
    System.out.println(aPackage);

    //得到父类
    Class superClass = exampleObjectClass.getSuperclass();
    System.out.println(superClass.getSimpleName());
```
结果如下：
```
package cn.byhieg.reflectiontutorial
FatherObject
```
很显然，得到父类的返回值也是一个Class对象，那么可以利用这个对象得到父类的一些信息，比如判断父类是不是抽象类
```
 System.out.println("父类是不是抽象类 " + Modifier.isAbstract(superClass.getModifiers()));
```
getModifiers可以得到类的修饰符，从而得到类的修饰符，当然，这个getModifiers不仅仅Class对象可以调用，Method对象可以调用。
可以使用java.lang.reflect.Modifier类中的方法来检查修饰符的类型：
```
Modifier.isAbstract(int modifiers);
Modifier.isFinal(int modifiers);
Modifier.isInterface(int modifiers);
Modifier.isNative(int modifiers);
Modifier.isPrivate(int modifiers);
Modifier.isProtected(int modifiers);
Modifier.isPublic(int modifiers);
Modifier.isStatic(int modifiers);
Modifier.isStrict(int modifiers);
Modifier.isSynchronized(int modifiers);
Modifier.isTransient(int modifiers);
Modifier.isVolatile(int modifiers);
```
此外，我们还可以得到父类实现的接口
```
        //得到接口
        Class[] classes = superClass.getInterfaces();
        System.out.println("父类的接口" + classes[0]);
```
因为Java类可以实现很多接口，所以用的数组，但在实际使用的时候，需要先判断数组的长度。
下面，重点讲解上述列出来的内容。

## 构造器
利用Java反射可以得到一个类的构造器，并根据构造器，在运行时动态的创建一个对象。首先，Java通过以下方式获取构造器的实例：
```
 //构造器
        Constructor[] constructors = exampleObjectClass.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor.toString());
        }
```
结果如下：
```
public cn.byhieg.reflectiontutorial.ExampleObject(int,java.lang.Integer)
public cn.byhieg.reflectiontutorial.ExampleObject(java.lang.String)
public cn.byhieg.reflectiontutorial.ExampleObject()
```
如果，事先知道要访问的构造方法的参数类型，可以利用如下方法获取指定的构造方法，例子如下：
```
   Constructor constructor = exampleObjectClass.getConstructor(String.class);
   System.out.println(constructor.toString());
```
结果显然是:

```
public cn.byhieg.reflectiontutorial.ExampleObject(java.lang.String)
```
还可以用如下方式得到另一个构造器
```
    Constructor constructor = exampleObjectClass.getConstructor(int.class,Integer.class);
    System.out.println(constructor.toString());
```
此外，如果我们不知道构造器的参数，只能得到所有的构造器对象，那么可以用如下方式得到每一个构造器对想的参数：
```
 Constructor[] constructors = exampleObjectClass.getConstructors();
    for (Constructor constructor : constructors){
        Class[] parameterTypes = constructor.getParameterTypes();
        System.out.println("构造器参数如下========================");
        for (Class clz : parameterTypes){
            System.out.println("参数类型 " + clz.toString());
        }
    }
```
结果如下：
```
构造器参数如下========================
参数类型 class java.lang.String
构造器参数如下========================
参数类型 int
参数类型 class java.lang.Integer
```
这里，可以看出无参构造方法，是不打印出结果的。基本类型的Class对象和引用类型的Class对象toString()方法是不一样的。
现在，可以根据构造器的各种信息，动态创建一个对象。
```
    Object object = constructor.newInstance(1,100);
    System.out.println(object.toString());
```
这个创建对象的方式有2个条件，第一是通过有参构造器创建的，第二，构造器对象必须通过传入参数信息的getConstructor得到。
第一个条件，对于无参构造方法就可以创建的对象，不需要得到构造器对象，直接Class对象调用newInstance()方法就直接创建对象。
第二个条件，构造器对象必须通过`exampleObjectClass.getConstructor(String.class);`这种形式得到。如果通过`getConstructors`得到构造器数组，然后调用指定的构造器对象去创建对象在JDK1.8是会错的。但是JDK1.6是正常的。

## 变量
利用Java反射可以在运行时得到一个类的变量信息，并且可以根据上面讲的方式，创建一个对象，设置他的变量值。首先，通过如下方法，得到所有public的变量：
```
   Field[] fields = exampleObjectClass.getFields();
    for (Field field : fields){
            System.out.println("变量为： " + field.toString());
    }
```
结果如下：

```
变量为： public int cn.byhieg.reflectiontutorial.ExampleObject.age
变量为： public java.lang.String cn.byhieg.reflectiontutorial.ExampleObject.name
```
很显然，得到的都是public的变量，上述的private的变量score，并没有得到。
和构造器一样的得到方式一样，我们可以指定一个参数名，然后得到指定的变量：
```
        Field field = exampleObjectClass.getField("age");
        System.out.println("变量为:" + field.toString());
```
上述的变量的toString方法得到的名字太长，Java对Field类提供了getName的方法，返回类中写的变量名字，上面的代码就可以改成field.getName()。
反射不仅提供了得到变量的方法，还提供了设置变量值的方式。通过如下方法可以对一个动态生成的类，改变其变量值：
```
    ExampleObject object = ((ExampleObject) constructor1.newInstance("byhieg"));
        System.out.println("原先的age是 " + object.age);
        field.set(object,10);
        System.out.println("更改之后的age是" + object.age);
```
结果如下：
```
原先的age是 30
更改之后的age是10
```
根据上面的代码，得到名字为age的Field对象，然后调用该对象的set方法，传入一个对象与要更改的值，就可以改变该对象的值了。注意，此方法不仅仅对成员变量有用，对静态变量也可以。当然，如果是静态变量，传入null，不用传对象，也是可以的。


## 方法
Java反射给我们除了给我们提供类的变量信息之外，当然也给我们提供了方法的信息，反射可以让我们得到方法名，方法的参数，方法的返回类型，以及调用方法等功能。
首先，通过如下代码得到方法：
```
        //输出类的public方法
        Method[] methods = exampleObjectClass.getMethods();
        for (Method method : methods){
            System.out.println("method = "+ method.getName());
        }
```
和获取变量一样似曾相识的代码，这里直接调用了getName，来得到类中写的方法名。写到这里，大家应该自然想到，Java同样提供了根据参数，得到具体的方法。
```
        Method method = exampleObjectClass.getMethod("setAge",int.class);
        System.out.println(method.getName());
```
这里与得到变量不同的是，getMethod方法还需要传入参数的类型信息，反射提供获取方法参数以及返回类型的方法，得到方法参数的例子如下：
```
        Method method = exampleObjectClass.getMethod("setAge",int.class);
        System.out.println(method.getName());
        for (Class clz : method.getParameterTypes()){
            System.out.println("方法的参数" + clz.getName());
        }
```
结果如下：
```
setAge
方法的参数int
```
得到方法返回类型的例子如下：
```
System.out.println(method.getReturnType().getName());
```
结果如下：
```
void
```
此外，Java反射支持通过invoke调用得到的方法。例子如下：
```
method.invoke(exampleObjectClass.newInstance(),1);
```
invoke第一个参数是这个对象，第二个参数是变长数组，传入该方法的参数。和Field对象同样，对于静态方法同样，可以传入null，调用静态方法。
## 私有变量与私有方法
上面的方法只能得到public方法和变量，无法得到非public修饰的方法和变量，Java提供了额外的方法来得到非public变量与方法。即通过`getDeclaredFields`与`getDeclaredMethods`方法得到私有的变量与方法，同样也支持用`getDeclaredField（变量名）`与` getDeclaredMethod（方法名)`的形式得到指定的变量名与方法名。但是这样得到的Field对象与Method对象无法直接运用，必须让这些对象调用setAccessible(true),才能正常运用。之后的方式就可上面讲的一样了。

## 注解
先写一个包含注解的类：
```
MyAnnotation(name="byhieg",value = "hello world")
public class AnnotationObject {

    @MyAnnotation(name="field",value = "变量")
    public String field;

    @MyAnnotation(name="method",value = "方法")
    public void doSomeThing(){
        System.out.println("做一些事情");
    }

    public void doOtherThing(@MyAnnotation(name="param",value = "参数") String param){

    }
}

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String name();
    public String value();

}
```
Java给我们提供了在运行时获取类的注解信息，可以得到类注解，方法注解，参数注解，变量注解。
与上面获取方式一样，Java提供了2种获取方式，一种是获取全部的注解，返回一个数组，第二种是指定得到指定的注解。
我们以一个类注解为例，讲解以下这两种获取方式。
```
 Class clz = AnnotationObject.class;
 Annotation[] annotations = clz.getAnnotations();
 Annotation annotation = clz.getAnnotation(AnnotationObject.class);
```
然后，就可以根据得到的注解进行后续的处理，下面是一个处理的例子:
```
 for (Annotation annotation : annotations){
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value:" + myAnnotation.value());
            }
        }
```
上面的类注解使用Class对象调用`getAnnotations`得到的，方法注解和变量注解是一样的，分别用method对象与field对象调用`getDeclaredAnnotations`得到注解，没什么多说的。例子看[反射代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/reflectiontutorial)
参数注解是比较麻烦的一项，获取方式比较得到，第一步，先取得method对象，调用`getParameterAnnotations`，但是这个返回值是一个二维数组，因为method对象有很多参数，每个参数有可能有很多注解。例子如下：
```
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
```
## 泛型
因为Java泛型是通过擦除来实现的，很难直接得到泛型具体的参数化类型的信息，但是我们可以通过一种间接的形式利用反射得到泛型信息。比如下面这个类：
```
public class GenericObject {
    public List<String> lists;

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
}
```
如果一个方法返回一个泛型类，我们可以通过method对象去调用`getGenericReturnType`来得到这个泛型类具体的参数化类型是什么。看下面的代码：
```
 Class clz = GenericObject.class;
        Method method = clz.getMethod("getLists");
        Type genericType = method.getGenericReturnType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) genericType);
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type : types){
                Class actualClz = ((Class) type);
                System.out.println("参数化类型为 ： " + actualClz);
            }
        }
```
结果如下：
```
参数化类型为 ： class java.lang.String
```
步骤有点繁琐，下面一步步解释：
1.   反射得到返回类型为泛型类的方法
2.  调用`getGenericReturnType`得到方法返回类型中的参数化类型
3.  判断该type对象能不能向下转型为`ParameterizedType`
4.  转型成功，调用`getActualTypeArguments`得到参数化类型的数组，因为有的泛型类，不只只有一个参数化类型如Map<K，V>
5.  取出数组中的每一个的值，转型为Class对象输出。

看结果确实得到了泛型的具体的信息。
如果没有一个方法返回泛型类型，那么我们也可以通过方法的参数为泛型类，来得到泛型的参数化类型，如上面类中的setLists方法。例子如下：
```
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
```
执行的结果如下：
```
GenericParameterTypes为 ： java.util.List<java.lang.String>
ParameterizedType为 :java.util.List<java.lang.String>
参数化类型为 ： java.lang.String
```
因为方法的参数为泛型类型的可能不止一个，所以通过`getGenericParameterTypes`得到是一个数组，我们需要确定每一个元素，是否是具有参数化类型。后续的步骤与上面类似，就不多说了。
如果连方法参数都不带泛型类，那么只剩下最后一种情况，通过变量类型，即用Field类。例子如下：
```
        Field field = clz.getField("lists");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            Type [] types = parameterizedType.getActualTypeArguments();
            for (Type type1 : types) {
                System.out.println("参数化类型 ： " + ((Class) type1).getTypeName());
            }
        }
```
原理和上面的一样，只不过Type对象是通过`field.getGenericType()`，剩下的操作类似就不多说了。
关于通过反射获取泛型的参数化类型的信息的介绍就到此为止。

## 数组
Java反射可以对数组进行操作，包括创建一个数组，访问数组中的值，以及得到一个数组的Class对象。
下面，先说简单的，创建数组以及访问数组中的值：在反射中使用Array这个类，是reflect包下面的。
```
	//创建一个int类型的数组，长度为3
   int[] intArray = (int[])Array.newInstance(int.class,3);
   //通过反射的形式，给数组赋值
        for (int i = 0 ;i < intArray.length;i++){
            Array.set(intArray,i,i + 2);
        }
//通过反射的形式，得到数组中的值
        for (int i = 0 ; i < intArray.length;i++){
            System.out.println(Array.get(intArray,i));
        }
```
上述就是创建数组，访问数组中的值利用反射方式。
对于得到一个数组的Class对象，简单的可以用`int[].class`，或者利用Class.forName的形式得到，写法比较奇怪：
```
 Class clz = Class.forName("[I");
 System.out.println(clz.getTypeName());
```
结果为：
```
int[]
```
这个forName中的字符串，`[`表示是数组，`I`表示是int，float就是`F`，double就是`D`等等，如果要得到一个普通对象的数组，则用下面的形式：
```
  Class stringClz = Class.forName("[Ljava.lang.String;");
```
`[`表示是数组,`L`的右边是类名，类型的右边是一个`；`；
这种方式获取数组的Class对象实在是太繁琐了。
在得到数组的Class对象之后，就可以调用他的一些独特的方法，比如调用`getComponentType`来得到数组成员的类型信息，如int数组就是成员类型就是int。
```
System.out.println(clz.getComponentType().getTypeName());
```
结果为`int`

## 总结
这次,关于反射的各种应用就到此为止，后续可能会有深入的知识讲解。具体的代码可以去看[反射代码](https://github.com/byhieg/JavaTutorial/tree/master/src/main/java/cn/byhieg/reflectiontutorial)
在src包里面是各种类，在test类里是对这些类的访问。





























详解Java反射各种应用

Java除了给我们提供在编译期得到类的各种信息之外，还通过反射让我们可以在运行期间得到类的各种信息。通过反射获取类的信息，得到类的信息之后，就可以获取以下相关内容：

Class对象
构造器
变量
方法
私有变量与私有方法
注解
泛型
数组
本文也将从上面几个方面来介绍Java反射。本文涉及的所有代码均在反射代码
首先放出一个Java类作为反射的研究对象，类的内容如下：

public abstract class FatherObject implements Runnable{
    public void doSomething(){
        System.out.println("做事情......");
    }
}

public class ExampleObject extends FatherObject{
    public int age = 30;
    public String name = "byhieg";
    private Integer score = 60;

    public void printName(){
        System.out.println(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public ExampleObject(){

    }

    public ExampleObject(String name){

    }

    public ExampleObject(int age,Integer score){

    }

    @Override
    public void doSomething() {
        super.doSomething();
    }

    @Override
    public void run() {
        System.out.println("run......");
    }
}

Class对象

我们应用会用到反射这个知识点，肯定是想要在运行时得到类的信息，根据类的那些信息去做一些特定的操作。那么，首先无疑就是得到类的信息，在JDK中提供了Class对象来保存类的信息。所以，反射的第一步就是得到Class对象。在JDK中提供了两种方式得到Class对象。
第一种，如果编写代码的时候，就知道Class的名字，可以直接用如下方式得到Class对象：

Class exampleObjectClass = ExampleObject.class;
第二种，如果在编写代码的时候，不知道类的名字，但是在运行时的时候，可以得到一个类名的字符串，可以用如下的方式获取Class对象：

Class exampleObjectClass = Class.forName("cn.byhieg.reflectiontutorial.ExampleObject");
注意，此方法需要有2个条件，第一，forName中的字符串必须是全限定名，第二，这个Class类必须在classpath的路径下面，因为该方法会抛出ClassNotFoundException的异常。

获取到这个Class对象之后，就可以得到类的各种信息，开头已经提及了一些信息，下面，说几个没提到的类的信息。

得到类的名字

类的名字有两种方式得到，一种是getName()，一种是getSimpleName()。第一种得到的是全限定名，第二种得到的是这个类的名字，不带包名。看下面的例子：Class对象，已经通过上面的代码得到了。

   String fullClassName = exampleObjectClass.getName();
   String simpleClassName = exampleObjectClass.getSimpleName();

   System.out.println(fullClassName);
   System.out.println(simpleClassName);
结果如下：

cn.byhieg.reflectiontutorial.ExampleObject
ExampleObject
得到类的包名、父类和实现的接口

类的包名和父类，可以通过如下代码得到。

  //得到包信息
    Package aPackage = exampleObjectClass.getPackage();
    System.out.println(aPackage);

    //得到父类
    Class superClass = exampleObjectClass.getSuperclass();
    System.out.println(superClass.getSimpleName());
结果如下：

package cn.byhieg.reflectiontutorial
FatherObject
很显然，得到父类的返回值也是一个Class对象，那么可以利用这个对象得到父类的一些信息，比如判断父类是不是抽象类

 System.out.println("父类是不是抽象类 " + Modifier.isAbstract(superClass.getModifiers()));
getModifiers可以得到类的修饰符，从而得到类的修饰符，当然，这个getModifiers不仅仅Class对象可以调用，Method对象可以调用。
可以使用java.lang.reflect.Modifier类中的方法来检查修饰符的类型：

Modifier.isAbstract(int modifiers);
Modifier.isFinal(int modifiers);
Modifier.isInterface(int modifiers);
Modifier.isNative(int modifiers);
Modifier.isPrivate(int modifiers);
Modifier.isProtected(int modifiers);
Modifier.isPublic(int modifiers);
Modifier.isStatic(int modifiers);
Modifier.isStrict(int modifiers);
Modifier.isSynchronized(int modifiers);
Modifier.isTransient(int modifiers);
Modifier.isVolatile(int modifiers);
此外，我们还可以得到父类实现的接口

        //得到接口
        Class[] classes = superClass.getInterfaces();
        System.out.println("父类的接口" + classes[0]);
因为Java类可以实现很多接口，所以用的数组，但在实际使用的时候，需要先判断数组的长度。
下面，重点讲解上述列出来的内容。

构造器

利用Java反射可以得到一个类的构造器，并根据构造器，在运行时动态的创建一个对象。首先，Java通过以下方式获取构造器的实例：

 //构造器
        Constructor[] constructors = exampleObjectClass.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor.toString());
        }
结果如下：

public cn.byhieg.reflectiontutorial.ExampleObject(int,java.lang.Integer)
public cn.byhieg.reflectiontutorial.ExampleObject(java.lang.String)
public cn.byhieg.reflectiontutorial.ExampleObject()
如果，事先知道要访问的构造方法的参数类型，可以利用如下方法获取指定的构造方法，例子如下：

   Constructor constructor = exampleObjectClass.getConstructor(String.class);
   System.out.println(constructor.toString());
结果显然是:

public cn.byhieg.reflectiontutorial.ExampleObject(java.lang.String)
还可以用如下方式得到另一个构造器

    Constructor constructor = exampleObjectClass.getConstructor(int.class,Integer.class);
    System.out.println(constructor.toString());
此外，如果我们不知道构造器的参数，只能得到所有的构造器对象，那么可以用如下方式得到每一个构造器对想的参数：

 Constructor[] constructors = exampleObjectClass.getConstructors();
    for (Constructor constructor : constructors){
        Class[] parameterTypes = constructor.getParameterTypes();
        System.out.println("构造器参数如下========================");
        for (Class clz : parameterTypes){
            System.out.println("参数类型 " + clz.toString());
        }
    }
结果如下：

构造器参数如下========================
参数类型 class java.lang.String
构造器参数如下========================
参数类型 int
参数类型 class java.lang.Integer
这里，可以看出无参构造方法，是不打印出结果的。基本类型的Class对象和引用类型的Class对象toString()方法是不一样的。
现在，可以根据构造器的各种信息，动态创建一个对象。

    Object object = constructor.newInstance(1,100);
    System.out.println(object.toString());
这个创建对象的方式有2个条件，第一是通过有参构造器创建的，第二，构造器对象必须通过传入参数信息的getConstructor得到。
第一个条件，对于无参构造方法就可以创建的对象，不需要得到构造器对象，直接Class对象调用newInstance()方法就直接创建对象。
第二个条件，构造器对象必须通过exampleObjectClass.getConstructor(String.class);这种形式得到。如果通过getConstructors得到构造器数组，然后调用指定的构造器对象去创建对象在JDK1.8是会错的。但是JDK1.6是正常的。

变量

利用Java反射可以在运行时得到一个类的变量信息，并且可以根据上面讲的方式，创建一个对象，设置他的变量值。首先，通过如下方法，得到所有public的变量：

   Field[] fields = exampleObjectClass.getFields();
    for (Field field : fields){
            System.out.println("变量为： " + field.toString());
    }
结果如下：

变量为： public int cn.byhieg.reflectiontutorial.ExampleObject.age
变量为： public java.lang.String cn.byhieg.reflectiontutorial.ExampleObject.name
很显然，得到的都是public的变量，上述的private的变量score，并没有得到。
和构造器一样的得到方式一样，我们可以指定一个参数名，然后得到指定的变量：

        Field field = exampleObjectClass.getField("age");
        System.out.println("变量为:" + field.toString());
上述的变量的toString方法得到的名字太长，Java对Field类提供了getName的方法，返回类中写的变量名字，上面的代码就可以改成field.getName()。
反射不仅提供了得到变量的方法，还提供了设置变量值的方式。通过如下方法可以对一个动态生成的类，改变其变量值：

    ExampleObject object = ((ExampleObject) constructor1.newInstance("byhieg"));
        System.out.println("原先的age是 " + object.age);
        field.set(object,10);
        System.out.println("更改之后的age是" + object.age);
结果如下：

原先的age是 30
更改之后的age是10
根据上面的代码，得到名字为age的Field对象，然后调用该对象的set方法，传入一个对象与要更改的值，就可以改变该对象的值了。注意，此方法不仅仅对成员变量有用，对静态变量也可以。当然，如果是静态变量，传入null，不用传对象，也是可以的。

方法

Java反射给我们除了给我们提供类的变量信息之外，当然也给我们提供了方法的信息，反射可以让我们得到方法名，方法的参数，方法的返回类型，以及调用方法等功能。
首先，通过如下代码得到方法：

        //输出类的public方法
        Method[] methods = exampleObjectClass.getMethods();
        for (Method method : methods){
            System.out.println("method = "+ method.getName());
        }
和获取变量一样似曾相识的代码，这里直接调用了getName，来得到类中写的方法名。写到这里，大家应该自然想到，Java同样提供了根据参数，得到具体的方法。

        Method method = exampleObjectClass.getMethod("setAge",int.class);
        System.out.println(method.getName());
这里与得到变量不同的是，getMethod方法还需要传入参数的类型信息，反射提供获取方法参数以及返回类型的方法，得到方法参数的例子如下：

        Method method = exampleObjectClass.getMethod("setAge",int.class);
        System.out.println(method.getName());
        for (Class clz : method.getParameterTypes()){
            System.out.println("方法的参数" + clz.getName());
        }
结果如下：

setAge
方法的参数int
得到方法返回类型的例子如下：

System.out.println(method.getReturnType().getName());
结果如下：

void
此外，Java反射支持通过invoke调用得到的方法。例子如下：

method.invoke(exampleObjectClass.newInstance(),1);
invoke第一个参数是这个对象，第二个参数是变长数组，传入该方法的参数。和Field对象同样，对于静态方法同样，可以传入null，调用静态方法。

私有变量与私有方法

上面的方法只能得到public方法和变量，无法得到非public修饰的方法和变量，Java提供了额外的方法来得到非public变量与方法。即通过getDeclaredFields与getDeclaredMethods方法得到私有的变量与方法，同样也支持用getDeclaredField（变量名）与getDeclaredMethod（方法名)的形式得到指定的变量名与方法名。但是这样得到的Field对象与Method对象无法直接运用，必须让这些对象调用setAccessible(true),才能正常运用。之后的方式就可上面讲的一样了。

注解

先写一个包含注解的类：

MyAnnotation(name="byhieg",value = "hello world")
public class AnnotationObject {

    @MyAnnotation(name="field",value = "变量")
    public String field;

    @MyAnnotation(name="method",value = "方法")
    public void doSomeThing(){
        System.out.println("做一些事情");
    }

    public void doOtherThing(@MyAnnotation(name="param",value = "参数") String param){

    }
}

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String name();
    public String value();

}
Java给我们提供了在运行时获取类的注解信息，可以得到类注解，方法注解，参数注解，变量注解。
与上面获取方式一样，Java提供了2种获取方式，一种是获取全部的注解，返回一个数组，第二种是指定得到指定的注解。
我们以一个类注解为例，讲解以下这两种获取方式。

 Class clz = AnnotationObject.class;
 Annotation[] annotations = clz.getAnnotations();
 Annotation annotation = clz.getAnnotation(AnnotationObject.class);
然后，就可以根据得到的注解进行后续的处理，下面是一个处理的例子:

 for (Annotation annotation : annotations){
            if (annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation)annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value:" + myAnnotation.value());
            }
        }
上面的类注解使用Class对象调用getAnnotations得到的，方法注解和变量注解是一样的，分别用method对象与field对象调用getDeclaredAnnotations得到注解，没什么多说的。例子看反射代码
参数注解是比较麻烦的一项，获取方式比较得到，第一步，先取得method对象，调用getParameterAnnotations，但是这个返回值是一个二维数组，因为method对象有很多参数，每个参数有可能有很多注解。例子如下：

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
泛型

因为Java泛型是通过擦除来实现的，很难直接得到泛型具体的参数化类型的信息，但是我们可以通过一种间接的形式利用反射得到泛型信息。比如下面这个类：

public class GenericObject {
    public List<String> lists;

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
}
如果一个方法返回一个泛型类，我们可以通过method对象去调用getGenericReturnType来得到这个泛型类具体的参数化类型是什么。看下面的代码：

 Class clz = GenericObject.class;
        Method method = clz.getMethod("getLists");
        Type genericType = method.getGenericReturnType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) genericType);
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type type : types){
                Class actualClz = ((Class) type);
                System.out.println("参数化类型为 ： " + actualClz);
            }
        }
结果如下：

参数化类型为 ： class java.lang.String
步骤有点繁琐，下面一步步解释：

反射得到返回类型为泛型类的方法
调用getGenericReturnType得到方法返回类型中的参数化类型
判断该type对象能不能向下转型为ParameterizedType
转型成功，调用getActualTypeArguments得到参数化类型的数组，因为有的泛型类，不只只有一个参数化类型如Map<K，V>
取出数组中的每一个的值，转型为Class对象输出。
看结果确实得到了泛型的具体的信息。
如果没有一个方法返回泛型类型，那么我们也可以通过方法的参数为泛型类，来得到泛型的参数化类型，如上面类中的setLists方法。例子如下：

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
执行的结果如下：

GenericParameterTypes为 ： java.util.List<java.lang.String>
ParameterizedType为 :java.util.List<java.lang.String>
参数化类型为 ： java.lang.String
因为方法的参数为泛型类型的可能不止一个，所以通过getGenericParameterTypes得到是一个数组，我们需要确定每一个元素，是否是具有参数化类型。后续的步骤与上面类似，就不多说了。
如果连方法参数都不带泛型类，那么只剩下最后一种情况，通过变量类型，即用Field类。例子如下：

        Field field = clz.getField("lists");
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = ((ParameterizedType) type);
            Type [] types = parameterizedType.getActualTypeArguments();
            for (Type type1 : types) {
                System.out.println("参数化类型 ： " + ((Class) type1).getTypeName());
            }
        }
原理和上面的一样，只不过Type对象是通过field.getGenericType()，剩下的操作类似就不多说了。
关于通过反射获取泛型的参数化类型的信息的介绍就到此为止。

数组

Java反射可以对数组进行操作，包括创建一个数组，访问数组中的值，以及得到一个数组的Class对象。
下面，先说简单的，创建数组以及访问数组中的值：在反射中使用Array这个类，是reflect包下面的。

    //创建一个int类型的数组，长度为3
   int[] intArray = (int[])Array.newInstance(int.class,3);
   //通过反射的形式，给数组赋值
        for (int i = 0 ;i < intArray.length;i++){
            Array.set(intArray,i,i + 2);
        }
//通过反射的形式，得到数组中的值
        for (int i = 0 ; i < intArray.length;i++){
            System.out.println(Array.get(intArray,i));
        }
上述就是创建数组，访问数组中的值利用反射方式。
对于得到一个数组的Class对象，简单的可以用int[].class，或者利用Class.forName的形式得到，写法比较奇怪：

 Class clz = Class.forName("[I");
 System.out.println(clz.getTypeName());
结果为：

int[]
这个forName中的字符串，[表示是数组，I表示是int，float就是F，double就是D等等，如果要得到一个普通对象的数组，则用下面的形式：

  Class stringClz = Class.forName("[Ljava.lang.String;");
[表示是数组,L的右边是类名，类型的右边是一个；；
这种方式获取数组的Class对象实在是太繁琐了。
在得到数组的Class对象之后，就可以调用他的一些独特的方法，比如调用getComponentType来得到数组成员的类型信息，如int数组就是成员类型就是int。

System.out.println(clz.getComponentType().getTypeName());
结果为int

总结

这次,关于反射的各种应用就到此为止，后续可能会有深入的知识讲解。具体的代码可以去看反射代码
在src包里面是各种类，在test类里是对这些类的访问。
