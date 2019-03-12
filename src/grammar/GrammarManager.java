package grammar;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

/**
 *
 */
public class GrammarManager {
    private static Lexer lexer;
    private static parser p;

    static {
        lexer = new Lexer(null);
        p = new parser(lexer);
    }

    /**
     * @param codeFile 原生策略代码文件
     * @throws Exception 语法有误时抛出异常
     * @throws Error 语法有误时抛出抛出错误
     */
    public static void analyze(File codeFile) throws Exception, Error {
        lexer.yyreset(new FileReader(codeFile));
        p.parse();
        lexer.yyclose();
    }

    /**
     * @param code 原生策略代码字符串
     * @throws Exception 语法有误时抛出异常
     * @throws Error 语法有误时抛出抛出错误
     */
    public static void analyze(String code) throws Exception, Error {
        lexer.yyreset(new StringReader(code));
        p.parse();
        lexer.yyclose();
    }
}
