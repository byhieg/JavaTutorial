package cn.byhieg.basistutorial;

/**
 * Created by byhieg on 17/1/16.
 * Mail to byhieg@gmail.com
 */
public class ExtendFather extends ExampleFather{

    private int age = 20;

    @Override
    public int getAge() {
        return super.getAge();
    }


    public ExtendFather(){
        super(10);
        this.age = 30;
    }

    public ExtendFather(int age){
        super(60);
        this.age = age;
    }
}
