package Exam;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*首先，输入一段字符串作为字符数组*/
        System.out.println("请输入一段字符串");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        countLeters(chars);
    }
    private static void countLeters(char[] arr) {
        /*然后，初始化一个长度为26的整型数组,初始值全为0*/
        int[] temp = new int[26];
        for (int i = 0; i < temp.length; i++) {
            temp[i]=0;
        }
        /*遍历字符数组，将其值对应的ascll值作为整型数组下标,++*/
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>='a'&&arr[i]<='z'){
                temp[arr[i]-'a']++;
            }
        }
        /*最后打印输出每一个小写英文字母及其出现的次数*/
        for (int i = 0; i <26 ; i++) {
            if (temp[i] > 0){
                System.out.println((char)(i+'a')+"出现了 ： "+temp[i]+" 次");
            }
        }
    }
}
