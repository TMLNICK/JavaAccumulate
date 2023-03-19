package leetcode.hashtable.solution;

import java.util.*;

/**
 * 哈希表
 *
 * @author mltang
 * @version 2023/3/18 13:24
 * @since JDK8
 */
public class HashTableSolution {
    /**
     * @Description 力扣242.有效的字母异位词
     *
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 示例 1: 输入: s = "anagram", t = "nagaram" 输出: true
     * 示例 2: 输入: s = "rat", t = "car" 输出: false
     * 说明: 你可以假设字符串只包含小写字母。
     */
    public static boolean isAnagram(String s, String t){
        int[] arr = new int[26];
        for(char i : s.toCharArray()){
            arr[i - 'a'] += 1;
        }
        for(char i : t.toCharArray()){
            arr[i - 'a'] -= 1;
        }
        for (int count : arr) {
            if(count != 0){
                return false;
            }
        }
        return true;
    }
    /*
        解析： 时间复杂度为O(n)，空间上因为定义是的一个常量大小的辅助数组，所以空间复杂度为O(1)。
        定义一个数组叫做record用来上记录字符串s里字符出现的次数。
        需要把字符映射到数组也就是哈希表的索引下标上，因为字符a到字符z的ASCII是26个连续的数值，所以字符a映射为下标0，相应的字符z映射为下标25。
        再遍历 字符串s的时候，只需要将 s[i] - ‘a’ 所在的元素做+1 操作即可，并不需要记住字符a的ASCII，只要求出一个相对数值就可以了。 这样就将字符串s中字符出现的次数，统计出来了。
        那看一下如何检查字符串t中是否出现了这些字符，同样在遍历字符串t的时候，对t中出现的字符映射哈希表索引上的数值再做-1的操作。
        那么最后检查一下，record数组如果有的元素不为零0，说明字符串s和t一定是谁多了字符或者谁少了字符，return false。
        最后如果record数组所有元素都为零0，说明字符串s和t是字母异位词，return true。
    */

