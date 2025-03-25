package com.lordbao.binSearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/12/10 10:09
 * @Version 1.0
 */
class BinarySearchTest {

    @Test
    void binSearch0A() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,3,5,7,9};
        //target在nums中
        Assertions.assertEquals(1,demo.binSearch0A(nums,3));
        Assertions.assertEquals(3,demo.binSearch0A(nums,7));
        Assertions.assertEquals(0,demo.binSearch0A(nums,1));
        Assertions.assertEquals(4,demo.binSearch0A(nums,9));


        //target未在nums中
        Assertions.assertEquals(-1,demo.binSearch0A(nums,-1));
        Assertions.assertEquals(-1,demo.binSearch0A(nums,4));
        Assertions.assertEquals(-1,demo.binSearch0A(nums,10));

    }
    @Test
    void binSearch0B() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,3,5,7,9};
        //target在nums中
        Assertions.assertEquals(1,demo.binSearch0B(nums,3));
        Assertions.assertEquals(3,demo.binSearch0B(nums,7));
        Assertions.assertEquals(0,demo.binSearch0B(nums,1));
        Assertions.assertEquals(4,demo.binSearch0B(nums,9));


        //target未在nums中
        Assertions.assertEquals(-1,demo.binSearch0B(nums,-1));
        Assertions.assertEquals(-1,demo.binSearch0B(nums,4));
        Assertions.assertEquals(-1,demo.binSearch0B(nums,10));

    }

    @Test
    void binSearch0C() {
        BinarySearch demo = new BinarySearch();
        //易错的测试
        int [] nums1 = {0,2};
        Assertions.assertEquals(-1,demo.binSearch0C0(nums1,1));
        Assertions.assertEquals(-1,demo.binSearch0C1(nums1,1));

        //测试版本0
        int [] nums = {1,3,5,7,9};
        //target在nums中
        Assertions.assertEquals(1,demo.binSearch0C0(nums,3));
        Assertions.assertEquals(3,demo.binSearch0C0(nums,7));
        Assertions.assertEquals(0,demo.binSearch0C0(nums,1));
        Assertions.assertEquals(4,demo.binSearch0C0(nums,9));


        //target未在nums中
        Assertions.assertEquals(-1,demo.binSearch0C0(nums,-1));
        Assertions.assertEquals(-1,demo.binSearch0C0(nums,4));
        Assertions.assertEquals(-1,demo.binSearch0C0(nums,10));


        //测试版本1
        //target在nums中
        Assertions.assertEquals(1,demo.binSearch0C1(nums,3));
        Assertions.assertEquals(3,demo.binSearch0C1(nums,7));
        Assertions.assertEquals(0,demo.binSearch0C1(nums,1));
        Assertions.assertEquals(4,demo.binSearch0C1(nums,9));


        //target未在nums中
        Assertions.assertEquals(-1,demo.binSearch0C1(nums,-1));
        Assertions.assertEquals(-1,demo.binSearch0C1(nums,4));
        Assertions.assertEquals(-1,demo.binSearch0C1(nums,10));

    }


    @Test
    void binSearch0D() {
        BinarySearch demo = new BinarySearch();
        //易错的测试
        int [] nums1 = {0,2};
        Assertions.assertEquals(-1,demo.binSearch0D(nums1,1));
        Assertions.assertEquals(-1,demo.binSearch0D(nums1,1));

        int [] nums = {1,3,5,7,9};
        //target在nums中
        Assertions.assertEquals(1,demo.binSearch0D(nums,3));
        Assertions.assertEquals(3,demo.binSearch0D(nums,7));
        Assertions.assertEquals(0,demo.binSearch0D(nums,1));
        Assertions.assertEquals(4,demo.binSearch0D(nums,9));


        //target未在nums中
        Assertions.assertEquals(-1,demo.binSearch0D(nums,-1));
        Assertions.assertEquals(-1,demo.binSearch0D(nums,4));
        Assertions.assertEquals(-1,demo.binSearch0D(nums,10));

    }

    @Test
    void binSearch1A() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,1,1,3,3,3,7};
        Assertions.assertEquals(2,demo.binSearch1A(nums,1));
        Assertions.assertEquals(5,demo.binSearch1A(nums,3));
        Assertions.assertEquals(-1,demo.binSearch1A(nums,-1));
        Assertions.assertEquals(6,demo.binSearch1A(nums,8));

        int [] nums1 = {1};
        Assertions.assertEquals(0,demo.binSearch1A(nums1,1));
        Assertions.assertEquals(0,demo.binSearch1A(nums1,2));
        Assertions.assertEquals(-1,demo.binSearch1A(nums1,-1));
    }

    @Test
    void binSearch2A() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,1,1,3,3,3,7};
        Assertions.assertEquals(0,demo.binSearch2A(nums,1));
        Assertions.assertEquals(3,demo.binSearch2A(nums,3));
        Assertions.assertEquals(0,demo.binSearch2A(nums,-1));
        Assertions.assertEquals(7,demo.binSearch2A(nums,8));

        int [] nums1 = {1};
        Assertions.assertEquals(0,demo.binSearch2A(nums1,1));
        Assertions.assertEquals(1,demo.binSearch2A(nums1,2));
        Assertions.assertEquals(0,demo.binSearch2A(nums1,-1));
    }

    @Test
    void binSearch3A() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,1,1,3,3,3,7};
        Assertions.assertEquals(-1,demo.binSearch3A(nums,1));
        Assertions.assertEquals(2,demo.binSearch3A(nums,3));
        Assertions.assertEquals(5,demo.binSearch3A(nums,7));
        Assertions.assertEquals(6,demo.binSearch3A(nums,8));

        int [] nums1 = {1};
        Assertions.assertEquals(-1,demo.binSearch3A(nums1,1));
        Assertions.assertEquals(0,demo.binSearch3A(nums1,2));
        Assertions.assertEquals(-1,demo.binSearch3A(nums1,-1));
    }

    @Test
    void binSearch4A() {
        BinarySearch demo = new BinarySearch();
        int [] nums = {1,1,1,3,3,3,7};
        Assertions.assertEquals(0,demo.binSearch4A(nums,-1));
        Assertions.assertEquals(3,demo.binSearch4A(nums,1));
        Assertions.assertEquals(6,demo.binSearch4A(nums,3));
        Assertions.assertEquals(7,demo.binSearch4A(nums,7));

        int [] nums1 = {1};
        Assertions.assertEquals(1,demo.binSearch4A(nums1,1));
        Assertions.assertEquals(1,demo.binSearch4A(nums1,2));
        Assertions.assertEquals(0,demo.binSearch4A(nums1,0));
    }
}