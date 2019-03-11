package main;

import compile.Strategy;
import compile.StrategyManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //接口使用示例代码
        try {
            //比特大战10回合,数组从0计算，因此大小应该为n+1
            int n = 10;
            //数组A储存策略1的历史选择，数组B储存策略2的历史选择
            int[] A = new int[n + 1], B = new int[n + 1];

            //读取原生策略代码，即当前目录下的data.txt
            File file1 = new File("./data1.txt");
            File file2 = new File("./data2.txt");

            //创建并编译策略
            StrategyManager.createStrategy("Strategy1", file1);
            StrategyManager.createStrategy("Strategy2", file2);
            //获取已经创建的策略，可用方法请看策略接口
            Strategy strategy1 = StrategyManager.getStrategy("Strategy1");
            Strategy strategy2 = StrategyManager.getStrategy("Strategy2");

            //两个策略对战的选择
            for (int i = 1; i <= n; i++) {
                A[i] = strategy1.getValue(i, A, B);//互为对手，因此参数A，B位置反转
                B[i] = strategy2.getValue(i, B, A);
            }

            for (int i = 1; i <= n; i++) {
                System.out.println(A[i] + ":" + B[i]);
            }
            //清空临时文件
            StrategyManager.clearTemp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
