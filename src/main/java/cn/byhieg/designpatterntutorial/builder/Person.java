package cn.byhieg.designpatterntutorial.builder;

/**
 * Created by shiqifeng on 2017/5/7.
 * Mail byhieg@gmail.com
 */
public class Person {

    private int age;
    private String name;
    private int height;
    private int weight;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Person(Builder builder) {
        age = builder.age;
        name = builder.name;
        height = builder.height;
        weight = builder.weight;
    }

    @Override
    public String toString() {
        return "age = " + age + " name  = " + name + " height = " + height + " weight = " + weight;
    }

    public static class Builder{

        private int age;
        private String name;
        private int height;
        private int weight;

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
