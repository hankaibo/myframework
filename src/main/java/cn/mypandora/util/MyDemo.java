package cn.mypandora.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kaibo on 2015/5/26.
 * desc
 */
public class MyDemo {

    public static void main(String[] args) {
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();

        set1.add("1");
        set1.add("2");
        set1.add("3");

        set2.add("3");
        set2.add("4");

        set1.removeAll(set2);

        for (String foo:set1){
            System.out.println(foo);
        }

        for (String foo:set2){
            System.out.println(foo);
        }
    }
}
