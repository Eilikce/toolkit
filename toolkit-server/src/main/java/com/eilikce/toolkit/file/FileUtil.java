package com.eilikce.toolkit.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private static final String BASE_DATA_PATH  = "."+File.separator+"data"+File.separator;

    public static List<String> fileList(String path) {

        createDirectory("");

        path = filePath(path);

        LOG.info("读取文件列表" + path);
        return Arrays.asList(new File(path).list());
    }

    public static String readFile(String path) {

        createDirectory("");

        path = filePath(path);

        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            LOG.error("读取文件失败", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error("读取文件失败", e);
                }
            }
        }
        return laststr;
    }


    /**
     * 保存文件
     *
     * @param fileName 文件名
     */
    public static void saveData(String fileName, String data) {

        createDirectory("");

        //Get the file reference
        Path path = Paths.get(filePath(fileName));
        LOG.info("准备写入文件: " + path);
        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(data);
            LOG.info("写入文件完成");
        } catch (IOException e) {
            LOG.error("写入文件失败", e);
        }
    }

    public static void createDirectory(String path) {
        path = filePath(path);
        //文件目录
        Path rootLocation = Paths.get(path);
        if(Files.notExists(rootLocation)){
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                LOG.info("文件夹创建失败", e);
            }
        }
    }

    private static String filePath(String path) {
        return BASE_DATA_PATH + path;
    }

}