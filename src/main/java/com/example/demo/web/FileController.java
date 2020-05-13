package com.example.demo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import com.example.demo.web.sys.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${uploadFilePath}")
    private String filePath;

    @PostMapping(value = "/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        try {

            if (!Paths.get(filePath).toFile().exists()) {
                Paths.get(filePath).toFile().mkdirs();
            }

            String videoName = file.getOriginalFilename();
            String suffix = videoName.substring(videoName.lastIndexOf("."));
            String newVideoName = "12" + suffix;

            File dest = new File(filePath, newVideoName);
            file.transferTo(dest);
        } catch (Exception e) {
            logger.error("上传失败" + e.getMessage(), e);
        }

        return Result.success();
    }

    @GetMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        {
            String fileName = request.getParameter("fileName");
            String downLoadFilePath = filePath + File.separator + fileName;
            OutputStream os = null;
            FileInputStream in = null;
            try {
                File f = new File(downLoadFilePath);
                response.setContentType("application/x-download");
                response.addHeader("Content-Length", "" + f.length());
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                os = response.getOutputStream();
                in = new FileInputStream(f);
                int n;
                byte[] b = new byte[500];
                while ((n = in.read(b)) != -1) {
                    os.write(b, 0, n);
                }
                os.flush();
            } catch (Exception e) {
                LOGGER.error("下载出错.错误信息:{}" + e.getMessage(), e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        LOGGER.error("IOException:" + e.getMessage(), e);
                    }
                }

                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        LOGGER.error("IOException:" + e.getMessage(), e);
                    }
                }
            }
        }
    }
}
