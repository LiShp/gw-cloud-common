package com.gw.cloud.common.core.util;

import com.gw.cloud.common.core.base.exception.ApplicationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author WRQ
 * @date 2019/3/20
 * @since 1.0.0
 */
public class FileUtil {

    /**
     * 文件存放的根路径
     */
    private static final String FILE_PATH_ROOT = System.getProperty("java.io.tmpdir") + "/gw/fileCache/";

    /**
     * 将上传的文件写入缓存文件并保存
     *
     * @param filePath 文件路径
     * @param file     上传文件
     * @return 缓存文件
     * @throws IOException IO异常
     */
    public static File saveFile(String filePath, MultipartFile file) throws IOException {
        // 得到文件对象
        File fileCache = new File(FILE_PATH_ROOT + filePath + StringUtil.STR_FILE_SEPARATOR + file.getOriginalFilename());
        // 判断文件所在的目录是否存在，若不存在则创建
        if (!fileCache.getParentFile().exists()) {
            fileCache.getParentFile().mkdirs();
        }
        // 写文件
        file.transferTo(fileCache);
        return fileCache;
    }

    /**
     * 将指定内容写入缓存文件并保存
     *
     * @param filePath 文件路径
     * @param text     文件内容
     * @throws IOException IO异常
     */
    public static void saveFile(String filePath, String text) throws IOException {
        // 得到文件对象
        File file = new File(FILE_PATH_ROOT + filePath);
        // 判断文件所在的目录是否存在，若不存在则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 写文件
        OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bufferedWriter = new BufferedWriter(writerStream);
        bufferedWriter.write(text);
        bufferedWriter.close();
        writerStream.close();
    }

    /**
     * 压缩文件夹至缓存目录
     *
     * @param out      输出流
     * @param fileName 文件或文件夹名
     */
    public static void compressFileToZip(OutputStream out, String fileName) {
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(FILE_PATH_ROOT + fileName);
            compress(zos, sourceFile, sourceFile.getName());
        } catch (Exception e) {
            throw new ApplicationException("Failed to compress folder to zip.");
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除缓存文件
     *
     * @param fileName 文件或文件夹
     */
    public static void delete(String fileName) {
        File sourceFile = new File(FILE_PATH_ROOT + fileName);
        if (sourceFile.isFile()) {
            sourceFile.delete();
        } else {
            File[] listFiles = sourceFile.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                listFiles[i].delete();
            }
            sourceFile.delete();
        }
    }

    private static void compress(ZipOutputStream zos, File sourceFile, String fileName) throws Exception {
        byte[] buf = new byte[1024];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(fileName));
            // copy文件到zip输出流中
            FileInputStream in = new FileInputStream(sourceFile);
            int len;
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 空文件夹的处理
                zos.putNextEntry(new ZipEntry(fileName + StringUtil.STR_SLASH));
                zos.closeEntry();
            } else {
                for (File file : listFiles) {
                    // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                    // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                    compress(zos, file, fileName + StringUtil.STR_SLASH + file.getName());
                }
            }
        }
    }



}
