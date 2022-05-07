package com.spring.test.algorithm.niuke.二分查找;

/**
 * 比较版本号
 * 客项目发布项目版本时会有版本号，比如1.02.11，2.14.4等等
 * 现在给你2个版本号version1和version2，请你比较他们的大小
 * 版本号是由修订号组成，修订号与修订号之间由一个"."连接。1个修订号可能有多位数字组成，修订号可能包含前导0，且是合法的。例如，1.02.11，0.1，0.2都是合法的版本号
 * 每个版本号至少包含1个修订号。
 * 修订号从左到右编号，下标从0开始，最左边的修订号下标为0，下一个修订号下标为1，以此类推。
 *
 *  比较规则：
 * 一. 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较忽略任何前导零后的整数值。比如"0.1"和"0.01"的版本号是相等的
 * 二. 如果版本号没有指定某个下标处的修订号，则该修订号视为0。例如，"1.1"的版本号小于"1.1.1"。因为"1.1"的版本号相当于"1.1.0"，第3位修订号的下标为0，小于1
 * 三.  version1 > version2 返回1，如果 version1 < version2 返回-1，不然返回0.
 *
 * @author hlshi3
 * @date 2022/5/1 7:32
 */
public class BM22 {


    public int compare (String version1, String version2) {
        // write code here
        int n1 =version1.length();
        int n2 = version2.length();
        int i=0;
        int j=0;
        while(i<n1 || j<n2){
            //计算每个.之前的数值并比较大小
            long num1 =0;
            while(i<n1 && version1.charAt(i)!='.'){
                // 0000001.00001.1001
                num1 = num1 * 10 + (version1.charAt(i)-'0');
                i++;
            }
            i++; //.

            long num2 =0;
            while(j<n1 && version1.charAt(j)!='.'){
                // 0000001.00001.1001
                num2 = num2 * 10 + (version2.charAt(j)-'0');
                j++;
            }
            j++;
            //比较num1 和 num2 的大小
            return num1 > num2 ? 1 : -1;

        }
        return 0;
    }


    public static void main(String[] args) {

       String str = "12345";
        System.out.println((int)str.charAt(0));
        System.out.println((int)'0');
        System.out.println((int)'1');
        System.out.println((int)'2');
    }
}
