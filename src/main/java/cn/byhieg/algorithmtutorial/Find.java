package cn.byhieg.algorithmtutorial;

/**
 * Created by shiqifeng on 2017/3/29.
 * Mail byhieg@gmail.com
 */
public class Find {


    /**
     * 二查查找算法，要求准确找到目标值，没有找到则是-1.
     * 此方法保证在相同元素都满足条件时，取到的是最大的下标
     * 时间复杂度 o(lgN)
     * @param nums int型数组，要求有序
     * @return 找到，返回下标，没找到，返回-1
     */
    public int binarySearchFind(int[] nums,int des) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == des) {
                return mid;
            } else if (nums[mid] < des) {
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 给定一个单调不降的数组，查找大于des条件的最小的数
     * @param nums
     * @param des
     * @return
     */
    public int binarySearchMinFind(int[] nums, int des) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        int mid ;
        while (low < high) {
            mid = (low + high) / 2;
           if (nums[mid] <= des){
               low = mid + 1;
           }else{
               high = mid;
           }
        }
        if (nums[high] > des) return high;
        return -1;
    }

    /**
     * 给定一个单调不降的数组，查找小于des条件的最大的数
     * @param nums
     * @param des
     * @return
     */
    public int binarySearchMaxFind(int[] nums, int des) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        int mid;
        int result = -1;
        while (low < high) {
            mid = low + (high - low + 1) / 2;
            if (nums[mid] < des){
                low = mid;
            }else{
                high = mid - 1;
            }
        }
        if (nums[low] < des) return low;
        return -1;
    }



}
