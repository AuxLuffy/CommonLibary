package com.lenovo.service.basicpubliclibrary.flowwateranimation.helper;

import java.util.Random;

/**
 * Created by tangrenmei on 2017/8/25.
 */

public class MathHelper {
    public static Random rand = new Random();
    public static float randomRange(float min, float max) {


        int randomNum = rand.nextInt(((int) max - (int) min) + 1) + (int) min;

        return randomNum;
    }
}

