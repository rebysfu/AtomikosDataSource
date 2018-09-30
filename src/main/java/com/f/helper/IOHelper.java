package com.f.helper;

import java.io.*;

/**
 * User: bvsoo
 * Date: 2018/6/28
 * Time: 上午11:44
 */
public final class IOHelper {
    /**
     * @param inputStream
     */
    public static void close(final InputStream inputStream) {
        if (inputStream == null) return;
        try {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param outputStream
     */
    public static void close(final OutputStream outputStream) {
        if (outputStream == null) return;
        try {
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param writer
     */
    public static void close(final Writer writer) {
        if (writer == null) return;
        try {
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param closeable
     */
    public static void close(final Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {

        }
    }

    /**
     * 输入流转字符串
     *
     * @param inputStream
     * @return
     */
    public static String string(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = inputStream.read(b)) != -1; ) {
            stringBuffer.append(new String(b, 0, n));
        }
        return stringBuffer.toString();
    }
}
