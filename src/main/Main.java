package main;

import compile.Strategy;
import compile.StrategyManager;
import fileoperator.FileOperator;
import grammar.GrammarManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Competition.Competition;

public class Main {
    public static void main(String[] args) {
        //接口使用示例代码
        try {
        	Scanner cin=new Scanner(System.in);
        	System.out.println("你要输入多少个策略？");
        	//strategynum为用户想要输入的策略数
        	int strategynum=cin.nextInt();
        	System.out.println("你要让他们比多少回合");
        	//n为用户想要他们进行比赛的回合
        	int n=cin.nextInt();
        	FileOperator fileOperator=new FileOperator();
        	//将输入的策略保存到文件里面
        	fileOperator.inputstrategy(strategynum);
            //比特大战10回合,数组从0计算，因此大小应该为n+1
           /* int n = 10;
            //数组A储存策略1的历史选择，数组B储存策略2的历史选择
            int[] A = new int[n + 1], B = new int[n + 1];

            
            //读取原生策略代码，即当前目录下的data.txt
            File file1 = new File("./data1.txt");
            File file2 = new File("./data2.txt");

            //语法分析和检测,出错时会抛出异常和error
            GrammarManager.analyze(file1);
            GrammarManager.analyze(file2);

            //创建并编译策略
            StrategyManager.createStrategy("Strategy1", file1);
            StrategyManager.createStrategy("Strategy2", file2);
            //获取已经创建的策略，可用方法请看策略接口
            Strategy strategy1 = StrategyManager.getStrategy("Strategy1");
            Strategy strategy2 = StrategyManager.getStrategy("Strategy2");*/
//比赛获取排名，map对应第几个策略排名第几
        	 HashMap<Integer, Integer> map=new Competition().getcompetitionresult(strategynum, n);
            for(int i=1;i<=strategynum;i++) {
            	System.out.println("策略"+i+"  排名"+map.get(i));
            }
            //每个策略的得分
          /*  int[] goals=new int[strategynum];
            //两个策略对战的选择
            for(int i=0;i<strategynum;i++) {
            	for(int j=i+1;j<strategynum;j++) {
            		for (int k = 1; k <= n; k++) {
                        A[k] = strategies.get(i).getValue(k, A, B);//互为对手，因此参数A，B位置反转
                        B[k] = strategies.get(j).getValue(k, B, A);
                        goals[i]+=A[k];
                        goals[j]+=B[k];
                    }
            	}
            }
            for (int i = 1; i <= n; i++) {
                A[i] = strategy1.getValue(i, A, B);//互为对手，因此参数A，B位置反转
                B[i] = strategy2.getValue(i, B, A);
            }
*/
            //输出选择
           /* for (int i = 1; i <= n; i++) {
                System.out.println(A[i] + ":" + B[i]);
            }*/
            //清空临时文件
            StrategyManager.clearTemp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
}
