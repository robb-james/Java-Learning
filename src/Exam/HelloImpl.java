package Exam;

public class HelloImpl extends AbstractHello {
    @Override
    public void method2() {
        System.out.println("这是method2");
    }

    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        hello.method1();
    }
}
