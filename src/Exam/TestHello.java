package Exam;

import sun.rmi.runtime.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHello {
    public static void main(String[] args) {
        List<Field> fieldList = new ArrayList<>() ;
        Class tempClass = Hello.class;
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        Field[] fields = tempClass.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field);
        }

        Method[] methods = tempClass.getDeclaredMethods();
        for(Method method : methods) {
            System.out.println(method);
        }
    }
    class Hello {
        private String  a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
