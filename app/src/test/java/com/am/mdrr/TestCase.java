package com.am.mdrr;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.am.mdrr.utils.RandomUtils;

import org.junit.Test;

import java.util.Random;

/**
 * Created by AM on 2016/7/19.
 */
public class TestCase extends junit.framework.TestCase {

    @Test
    public void test1(){
        Random random = new Random();
        int i = random.nextInt(2);
        System.out.println("===========   "+i);
    }

}
