package compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Convert {
    private static String template;

    static {
        try {
            template = "";
            File file = new File("./template.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while (in.ready()) {
                template += in.readLine() + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convert(String strategyName, String code) {
        return template.replace("{0}", strategyName).replace("{1}", code);
    }
}
