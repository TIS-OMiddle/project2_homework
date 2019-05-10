package competition;

import compile.Strategy;
import fileoperator.FileOperator;

import java.util.ArrayList;
import java.util.Collections;

public class Competition {
    public class StrategyGoal implements Comparable<StrategyGoal> {
        public int goal;
        public String name;

        public StrategyGoal(String name, int goal) {
            this.goal = goal;
            this.name = name;
        }

        @Override
        public int compareTo(StrategyGoal o) {
            return o.goal - goal;
        }
    }

    //由选择计算得分
    private int getGoal(int self, int other) {
        if (self == 0 && other == 0) return 1;//互相背叛
        else if (self == 1 && other == 1) return 3;//互相合作
        else if (self == 1 && other == 0) return 0;//被背叛
        else return 5;//背叛他人
    }

    //strategynum为策略数，n为比赛回合
    public ArrayList<StrategyGoal> getCompetitionResult(FileOperator fileOperator, int strategynum, int n) {
        ArrayList<Strategy> strategies = fileOperator.getStrategy(strategynum);
        //数组A储存策略1的历史选择，数组B储存策略2的历史选择
        int[] A = new int[n + 1], B = new int[n + 1];
        //每个策略的得分
        int[] goals = new int[strategynum];
        //两个策略对战的选择
        for (int i = 0; i < strategynum; i++) {
            for (int j = i + 1; j < strategynum; j++) {
                for (int k = 1; k <= n; k++) {
                    A[k] = strategies.get(i).getValue(k, A, B);//互为对手，因此参数A，B位置反转
                    B[k] = strategies.get(j).getValue(k, B, A);
                    goals[i] += getGoal(A[k], B[k]);
                    goals[j] += getGoal(B[k], A[k]);
                }
            }
        }

        ArrayList<StrategyGoal> goalslist = new ArrayList<>();
        for (int i = 1; i <= strategynum; i++) {
            goalslist.add(new StrategyGoal("策略" + i, goals[i - 1]));
        }
        Collections.sort(goalslist);
        return goalslist;
    }
}
