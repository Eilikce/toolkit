package com.eilikce.toolkit.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    public static String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
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
        return laststr;
    }


    /**
     * 保存文件
     * @param fileName 文件名
     */
    public static void saveData(String fileName, String data) {

        //Get the file reference
        Path path = Paths.get(fileName);

        Files.exists(path);

        LOG.info("准备写入文件: " + path);
        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.write(data);
            LOG.info("写入文件完成");
        } catch (IOException e) {
            LOG.error("写入文件失败",e);
        }

    }

}