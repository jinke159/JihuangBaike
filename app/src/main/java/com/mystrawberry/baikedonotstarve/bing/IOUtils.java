package com.mystrawberry.baikedonotstarve.bing;

import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 输入输出流工具类
 * Created by jk on 2017/10/19.
 */

public class IOUtils {
    @Nullable
    public static String inputStream2String(InputStream inputStream) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        String json = null;
        try {
            int readLength;
            while ((readLength = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, readLength);
            }
            json = result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream, result);
        }
        return json;
    }

    private static void close(InputStream inputStream, OutputStream result) {
        close(result);
        close(inputStream);


    }

    private static void close(OutputStream result) {
        try {

            if (result != null)
                result.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(InputStream inputStream) {
        try {
            if (inputStream != null)
                inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
