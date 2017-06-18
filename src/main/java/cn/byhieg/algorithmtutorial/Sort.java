package cn.byhieg.algorithmtutorial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by shiqifeng on 2017/3/28.
 * Mail byhieg@gmail.com
 */
public class Sort {

    /**
     * 选择排序，每一轮排序，选择数组中数字最小的那一个放到指定的位置上。
     * 时间复杂度o(n^2),无论数组顺序如何都要选择一个最小的值，因为数组的是否有序，不影响时间复杂度
     * 空间复杂度o(1)
     * 不稳定排序
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
     * 稳定排序
     * @param nums
     */
    public void insertDirectlySort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                //这一步导致该算法是稳定排序
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
     *
     * @param nums
     */
    public void insertBinarySort(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int tmp = nums[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (tmp < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            for (int j = i; j >= low + 1; j--) {
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
     *  稳定排序
     * @param nums
     */
    public void bubbleSort1(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                //这一步导致该算法是稳定排序
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序，高效率实现，因为只需要用一个flag变量来记录本次的排序，是否修改
     * 如果没有修改，说明已经有序
     *
     * @param nums
     */
    public void bubbleSort2(int[] nums) {
        int length = nums.length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = true;
                }
            }
            length--;
        }
    }

    /**
     * 归并排序，将数组一分为二，对于每一子数组继续进行上述步骤，直到子数组只有1个元素，那么自然是有序的。
     * 然后不断合并两个数组，直到合并到整个数组。
     * 时间复杂度o(NlgN)
     * 空间复杂度o(N)
     * @param nums
     */
    public void mergeSort(int[] nums) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        realSort(nums, low, high);
    }

    /**
     * 归并排序真正的sort函数
     * @param nums 待排序的数组
     * @param low 最低位
     * @param high 最高位
     */
    private void realSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            realSort(nums, low, mid);
            realSort(nums, mid + 1, high);
            realMerge(nums, low, mid, high);
        }
    }

    private void realMerge(int[] nums, int low, int mid, int high) {
        int[] tmpNums = new int[high - low + 1];
        int leftPoint = low;
        int rightPoint = mid + 1;
        int index = 0;

        while (leftPoint <= mid && rightPoint <= high) {
            if (nums[leftPoint] < nums[rightPoint]) {
                tmpNums[index++] = nums[leftPoint++];
            }else{
                tmpNums[index++] = nums[rightPoint++];
            }
        }

        while (leftPoint <= mid) {
            tmpNums[index++] = nums[leftPoint++];
        }
        while (rightPoint <= high) {
            tmpNums[index++] = nums[rightPoint++];
        }

        System.arraycopy(tmpNums, 0, nums, low, tmpNums.length);
    }

    /**
     * 快速排序，选定一个切分元素，每一轮排序后，都保证切分元素之前的元素都小于切分元素，切分元素之后的元素都大于切分元素
     * 时间复杂度o(NlgN)
     * 空间复杂度o(lgN)
     * 不稳定排序
     * @param nums
     */
    public void quickSort(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        sort(nums, low, high);
    }

    /**
     * 快速排序的递归实现
     *
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
     *
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
            while (i < j && nums[j] >= x) j--;
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }

            //从左向右找到nums[i]大于x的元素
            while (i < j && nums[i] <= x) i++;
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = x;
        return i;
    }


    /**
     * 堆排序，建立一个小顶堆，小顶堆满足父节点比两个子节点的值要小
     * 堆的性质满足:1. 只能在堆顶删除元素
     *            2. 只能在堆的最后一位存元素。
     *            3. 堆的存储利用数组，满足i节点是父节点，则子节点是2 * i+ 1,2 * i + 2
     *            4. 堆的两种建方法，第一种是从上到下，@see sink（）,第二种是从下到上 @see swim
     *            5. 堆排序是指在弄好的堆中，输出第一个元素，然后将最后一个元素与第一个元素互换，换后调用sink，找到自己的位置后，在重复这个步骤，就输出一个有序的堆
     *            6. 如果要生序就需要大顶堆，要降序就需要小顶堆。
     * 时间复杂度：o(NlgN)
     * 空间复杂度: o(1)
     * 这是小顶堆的排序，所以nums数组最后是降序的
     * 不稳定，不稳定的原因在建堆的时候，就可能把相同元素的位置换了，比如两个相同元素在不同的子树上
     * @param nums
     */
    public void heapSort(int[] nums) {
        int length = nums.length;
//        for (int i = 0; i < length; i++) {
//            swim(nums, i);
//        }
        //只能从前一半开始sink
        for (int i = length / 2 ; i >= 0;i--) {
            sink(nums,i,length);
        }
        while (length > 0) {
            int temp = nums[0];
            nums[0] = nums[length - 1];
            nums[length - 1] = temp;
            length--;
            sink(nums, 0, length);
        }
    }

    /**
     * 将i放入对堆中,i的父节点是（i - 1）/ 2,父节点的值是小于他的两个子节点的
     * i节点放入后，要向上移动，如果父节点比i节点的值大，则i节点要继续上移。
     *
     * @param nums
     * @param i
     */
    private void swim(int nums[], int i) {
        while (i > 0) {
            int father = (i - 1) / 2;
            if (nums[father] > nums[i]) {
                int temp = nums[father];
                nums[father] = nums[i];
                nums[i] = temp;
            }
            i = father;
        }
    }


    /**
     * 从i节点由上到下开始调整，i节点的子节点为2*i + 1, 2 * i + 2
     * i节点要向下移动，直到满足i节点小于两个子节点
     *
     * @param nums nums[] 数组
     * @param i    i节点
     */
    public void sink(int [] nums, int i,int n) {
        int son = 2 * i + 1;
        while (son <= n - 1) {
            if (son < n - 1 && nums[son] > nums[son + 1]) son++;
            if (nums[i] > nums[son]) {
                int temp = nums[i];
                nums[i] = nums[son];
                nums[son] = temp;
                i = son;
                son = 2 * i + 1;
            }else{
                break;
            }
        }
    }



}
