package leetcode.string.solution;

/**
 * 字符串
 *
 * @author mltang
 * @version 2023/3/18 23:08
 * @since JDK8
 */
public class StringSolution {
    /**
     * @Description 力扣344.反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     * 示例 1：
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     *
     * 示例 2：
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     */
    public static char[] reverseString(char[] c){
        int left = 0;
        int right = c.length - 1;
        while(left < right){
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return c;
    }

    /*
        对于字符串，我们定义两个指针（也可以说是索引下标），一个从字符串前面，一个从字符串后面，两个指针同时向中间移动，并交换元素。
     */

    /**
     * @Description 力扣541.反转字符串II
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起, 每计数至 2k 个字符，就反转这 2k 个字符中的前 k 个字符。
     *
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     *
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 示例:
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "bacd feg"
     *
     */
    public static String reverseStr(String s, int k){
        char[] ch =  s.toCharArray();
        for(int i = 0; i < ch.length - 1; i += 2 * k){
            int start = i;
            //如果剩余字符少于 k 个，则将剩余字符全部反转。如果k=8,少于k了取全部长度
            int end = Math.min(ch.length - 1, start + k - 1);
            while(start < end){
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;

                start++;
                end--;
            }
        }
        return new String(ch);
    }

    /**
     * @Description 剑指Offer 05.替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 示例 1： 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *
     */
    public static String replaceSpace(String s){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                str.append("%20");
            }else {
                str.append(s.charAt(i));
            }
        }
//        return str.toString();
//解法二：双指针
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                //两个空格
                str.append("  ");
            }
        }
        int left = s.length() - 1;
        s += str.toString();
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while(left >= 0){
            if(chars[left] == ' '){
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            }else{
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }

/*
时间复杂度：O(n) 空间复杂度：O(1)

如果想把这道题目做到极致，就不要只用额外的辅助空间了！
首先扩充数组到每个空格替换成"%20"之后的大小。
然后从后向前替换空格，也就是双指针法，过程如下：
i指向新长度的末尾，j指向旧长度的末尾。

有同学问了，为什么要从后向前填充，从前向后填充不行么？

从前向后填充就是O(n^2)的算法了，因为每次添加元素都要将添加元素之后的所有元素向后移动。
其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
这么做有两个好处：
    不用申请新数组。
    从后向前填充元素，避免了从前向后填充元素时，每次添加元素都要将添加元素之后的所有元素向后移动的问题。
*/

    /**
     * @Description 151. 翻转字符串里的单词
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     *
     * 示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     * 示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * 一些同学会使用split库函数，分隔单词，然后定义一个新的string字符串，最后再把单词倒序相加，那么这道题题目就是一道水题了，
     * 失去了它的意义。所以这里我还是提高一下本题的难度：不要使用辅助空间，空间复杂度要求为O(1)。
     */
    public static String replaceWords(String s){
        StringBuilder res =  removeSpace(s);
        reverseString(res, 0, res.length() - 1);
        reverseEachWord(res);
        return res.toString();
    }

    /**
     * 1、去除字符串中多余的空格 双指针
     * @param s
     * @return
     */
    private static StringBuilder removeSpace(String s){
        int left = 0;
        int right = s.length() - 1;
        while(s.charAt(left) == ' ') left++;
        while(s.charAt(right) == ' ') right--;
        StringBuilder sb = new StringBuilder();
        while(left <= right){
            //去除多余的空格 永远判断当前要加的字符和sb的最后一个字符是否有一个不是空格
            if(s.charAt(left) != ' ' || sb.charAt(sb.length() - 1) != ' '){
                sb.append(s.charAt(left));
            }
            left++;
        }
        return sb;
    }

    /**
     * 2、反转指定范围的字符串
     * @param sb
     * @param start
     * @param end
     */
    private static void reverseString(StringBuilder sb, int start, int end){
        while(start < end){
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    /**
     * 3、反转每个单词
     * 双指针：起始位置从0，终止位置从1开始，然后循环找到为空格的字符即可调用第二步反转字符串的方法
     *       更新start和end，start就是 end + 1, end就是现在的start+1,以此循环到 start>n结束
     * @param sb
     */
    private static void reverseEachWord(StringBuilder sb){
        int start = 0;
        int end = 1;
        int n = sb.length();
        while(start < n){
            while(end < n && sb.charAt(end) != ' '){
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }

    /**
     * @Description 剑指Offer58-II.左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     *
     * 示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     *
     * 限制：
     * 1 <= k < s.length <= 10000
     *
     * 一些同学热衷于使用substr，来做这道题。 其实使用substr 和 反转 时间复杂度是一样的 ，都是O(n)，
     * 但是使用substr申请了额外空间，所以空间复杂度是O(n)，而反转方法的空间复杂度是O(1)。
     *
     * 如果想让这套题目有意义，就不要申请额外空间。
     */
    public static String reverseLeftWords(String s, int k){
        char[] chars1 = reverseStringIndex(s.toCharArray(), 0, k - 1); //bacdefg
        char[] chars2 = reverseStringIndex(chars1, k, s.length() - 1); //bagfedc
        char[] chars = reverseStringIndex(chars2, 0, s.length() - 1); //cdefgab
        return new String(chars);
    }
    private static char[] reverseStringIndex(char[] c, int left, int right){
        while(left < right){
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return c;
    }

    /**
     * @Desciption 28. TODO 实现 strStr()
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     * 示例 1: 输入: haystack = "hello", needle = "ll" 输出: 2
     * 示例 2: 输入: haystack = "aaaaa", needle = "bba" 输出: -1
     * 说明: 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符
     */

    /**
     * @Desciption 459.TODO 重复的子字符串
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * 解释: 可由子字符串 "ab" 重复两次构成。
     *
     * 示例 2:
     * 输入: "aba"
     * 输出: False
     *
     * 示例 3:
     * 输入: "abcabcabcabc"
     * 输出: True
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成 )
     */

    public static void main(String[] args) {
        System.out.println(reverseLeftWords("abcdefg", 2));
    }
}

