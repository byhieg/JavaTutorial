package cn.byhieg.reflectiontutorial;

/**
 * Created by shiqifeng on 2017/1/9.
 * Mail byhieg@gmail.com
 */
public class ExampleObject extends FatherObject {
    public int age = 30;
    public String name = "byhieg";
    private Integer score = 60;

    public void printName() {
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

    public ExampleObject() {

    }

    public ExampleObject(String name) {

    }

    public ExampleObject(int age, Integer score) {

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
