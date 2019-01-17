package com.geostar.gfstack.docker.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * @author 王睿
 * @date 2019-01-17 10:54
 */
public class LogsFormat {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("请输入第一个参数：日志文件路径");
            return;
        }
        String fileName = args[0];
        InputStream is = LogsFormat.class.getResourceAsStream("/templates/DockerLogsTemplate.html");
        String html = IOUtils.toString(is, "UTF-8");
        File file = new File(fileName);
        String text = IOUtils.toString(new FileInputStream(file), "UTF-8");
        String[] arr = text.split("\n");
        JsonParser parser = new JsonParser();
        String time, log, stream;
        StringBuilder sb = new StringBuilder(1024 * 10);
        for (int i = 0; i < arr.length; i++) {
            try {
                JsonObject jsonObject = parser.parse(arr[i]).getAsJsonObject();
                time = jsonObject.get("time").getAsString();
                log = jsonObject.get("log").getAsString();
                stream = jsonObject.get("stream").getAsString();
                sb.append("<tr");
                if ("stderr".equals(stream)) {
                    sb.append(" class='stderr'");
                }
                sb.append("><td>");
                sb.append(i + 1);
                sb.append("</td><td>");
                sb.append(time);
                sb.append("</td><td>");
                sb.append(log);
                sb.append("</td></tr>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        html = html.replace("<![data]>", sb.toString());
        File outFile = new File(file.getPath() + ".html");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
        out.write(html);
        out.close();
        System.out.println("Docker日志格式化成功，请查看：" + outFile.getAbsoluteFile());
    }
}
