package com.hexiang.fastdfs.controller;

import com.hexiang.fastdfs.pojo.FastDFSFile;
import com.hexiang.fastdfs.utils.FastDFSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;

/**
 * @author: hexiang
 * @date: 2022/4/4
 * @description:
 */
@Controller
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

      /**
       * upload file
       */
      @PostMapping("/upload")
      public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
       if(file.isEmpty()) {
          redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
          return "redirect:uploadStatus";
       }
          try {
              String path = saveFile(file);
              redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");
              redirectAttributes.addFlashAttribute("path","路径："+path);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return "redirect:uploadStatus";
      }

      public String saveFile(MultipartFile multipartFile) throws Exception {
       String[] fileAbsolutePath = {};
       String fileName = multipartFile.getOriginalFilename();
       String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
       byte[] file_buff = null;
       InputStream input = multipartFile.getInputStream();
       if (input != null) {
                  int len1 = input.available();
                  file_buff = new byte[len1];
                  input.read(file_buff);
       }
       input.close();
       FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
       fileAbsolutePath = FastDFSClientUtil.uploadFile(file);
       if (fileAbsolutePath == null) {
           logger.error("upload file failed,please upload again!");
       }
          return FastDFSClientUtil.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
      }

    /**
     * 页面跳转
     */
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    @GetMapping("/")
    public String upload() {
        return "upload";
    }
}
