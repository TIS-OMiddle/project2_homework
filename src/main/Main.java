package main;

import compile.StrategyManager;
import fileoperator.FileOperator;

import java.util.ArrayList;
import java.util.Scanner;

import competition.Competition;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0) StrategyManager.setBuildByIDE(false);
        //接口使用示例代码
        try {
            Scanner cin = new Scanner(System.in);
            System.out.println("你要输入多少个策略？");
            //strategynum为用户想要输入的策略数
            int strategynum = cin.nextInt();
            System.out.println("你要让他们比多少回合");
            //n为用户想要他们进行比赛的回合
            int n = cin.nextInt();
            FileOperator fileOperator = new FileOperator();
            //将输入的策略保存到文件里面
            fileOperator.inputStrategy(strategynum);
            //比特大战10回合,数组从0计算，因此大小应该为n+1

            //比赛获取排名，map对应第几个策略排名第几
            int i = 1;
            ArrayList<Competition.StrategyGoal> list = new Competition().getCompetitionResult(fileOperator, strategynum, n);
            for (Competition.StrategyGoal item : list) {
                System.out.println(item.name + " 排名为: " + i + " 总得分: " + item.goal);
                i++;
            }
            //清空临时文件
            StrategyManager.clearTemp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
}
