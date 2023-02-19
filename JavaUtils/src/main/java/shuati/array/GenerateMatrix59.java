package shuati.array;

import java.util.Arrays;

/**
 * 螺旋矩阵
 *
 * @author mltang
 * @version 2023/2/19 9:28
 * @since JDK8
 */
public class GenerateMatrix59 {
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int loop = n / 2; //循环几次
        int mid = n / 2; //矩阵中间的位置
        int startx = 0, starty = 0; //矩阵起始位置
        int count = 1; //给矩阵赋值
        int offset = 1; //控制每条边遍历的长度，遍历一次右边界收缩一位
        int i,j;
        while(loop-- > 0){
            i = startx;
            j = starty;

            //遵循左闭右开
            for(; j < n - offset; j++){
                result[i][j] = count++;
            }
            for(; i < n - offset; i++){
                result[i][j] = count++;
            }
            for(; j > starty; j--){
                result[i][j] = count++;
            }
            for(; i > starty; i--){
                result[i][j] = count++;
            }
            startx++;
            starty++;

            offset += 1;
        }
        if(n % 2 == 1){
            result[mid][mid] = count;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] res = generateMatrix(4);
        System.out.println(res);
        System.out.println(Arrays.stream(generateMatrix(4)).toArray());
    }
}

