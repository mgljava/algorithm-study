package com.github.mgljava.nowcoder;

/**
 * 剪绳子 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n）， 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * <p>
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 假设f(n)代表切割n所能获得的最大价值，那么f(n)=max{f(i)+f(n-i)}
 *
 *  f(n)=max{prices[n-1], f(1)+f(n-1), f(2)+f(n-2), ..., f(i)+f(n-i), ...}
 */
public class CutRope {

  public static void main(String[] args) {
    CutRope cutRope = new CutRope();
    System.out.println(cutRope.cutRope1(16));
    System.out.println(cutRope.cutRope2(16));
  }

  // 动态规划
  public int cutRope1(int target) {
    if (target == 1) {
      return 0;
    }
    if (target == 2) {
      return 1;
    }
    if (target == 3) {
      return 2;
    }
    int[] mul = new int[target + 1];
    mul[0] = 0;
    mul[1] = 1;
    mul[2] = 2;
    mul[3] = 3;
    for (int i = 4; i <= target; i++) {
      int max = 0;
      for (int j = 1; j <= i / 2; j++) {
        int temp = mul[j] * mul[i - j];
        if (max < temp) {
          max = temp;
        }
      }
      mul[i] = max;
    }
    return mul[target];
  }

  //贪心算法
  public int cutRope2(int n){
    //异常处理
    if(n < 0)
      throw new IllegalArgumentException("Illegal Paramters");

    if(n < 2)
      return 0;
    if(n == 2)
      return 1;
    if(n == 3)
      return 2;

    int timesOf3 = n/3;

    //如果剩余1，那么将1和一个3组成4可得到最大乘积
    if(n - timesOf3*3 == 1)
      timesOf3 -= 1;

    int timesOf2 = (n - timesOf3*3) / 2;

    return (int)Math.pow(3, timesOf3)*(int)Math.pow(2, timesOf2);
  }
}
