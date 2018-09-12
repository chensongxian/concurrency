package com.csx.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @author: csx
 * @Date: 2018-09-12
 */
public class NumToChinese {
    private static final String[] FIGURE_CN =
            { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    //自定义单位数组
    private static final String[] UNIT_CN =
            {"", "十", "百", "千","万"};

    public static void main(String[] args) {
        String ch = numToCh(7);
        System.out.println(ch);
    }

    public static String numToCh(int num){
        StringBuilder builder = new StringBuilder();
        List<Integer> figureList = new ArrayList<>();
        while (num>0){
            int figure = num % 10;
            figureList.add(figure);
            num = num / 10;
        }

        for(int i = figureList.size()-1;i>=0;i--){
            int figure = figureList.get(i);
            builder.append(FIGURE_CN[figure]);
            builder.append(UNIT_CN[i]);
        }
        return builder.toString();
    }
}
