package compile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 策略管理器，包含编译生成策略、获取已生成策略、清空缓存
 *
 */
public class StrategyManager extends ClassLoader {
    //由IDE构建
    private static boolean buildByIDE = true;
    //类路径
    private String classPath;
    //动态加载器
    private static StrategyManager loader;
    //临时文件集合
    private static List<File> fileList;

    static {
        File file = new File(".");
        //ide path
        String path;
        if (buildByIDE) {
            path = file.getAbsolutePath().replace(".", "") + "bin" + File.separator;
        } else {
            path = file.getAbsolutePath().replace(".", "\\");
        }
        loader = new StrategyManager(path);
        fileList = new ArrayList<>();
    }

    @Override
    protected Class<?> findClass(String arg0) throws ClassNotFoundException {

        byte[] data = getData(arg0);
        if (data == null) {

            throw new ClassNotFoundException();
        }
        return defineClass(arg0, data, 0, data.length);
    }

    private byte[] getData(String className) {

        String path = classPath + File.separatorChar + className.replace(".", File.separator) + ".class";

        InputStream is = null;
        try {
            is = new FileInputStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = is.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
            if (is != null)
                is.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private StrategyManager(String classPath) {
        super();
        this.classPath = classPath;
    }


    /**
     * 根据策略名和原生策略进行编译
     * @param strategyName 策略名
     * @param code 原生策略代码
     * @return 是否创建成功
     */
    public static boolean createStrategy(String strategyName, String code) {
        try {
            code = Convert.convert(strategyName, code);
            //当前路径下新建一个翻译后的java文件
            File file = new File(".\\" + strategyName + ".java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(code);
            fileWriter.flush();
            fileWriter.close();
            fileList.add(file);

            Runtime runtime = Runtime.getRuntime();
            Process p;
            if (buildByIDE) {
                p = runtime.exec("javac -d bin\\ -encoding utf-8 -classpath bin\\ " + strategyName + ".java");
            } else {
                p = runtime.exec("javac -d . -encoding utf-8 -classpath . " + strategyName + ".java");
            }
            if (p.waitFor() == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据策略名和原生策略进行编译
     * @param strategyName 策略名
     * @param codeFile 原生策略代码文件
     * @return 是否创建成功
     */
    public static boolean createStrategy(String strategyName, File codeFile) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (in.ready()) {
                sb.append(in.readLine()).append('\n');
            }
            return createStrategy(strategyName, sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param strategyName 策略名
     * @return 返回已经编译的策略，该策略继承Strategy接口
     * @throws Exception
     */
    public static Strategy getStrategy(String strategyName) throws Exception {
        Class clazz = loader.findClass("compile." + strategyName);
        Strategy strategy = (Strategy) clazz.newInstance();
        return strategy;
    }

    /**
     * 清除缓存
     */
    public static void clearTemp(){
        for (File file : fileList) {
            file.delete();
        }
    }
}
