package cn.byhieg.algorithmtutorial;

/**
 * Created by shiqifeng on 2017/3/28.
 * Mail byhieg@gmail.com
 */
public class Sort {

    /**
     * 选择排序，每一轮排序，选择数组中数字最小的那一个放到指定的位置上。
     * 时间复杂度o(n^2),无论数组顺序如何都要选择一个最小的值，因为数组的是否有序，不影响时间复杂度
     * 空间复杂度o(1)
     * @param nums
     */
    public void chooseSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int min = i;//申请额外的空间o(1)
            for (int j = i + 1; j < length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            //将最小的下标代表的数与i位置的进行交换
            int tmp = nums[i];
            nums[i] = nums[min];
            nums[min] = tmp;
        }
    }

    /**
     * 直接插入排序，每一轮排序，都是在i坐标之前，包括i坐标的序列是有序的，但是并不是最终的排序位置。
     * 时间复杂度o（n^2),对于第二重循环，只会在非有序的环境下才会执行每个元素后移，因此针对有序的数组，时间复杂度最好的情况是o(N)。
     * 空间复杂度o(1)
     * @param nums
     */
    public void insertDirectlySort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
           for (int j = i ; j > 0; j --) {
               if (nums[j] < nums[j - 1]) {
                   int tmp = nums[j - 1];
                   nums[j - 1] = nums[j];
                   nums[j] = tmp;
               }
           }
        }
    }

    /**
     * 折半插入排序，针对直接排序而言，每一个要插入的元素都是插入在有序的数组中，因此，只需要查找到插入的位置即可，查找的方式利用二分查找
     * 时间复杂度和直接插入是一样的，只是快在了查找的过程中，还是o(N^2),最好的环境下是o（N）
     * 空间复杂度还是o(1)
     * @param nums
     */
    public void insertBinarySort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length;i++) {
            int tmp = nums[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (tmp < nums[mid]) {
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }

            for (int j = i; j >= low + 1;j--) {
                nums[j] = nums[j - 1];
            }
            nums[low] = tmp;
        }
    }

    /**
     * 冒泡排序，每i轮排序，就是不断交换两个元素，直到将最大的元素放到n - i的位置上
     * 这种实现是按照算法定义的，但是效率是最低的
     * 时间复杂度o(n^2)
     * 空间复杂度o(1)
     * @param nums
     */
    public void bubbleSort1(int[] nums) {
        int length = nums.length;
        for (int i = 1 ; i < length;i++) {
            for (int j = 0 ; j < length - i;j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序，高效率实现，因为只需要用一个flag变量来记录本次的排序，是否修改
     * 如果没有修改，说明已经有序
     * @param nums
     */
    public void bubbleSort2(int[] nums) {
        int length = nums.length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0 ; j < length - 1;j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;
                }
            }
            length -- ;
        }
    }

    /**
     * 快速排序，选定一个切分元素，每一轮排序后，都保证切分元素之前的元素都小于切分元素，切分元素之后的元素都大于切分元素
     *
     * @param nums
     */
    public void quickSort(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        sort(nums, low, high);
    }

    /**
     * 快速排序的递归实现
     * @param nums
     * @param low
     * @param high
     */
    public void sort(int[] nums, int low, int high) {
        if (low >= high) return;
        int j = partition(nums, low, high);
        sort(nums, low, j - 1);
        sort(nums, j + 1, high);
    }

    /**
     * 快速排序的辅助方法，来对排序的数组，进行切分,
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] nums, int low, int high) {
        int i = low;
        int j = high;
        int x = nums[i];
        while (i < j) {
            //从右向左找到nums[j]小于x的元素
            while (i < j && nums[j] > x) j-- ;
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }

            //从左向右找到nums[i]大于x的元素
            while (i < j && nums[i] < x) i++;
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = x;
        return i;
    }

    

}
