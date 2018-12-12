package cn.itcast.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/fileup3")
    public String fileupload3(HttpServletRequest request, MultipartFile load) throws Exception {
        System.out.println("文件上传");

        String path = "http://localhost:8080/load/";

        //解析request对象，获取上传文件项
        String filename = load.getOriginalFilename();

        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename =uuid+"_"+filename;

        //创建客户端对象
        Client client = Client.create();
        //和图片服务器相连接
        WebResource resource = client.resource(path + filename);

        //上传文件
        resource.put(load.getBytes());


        return "success";
    }

    @RequestMapping("/fileup2")
    public String fileupload2(HttpServletRequest request, MultipartFile load) throws Exception {
        System.out.println("文件上传");

        String Path = request.getSession().getServletContext().getRealPath("/uploads/");

        File file = new File(Path);

        if (!file.exists()){

            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        String filename = load.getOriginalFilename();

                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename =uuid+"_"+filename;



                load.transferTo(new File(Path,filename));

        return "success";
    }

    @RequestMapping("/fileup")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");

        String Path = request.getSession().getServletContext().getRealPath("/uploads/");

        File file = new File(Path);

        if (!file.exists()){

            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(request);

        for (FileItem item : items) {
            //进行判断，当前item对象是否是上传文件项
            if (item.isFormField()){
                //普通表单项
            }else{
                //说明上传文件项
                String filename = item.getName();

                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename =uuid+"_"+filename;

                item.write(new File(Path,filename));
                //删除临时文件
                item.delete();
            }
        }

        return "success";
    }
}
