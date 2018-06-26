package com.csx.concurrency.example.immutable;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.csx.concurrency.annoations.ThreadSafe;
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
public class ImmutableExample2 {
    private static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }


}
