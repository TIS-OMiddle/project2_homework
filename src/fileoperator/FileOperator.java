package fileoperator;

import compile.Strategy;
import compile.StrategyManager;
import grammar.GrammarManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 廖南山
 */
public class FileOperator {
    private String filepath = "Strategy";

    private ArrayList<String> temp = new ArrayList<>();

    //n为用户想要输入的策略数
    public Boolean inputStrategy(int n) {

        String string = "";
        int i = 1;
        Scanner cin = new Scanner(System.in);
        while (n-- != 0) {
            string = "";
            System.out.println("请输入第" + i + "个策略,新起一行end结束输入");
            String string2 = "";
            while (cin.hasNextLine()) {
                string2 = cin.nextLine();
                if (string2 == null) {
                    continue;
                }
                if (string2.equals("end")) {
                    break;
                }
                string = string + string2 + "\r\n";
            }
            //cin.close();
            //System.out.print(string);
            strategyOutput(string, i++);
            temp.add(string);
        }
        cin.close();
        return true;
    }

    //保存策略到名为filepath.txt的文件中
    public Boolean strategyOutput(String string, int i) {
        // 1：利用File类找到要操作的对象
        File file = new File(filepath + i + ".txt");
        try {
            //2：准备输出流
            Writer out = new FileWriter(file);
            out.write(string);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Strategy> getStrategy(int n) {

        // 使用ArrayList来存储每一个策略
        ArrayList<Strategy> strategies = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            //读取原生策略代码，即当前目录下的data.txt
            String code = temp.get(i - 1);
            //语法分析和检测,出错时会抛出异常和error
            try {
                GrammarManager.analyze(code);
                //创建并编译策略
                StrategyManager.createStrategy("Strategy" + i, code);
                //获取已经创建的策略，可用方法请看策略接口
                Strategy strategy = StrategyManager.getStrategy("Strategy" + i);
                strategies.add(strategy);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } catch (Error e) {
                System.out.println(e.getMessage());
            }
        }
        return strategies;
    }
}
