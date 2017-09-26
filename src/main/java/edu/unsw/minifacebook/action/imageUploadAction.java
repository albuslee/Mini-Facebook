package edu.unsw.minifacebook.action;
import java.io.File;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;  
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;  
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.DAO.DetailDAO;
import edu.unsw.minifacebook.bean.DetailBean;  
  
@Controller("brandAction")  
@Scope("prototype")  
public class imageUploadAction extends ActionSupport {  
	@Autowired
	private DetailDAO detailDao;
	
    private static final long serialVersionUID = 1L;  
      
    //此属性对应于表单中文件字段的名称    
    private File uploadFile;   
    //下面的这两个属性的命名必须遵守上定的规则，即为"表单中文件字段的名称" + "相应的后缀"    
    private String uploadFileContentType; // 得到上传的文件的数据类型,    
    private String uploadFileFileName; // 得到上传的文件的名称    
      
    public File getUploadFile() {  
        return uploadFile;  
    }  
    public void setUploadFile(File uploadFile) {  
        this.uploadFile = uploadFile;  
    }  
      
    public String getUploadFileContentType() {  
        return uploadFileContentType;  
    }  
    public void setUploadFileContentType(String uploadFileContentType) {  
        this.uploadFileContentType = uploadFileContentType;  
    }  
      
    public String getUploadFileFileName() {  
        return uploadFileFileName;  
    }  
    public void setUploadFileFileName(String uploadFileFileName) {  
        this.uploadFileFileName = uploadFileFileName;  
    }  
      
    public String execute() throws IOException  
    {  
        String realPath = ServletActionContext.getServletContext().getRealPath("/image");  
        SimpleDateFormat date = new SimpleDateFormat("/yyyy/MM/dd");  
        String dateTime = date.format(new Date());  
        realPath += dateTime;  
          
        uploadFileFileName = UUID.randomUUID().toString() + uploadFileFileName.substring(uploadFileFileName.lastIndexOf('.'));  
          
        System.out.println(uploadFileContentType);   
        //控制图片类型  
        if(uploadFileContentType.equals("image/gif") || uploadFileContentType.equals("image/jpeg") ||   
                uploadFileContentType.equals("image/png") || uploadFileContentType.equals("image/bmp") ||   
                uploadFileContentType.equals("image/x-icon") || uploadFileContentType.equals("image/pjpeg"))  
        {  
            //判断文件是否为空,并且文件不能大于2M  
            if(uploadFile != null && uploadFile.length() < 2097152)  
            {    
                //根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。  
                File filePath = new File(new File(realPath), uploadFileFileName);    
                //判断路径是否存在    
                if(!filePath.getParentFile().exists())  
                {  
                    //如果不存在，则递归创建此路径   
                    filePath.getParentFile().mkdirs();  
                }  
                String imageResource="image"+dateTime+"/"+uploadFileFileName;
                HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST); 
                DetailBean detailbean=(DetailBean) request.getSession().getAttribute("detailbean");
                detailbean.setPhoto(imageResource);
                detailDao.updateObject(detailbean);
                System.out.println(uploadFileFileName);   
                System.out.println(filePath.getParentFile()); 
                ActionContext.getContext().getSession().put("detailbean", detailbean);
                //将文件保存到硬盘上,Struts2会帮我们自动删除临时文件  
                try {  
                    FileUtils.copyFile(uploadFile, filePath);  
                } catch (IOException e) {  
                    System.out.println("图片上传失败");   
                    e.printStackTrace();  
                }   
            }    
        }  
        return "success";    
    }  
}  