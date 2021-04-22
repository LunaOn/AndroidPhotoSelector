package com.luck.picture.lib.tools;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author：luck
 * @date：2019-11-08 19:25
 * @describe：Android Q相关处理类
 */
public class AndroidQTransformUtils {



    /**
     * 解析Android Q版本下图片
     * #耗时操作需要放在子线程中操作
     *
     * @param ctx
     * @param url
     * @param mineType
     * @param customFileName
     * @return
     */
    public static String copyPathToAndroidQ(Context ctx, String url, int width, int height, String mineType, String customFileName) {
        // 走普通的文件复制流程，拷贝至应用沙盒内来
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Uri uri = Uri.parse(url);
            String encryptionValue = StringUtils.getEncryptionValue(url, width, height);
            String newPath = PictureFileUtils.createFilePath(ctx, encryptionValue, mineType, customFileName);
            File outFile = new File(newPath);
            if (outFile.exists()) {
                return newPath;
            }
            inputStream = ctx.getContentResolver().openInputStream(uri);
            outputStream = new FileOutputStream(outFile);
            boolean transfer = IOUtils.transfer(inputStream, outputStream);

            if (transfer) {
                return newPath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(inputStream);
            IOUtils.close(outputStream);
        }
        return "";
    }

}
