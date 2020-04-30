package com.win.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageParserUtils {

    private static VelocityEngine ve;
    private static boolean isReplace = true;
    private static final Logger logger = LoggerFactory.getLogger(PageParserUtils.class);

    static {
        try {
            String templateBasePath = CommonsUtils.getProjectPath() + "/src/main/java/com/win/template/";
            Properties properties = new Properties();
            properties.setProperty("file.resource.loader.path", templateBasePath);
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init(properties);
            ve = velocityEngine;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public static void WriterPage(VelocityContext context, String templateName, String targetFile, String fileName,
            String sourceFileName) {

        File file;
        try {
            file = new File(targetFile + fileName);
            if (!(file.exists())) {
                new File(file.getParent()).mkdirs();
            } else if (isReplace) {
                System.out.println("替换文件:" + file.getAbsolutePath());
            }

            Template template = ve.getTemplate(templateName, "UTF-8");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
            fos.close();
            InputStream in = new FileInputStream(file);

            OutputStream out = new FileOutputStream("D:/5678");
            byte[] bytes = toByteArray(in);
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();
            System.out.println("生成文件:" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
}
