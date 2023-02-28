package com.wokite.net.backendstore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Component
public class FileUtils {

    @Autowired
    private static ServletContext context;

    public static void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int byteRead = -1;
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
