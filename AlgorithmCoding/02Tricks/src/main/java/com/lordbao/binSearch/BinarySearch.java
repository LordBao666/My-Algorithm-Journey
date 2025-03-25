package com.lordbao.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/12/10 10:04
 * @Version 1.0
 *
 * 二分查找的代码模板
 */
public class BinarySearch {

    /**
     * 左闭右闭版本
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标,如果不存在,则返回-1
     */
    public int binSearch0A(int [] nums, int target){
        int lo = 0,hi = nums.length-1;
        while (lo<=hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<target){//target在nums[mi]右侧
                lo=mi+1;
            }else if(target<nums[mi]){//target在nums[mi]左侧
                hi=mi-1;
            }else {
                return mi;
            }
        }
        return -1;
    }

    /**
     * 左闭右开版本
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标,如果不存在,则返回-1
     */
    public int binSearch0B(int [] nums,int target){
        int lo = 0,hi = nums.length;
        while (lo<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<target){//target在nums[mi]右侧
                lo=mi+1;
            }else if(target<nums[mi]){//target在nums[mi]左侧
                hi=mi;
            }else {
                return mi;
            }
        }
        return -1;
    }

    /**
     * 左开右闭版本
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标,如果不存在,则返回-1
     */
    public int binSearch0C0(int [] nums,int target){
        int lo = -1,hi = nums.length-1;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<target){//target在nums[mi]右侧
                lo=mi;
            }else if(target<nums[mi]){//target在nums[mi]左侧
                hi=mi-1;
            }else {
                return mi;
            }
        }
        if(lo+1==hi){
            return nums[hi]== target? hi:-1;
        }
        return -1;
    }

    /**
     * 左开右闭版本
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标,如果不存在,则返回-1
     */
    public int binSearch0C1(int [] nums,int target){
        int lo = -1,hi = nums.length-1;
        while (lo<hi){
            //当lo,hi奇偶性相同,则flag为0;奇偶性不同,则flag为1
            int flag = (lo&1)^(hi&1);
            int mi = lo + ((hi-lo+flag)>>1);//避免lo+hi溢出，其中+flag是使得mi 始终为 (lo+hi)/2 向上取整
            if(nums[mi]<target){//target在nums[mi]右侧
                lo=mi;
            }else if(target<nums[mi]){//target在nums[mi]左侧
                hi=mi-1;
            }else {
                return mi;
            }
        }
        return -1;
    }

    /**
     * 左开右开
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标,如果不存在,则返回-1
     */
    public int binSearch0D(int [] nums,int target){
        int lo = -1,hi = nums.length;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<target){//target在nums[mi]右侧
                lo=mi;
            }else if(target<nums[mi]){//target在nums[mi]左侧
                hi=mi;
            }else {
                return mi;
            }
        }
        return -1;
    }

    /**
     *
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回有序数组中nums[i] <= target的最大i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为1，那么i就是2；
     * target为3，那么i就是5；
     * target为-1，那么i就是-1，此时表示数组中不存在小于等于target的元素；
     * target为8，那么i就是6。
     */
    public int binSearch1A(int [] nums,int target){
        //给nums增加2个虚拟值
        // lo=-1表示nums[-1]=Integer.MIN_VALUE;
        // hi=nums.length表示 nums[nums.length]=Integer.MAX_VALUE;
        int lo = -1,hi = nums.length;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<=target){
                lo=mi;
            }else {
                hi=mi;
            }
        }
        return lo;
    }

    /**
     *
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回有序数组中  target <= nums[i]的最小i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为1，那么i就是0；
     * target为3，那么i就是3；
     * target为-1，那么i就是0
     * target为8，那么i就是7(nums.length), 此时表示数组中不存在元素大于等于target。
     */
    public int binSearch2A(int [] nums,int target){
        //给nums增加2个虚拟值
        // lo=-1表示nums[-1]=Integer.MIN_VALUE;
        // hi=nums.length表示 nums[nums.length]=Integer.MAX_VALUE;
        int lo = -1,hi = nums.length;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(target<=nums[mi]){
                hi=mi;
            }else {
                lo=mi;
            }
        }
        return hi;
    }

    /**
     *
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回有序数组中   nums[i] < target的最大i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为1，那么i就是-1,表示数组中不存在元素小于target.
     * target为3，那么i就是2；
     * target为7，那么i就是5
     * target为8，那么i就是6.
     */
    public int binSearch3A(int [] nums,int target){
        //给nums增加2个虚拟值
        // lo=-1表示nums[-1]=Integer.MIN_VALUE;
        // hi=nums.length表示 nums[nums.length]=Integer.MAX_VALUE;
        int lo = -1,hi = nums.length;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(target<=nums[mi]){
                hi=mi;
            }else {
                lo=mi;
            }
        }
        return lo;
    }

    /**
     *
     * @param nums 有序数组
     * @param target 目标值
     * @return 返回有序数组中   target  <  nums[i]的最小i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为-1，那么i就是0,
     * target为1，那么i就是3；
     * target为3，那么i就是6
     * target为7，那么i就是7,此时表明数组中不存在元素大于target.
     */
    public int binSearch4A(int [] nums,int target){
        //给nums增加2个虚拟值
        // lo=-1表示nums[-1]=Integer.MIN_VALUE;
        // hi=nums.length表示 nums[nums.length]=Integer.MAX_VALUE;
        int lo = -1,hi = nums.length;
        while (lo+1<hi){
            int mi = lo + ((hi-lo)>>1);//避免lo+hi溢出
            if(nums[mi]<=target){
                lo=mi;
            }else {
                hi=mi;
            }
        }
        return hi;
    }
}
