package com.jiu907.api.utils.ioutil;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Author LeiLiMin
 * @Description:
 * @date: 2022/1/22
 */
public class FileStream {
    public static String readData(String filePath){
        File file = new File(filePath);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileContentStr = new String(fileContent);
        return fileContentStr;
    }
}
