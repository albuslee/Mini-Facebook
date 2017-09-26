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
      
    //�����Զ�Ӧ�ڱ����ļ��ֶε�����    
    private File uploadFile;   
    //��������������Ե��������������϶��Ĺ��򣬼�Ϊ"�����ļ��ֶε�����" + "��Ӧ�ĺ�׺"    
    private String uploadFileContentType; // �õ��ϴ����ļ�����������,    
    private String uploadFileFileName; // �õ��ϴ����ļ�������    
      
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
        //����ͼƬ����  
        if(uploadFileContentType.equals("image/gif") || uploadFileContentType.equals("image/jpeg") ||   
                uploadFileContentType.equals("image/png") || uploadFileContentType.equals("image/bmp") ||   
                uploadFileContentType.equals("image/x-icon") || uploadFileContentType.equals("image/pjpeg"))  
        {  
            //�ж��ļ��Ƿ�Ϊ��,�����ļ����ܴ���2M  
            if(uploadFile != null && uploadFile.length() < 2097152)  
            {    
                //���� parent ����·������ child ·�����ַ�������һ���� File ʵ����  
                File filePath = new File(new File(realPath), uploadFileFileName);    
                //�ж�·���Ƿ����    
                if(!filePath.getParentFile().exists())  
                {  
                    //��������ڣ���ݹ鴴����·��   
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
                //���ļ����浽Ӳ����,Struts2��������Զ�ɾ����ʱ�ļ�  
                try {  
                    FileUtils.copyFile(uploadFile, filePath);  
                } catch (IOException e) {  
                    System.out.println("ͼƬ�ϴ�ʧ��");   
                    e.printStackTrace();  
                }   
            }    
        }  
        return "success";    
    }  
}  