package com.csx.concurrency.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author csx
 * @Package com.csx.concurrency.guava
 * @Description: TODO
 * @date 2018/8/7 0007
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer value1 = null;
        Optional<Integer> optional = Optional.ofNullable(value1);
        System.out.println(optional.orElse(10));
        System.out.println(optional.isPresent());

        ArrayList<Object> arrayList = Lists.newArrayList();
        Optional<ArrayList<Object>> arrayListoptional = Optional.ofNullable(arrayList);

        ArrayList<Integer> newArrayList = Lists.newArrayList();
        newArrayList.add(1);
        newArrayList.add(2);
        newArrayList.add(null);

        List<Integer> transform = Lists.transform(newArrayList, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        });
        System.out.println(transform);

    }
}