    /**
     * @Description 349. 两个数组的交集
     *
     * 题意：给定两个数组，编写一个函数来计算它们的交集。
     * 示例 1: 输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2]
     * 示例 2: 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [9,4]
     * 说明： 输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
     */
    public static int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if(set1.contains(i)){
                resSet.add(i);
            }
        }
        return resSet.stream().mapToInt(x -> x).toArray();
    }
    /*
        直接使用set 不仅占用空间比数组大，而且速度要比数组慢，set把数值映射到key上都要做hash计算的。
        不要小瞧 这个耗时，在数据量大的情况，差距是很明显的。
        本题后面 力扣改了 题目描述 和 后台测试数据，增添了 数值范围：
            1 <= nums1.length, nums2.length <= 1000
            0 <= nums1[i], nums2[i] <= 1000
        所以就可以 使用数组来做哈希表了， 因为数组都是 1000以内的。
    */

    /**
     * @Description 202. 快乐数
     *
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * 示例
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static boolean isHappy(int n){
        HashSet<Integer> set = new HashSet<>();
        while(n != 1){
            if(set.contains(n)){
                return false;
            }
            set.add(n);
            n = getNextNumber(n);
        }
        return true;
    }
    //各个位求和
    private static int getNextNumber(int n){
        int sum = 0;
        while(n > 0){
            int temp = n % 10;
            sum += temp * temp;
            n = n / 10;
        }
        return sum;
    }
    /*
        题目中说了会 无限循环，那么也就是说求和的过程中，sum会重复出现，这对解题很重要！
        当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
        所以这道题目使用哈希法，来判断这个sum是否重复出现，如果重复了就是return false， 否则一直找到sum为1为止。
     */

    /**
     * @Description 1. 两数之和
     *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    /*
        首先我在强调一下 什么时候使用哈希法，当我们需要查询一个元素是否出现过，或者一个元素是否在集合里的时候，就要第一时间想到哈希法。

        本题呢，我就需要一个集合来存放我们遍历过的元素，然后在遍历数组的时候去询问这个集合，某元素是否遍历过，也就是 是否出现在这个集合。

        那么我们就应该想到使用哈希法了。

        因为本地，我们不仅要知道元素有没有遍历过，还有知道这个元素对应的下标，需要使用 key value结构来存放，key来存元素，value来存下标，那么使用map正合适。

        再来看一下使用数组和set来做哈希法的局限。

        数组的大小是受限制的，而且如果元素很少，而哈希值太大会造成内存空间的浪费。
        set是一个集合，里面放的元素只能是一个key，而两数之和这道题目，不仅要判断y是否存在而且还要记录y的下标位置，因为要返回x 和 y的下标。所以set 也不能用。
        此时就要选择另一种数据结构：map ，map是一种key value的存储结构，可以用key保存数值，用value在保存数值所在的下标。

        接下来需要明确两点：
            map用来做什么
            map中key和value分别表示什么
        map目的用来存放我们访问过的元素，因为遍历数组的时候，需要记录我们之前遍历过哪些元素和对应的下标，这样才能找到与当前元素相匹配的（也就是相加等于target）

        接下来是map中key和value分别表示什么。

        这道题 我们需要 给出一个元素，判断这个元素是否出现过，如果出现过，返回这个元素的下标。
        那么判断元素是否出现，这个元素就要作为key，所以数组中的元素作为key，有key对应的就是value，value用来存下标。

        所以 map中的存储结构为 {key：数据元素，value：数组元素对应的下标}。
        在遍历数组的时候，只需要向map去查询是否有和目前遍历元素匹配的数值，如果有，就找到的匹配对，如果没有，就把目前遍历的元素放进map中，因为map存放的就是我们访问过的元素。

        # 本题其实有四个重点：

        为什么会想到用哈希表
        哈希表为什么用map
        本题map是用来存什么的
        map中的key和value用来存什么的
     */

    /**
     * @Description 454. 四数相加II
     *
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
     *
     * 示例:
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * 输出:
     * 2
     * 解释:
     * 两个元组如下:
     * (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int res  = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                if(map.containsKey(i + j)){
                    map.put(i + j, map.get(i + j) + 1);
                }else{
                    map.put(i + j, 1);
                }
            }
        }
        for(int i : nums3){
            for(int j : nums4){
                if(map.containsKey(-i - j)){
                    res+= map.get(-i - j);
                }
            }
        }
        return res;
    }
    /*
        本题咋眼一看好像和0015.三数之和 (opens new window)，0018.四数之和 (opens new window)差不多，其实差很多。

        本题是使用哈希法的经典题目，而0015.三数之和 (opens new window)，0018.四数之和 (opens new window)并不合适使用哈希法，
        因为三数之和和四数之和这两道题目使用哈希法在不超时的情况下做到对结果去重是很困难的，很有多细节需要处理。

        而这道题目是四个独立的数组，只要找到A[i] + B[j] + C[k] + D[l] = 0就可以，不用考虑有重复的四个元素相加等于0的情况，所以相对于题目18. 四数之和，题目15.三数之和，还是简单了不少！
        如果本题想难度升级：就是给出一个数组（而不是四个数组），在这里找出四个元素相加等于0，答案中不可以包含重复的四元组，大家可以思考一下，后续的文章我也会讲到的。
     */

    /**
     * @Description 383. 赎金信
     *
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
     * 如果可以构成，返回 true ；否则返回 false。
     *
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     *
     * 注意：
     * 你可以假设两个字符串均只含有小写字母。
     *
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     *
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for(char c : magazine.toCharArray()){
            record[c - 'a'] += 1;
        }

        for(char c : ransomNote.toCharArray()){
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for(int i : record){
            if(i < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * @Description 15. TODO 三数之和
     *
     *给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意： 答案中不可以包含重复的三元组。
     *
     * 示例：
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
     *
     */


    /**
     * @Description 18. TODO 四数之和
     * 题意：给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     * 示例： 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
     *
     */





    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}

