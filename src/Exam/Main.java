package Exam;

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.say();
    }
}
class Father {
    public void say() {
        System.out.println("father");
    }
}
class Child extends Father{
    public void say(){
        super.say();
        System.out.println("child");
    }
}
