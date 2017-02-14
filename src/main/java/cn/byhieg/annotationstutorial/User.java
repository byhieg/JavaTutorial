package cn.byhieg.annotationstutorial;

/**
 * Created by byhieg on 17/2/14.
 * Mail to byhieg@gmail.com
 */
public class User {

    public String name;
    public int age;

    @AConstructor
    public User(){

    }

    @Override
    public String toString() {
        return "名字是 " + name + "年龄是 " + age;
    }

    @AMethod(method = "Sleep",value = "60m")
    public void doSomeThing(){
        System.out.println("做一些事情");
    }

}
