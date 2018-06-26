package com.csx.concurrency.example.immutable;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.csx.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-20
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map2=ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).put(5,6).build();


    public static void main(String[] args) {
//        list.add(4);
        map2.put(1,3);
        System.out.println(map2.get(3));
    }


}
