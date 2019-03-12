package Competition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import compile.Strategy;
import fileoperator.FileOperator;

public class Competition {

	//strategynum为策略数，n为比赛回合
	public HashMap<Integer, Integer> getcompetitionresult(int strategynum,int n){
		 ArrayList<Strategy> strategies=new FileOperator().getstrategyfromfile(strategynum);
		 //数组A储存策略1的历史选择，数组B储存策略2的历史选择
         int[] A = new int[n + 1], B = new int[n + 1];
		 //每个策略的得分
         int[] goals=new int[strategynum];
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
         
         LinkedList<Integer> goalslist = new LinkedList<>();
 		for(int i=0;i<n;i++) {
 			goalslist.add(goals[i]);
 		}
 		goalslist.sort(new Comparator<Integer>() {

 			@Override
 			public int compare(Integer o1, Integer o2) {
 				// TODO Auto-generated method stub
 				if (o1<o2) {
 					return 1;
 				}
 				return -1;
 			}
 		});
 		HashMap<Integer, Integer> map=new HashMap<>();
 		for(int i=0;i<goalslist.size();i++) {
 			for(int j=0;j<strategynum;j++) {
 				if (goalslist.get(i)==goals[j]) {
					map.put(j+1, i+1);
					break;
				}
 			}
 		}
 		return map;
 		
	}
}
