package fileoperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import compile.Strategy;
import compile.StrategyManager;
import grammar.GrammarManager;

public class FileOperator {
	private String filepath="data";
	//n为用户想要输入的策略数
	public Boolean inputstrategy(int n) {
		
		String string="";
		int i=1;
		Scanner cin=new Scanner(System.in);
		while(n-- !=0) {
			string="";
			System.out.println("请输入第"+i+"个策略,新起一行end结束输入");
			String string2="";
			while(cin.hasNextLine()) {
				string2=cin.nextLine();
				if (string2==null) {
					continue;
				}
				if (string2.equals("end")) {
					break;
				}
				string=string+string2+"\r\n";
			}
			//cin.close();
			//System.out.print(string);
			strategyoutput(string, i++);
		}
		cin.close();
		return true;
	}
	//保存策略到名为"datai.txt"的文件中
	public Boolean strategyoutput(String string,int i) {
		// 1：利用File类找到要操作的对象
        File file = new File(filepath+i+".txt");
       /* if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }*/
     
        try {
        	  //2：准备输出流
            Writer out = new FileWriter(file);
			out.write(string);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
        
	}
	

public ArrayList<Strategy> getstrategyfromfile(int n) {

		// 使用ArrayList来存储每一个策略
	ArrayList<Strategy> strategies=new ArrayList<>();
for(int i=1;i<=n;i++) {
	//读取原生策略代码，即当前目录下的data.txt
    File file = new File("./data"+i+".txt");

    //语法分析和检测,出错时会抛出异常和error
    try {
		GrammarManager.analyze(file);
	    //创建并编译策略
	    StrategyManager.createStrategy("Strategy"+i, file);
	    //获取已经创建的策略，可用方法请看策略接口
	    Strategy strategy = StrategyManager.getStrategy("Strategy"+i);
	    strategies.add(strategy);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Error e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
		
		return strategies;

	}

	
}
