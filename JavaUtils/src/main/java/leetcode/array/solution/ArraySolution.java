package leetcode.array.solution;

/**
 * 数组
 *
 * @author mltang
 * @version 2023/3/19 9:31
 * @since JDK8
 */
public class ArraySolution {
    /**
     * @Description 704：二分查找
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target，写一个函数搜索nums中的 target，
     * 如果目标值存在返回下标，否则返回 -1。
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 定义target在左闭右闭的区间里，[left, right]
        while (left <= right) { // 当left==right，区间[left, right]依然有效，所以用 <=
            int middle = left + ((right - left) / 2);// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target) {
                right = middle - 1; // target 在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，所以[middle + 1, right]
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值，直接返回下标
            }
        }
        return -1;
    }

    /**
     * @Description 27：移除元素（快慢指针）
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5,
     * 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     */
    public int removeElement(int[] nums, int val) {
        // int size = nums.length;
        // for(int i = 0; i < size; i++){
        //     if (nums[i] == val) { // 发现需要移除的元素，就将数组集体向前移动一位
        //        for (int j = i + 1; j < size; j++) {
        //            nums[j - 1] = nums[j];
        //       }
        //       i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
        //       size--; // 此时数组的大小-1
        //   }
        // return size;
        int slowindex = 0;
        for(int fastindex = 0; fastindex < nums.length; fastindex++){
            if(val != nums[fastindex]){
                nums[slowindex] = nums[fastindex];
                slowindex++;
            }
        }
        return slowindex;
    }

}

