package cn.edu.ahut.utils;

import java.io.File;

/**
 * Created by zhushuangfei on 2018/2/1
 */
public class DeleteFile {

    public static void delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delete(files[i].toString());
                }
                file.delete();
            }
        }
    }
}
