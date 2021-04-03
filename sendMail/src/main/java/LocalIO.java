import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @AuthorBy 雁不过衡阳.
 * @Content 雁阵惊寒，声断衡阳之浦
 * @Date Created in 2021/3/12 10:38
 */
public class LocalIO {

    /**
     * 获取本地txt文件内容
     * @param filePath
     *      String : filePath : txt文件路径,例如 C:\\1.txt
     * @return
     *      请求的结果List<String>
     */
    public static List<String> getLocalTxt(String filePath) {
        List<String> results=new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        String tempString = null;
        int line = 1;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            while ((tempString = reader.readLine()) != null) {
                System.out.println("Line" + line + ":" + tempString);
                results.add(tempString.trim());
                line++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}
