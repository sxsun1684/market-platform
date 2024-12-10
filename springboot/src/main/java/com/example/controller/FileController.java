package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File API Controller
 * Provides endpoints for file upload, download, and deletion, as well as integration with WangEditor.
 */
@RestController
@RequestMapping ("/files")
public class FileController {

    // File upload storage path
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @Value ("${server.port:9090}")
    private String port;

    @Value ("${ip:localhost}")
    private String ip;

    /**
     * Upload a file to the server.
     *
     * @param file The file to be uploaded.
     * @return A success result containing the file's access URL.
     */
    @PostMapping ("/upload")
    public Result upload(MultipartFile file) {
        String flag;
        synchronized(FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            if (! FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            // File storage format: timestamp-filename
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--Upload successful");
        } catch(Exception e) {
            System.err.println(fileName + "--File upload failed");
        }
        String http = "http://" + ip + ":" + port + "/files/";
        return Result.success(http + flag + "-" + fileName);
    }

    /**
     * Download a file from the server.
     *
     * @param flag     The unique identifier of the file (timestamp-filename format).
     * @param response The HTTP response to write the file data to.
     */
    @GetMapping ("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;
        try {
            if (StrUtil.isNotEmpty(flag)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + flag);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch(Exception e) {
            System.out.println("File download failed");
        }
    }

    /**
     * Delete a file from the server.
     *
     * @param flag The unique identifier of the file (timestamp-filename format).
     */
    @DeleteMapping ("/{flag}")
    public void delFile(@PathVariable String flag) {
        FileUtil.del(filePath + flag);
        System.out.println("Deleted file " + flag + " successfully");
    }

    /**
     * Upload a file through WangEditor integration.
     *
     * @param file The file to be uploaded.
     * @return A map containing the result for WangEditor, including the file's access URL.
     */
    @PostMapping ("/wang/upload")
    public Map<String, Object> wangEditorUpload(MultipartFile file) {
        String flag = System.currentTimeMillis() + "";
        String fileName = file.getOriginalFilename();
        try {
            // File storage format: timestamp-filename
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
            System.out.println(fileName + "--Upload successful");
            Thread.sleep(1L);
        } catch(Exception e) {
            System.err.println(fileName + "--File upload failed");
        }
        String http = "http://" + ip + ":" + port + "/files/";
        Map<String, Object> resMap = new HashMap<>();
        // Response for WangEditor image upload
        resMap.put("errno", 0);
        resMap.put("data", CollUtil.newArrayList(Dict.create().set("url", http + flag + "-" + fileName)));
        return resMap;
    }

}
