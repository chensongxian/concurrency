package com.csx.concurrency.example.publish;

import com.csx.concurrency.annoations.NotThreadSafe;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-06-20
 */
@Slf4j
@NotThreadSafe
public class UnSafePublish {
    private String[] states={"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnSafePublish unSafePublish=new UnSafePublish();
        log.info("{}", Arrays.toString(unSafePublish.getStates()));

        unSafePublish.getStates()[0]="d";
        log.info("{}",Arrays.toString(unSafePublish.getStates()));
    }
}
