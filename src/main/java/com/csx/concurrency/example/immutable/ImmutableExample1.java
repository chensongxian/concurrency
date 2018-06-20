package com.csx.concurrency.example.immutable;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-20
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a=1;
    private final static String b="2";
    private final static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//         a=2;
//        b="3";
//        map=Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    private void test(final int a){
//        a=1;
    }

}
