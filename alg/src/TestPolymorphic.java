class Parent {
    public int age;
    public Parent() {
        age = 40;
    }
    public int get(){
        return age;
    }
    void eat() {
        System.out.println("父亲在吃饭");
    }
}

class Child extends Parent {
    public int age;

    public Child() {
        age = 18;
    }
    void eat() {
        System.out.println("孩子在吃饭");
    }
    public int get(){
        return age;
    }
    void play() {
        System.out.println("孩子在打CS");
    }
}
public class TestPolymorphic {
    public static void main(String[] args) {
        Parent c = new Child();
        c.eat();
//        c.play();
        Child c2 = (Child) c;
        System.out.println("年龄：" + c.age);
        System.out.println("年龄：" + c.get());
        System.out.println("年龄：" + c2.age);
    }
}