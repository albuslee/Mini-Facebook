package edu.unsw.minifacebook.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.unsw.minifacebook.bean.DetailBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.forms.PostForm;
import edu.unsw.minifacebook.service.PostService;
import edu.unsw.minifacebook.service.UserService;
import edu.unsw.minifacebook.util.Emailer;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

public class PostAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	// private UserForm userform;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	private PostForm postform;

	public PostForm getPostform() {
		return postform;
	}


	public void setPostform(PostForm postform) {
		this.postform = postform;
	}


	public String loadposts() {
		try {
			DetailBean db = (DetailBean) ActionContext.getContext().getSession().get("detailbean");	
			if(db == null) return LOGIN;
			String currentUsername = db.getUsername();
			List<PostBean> postList = postService.loadFriendPosts(currentUsername);
			request.put("postlist", postList);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	
	public String addposts() {
		try {

			DetailBean detailBean = (DetailBean) ActionContext.getContext().getSession().get("detailbean");
			postService.createNewPost(detailBean.getUsername(), postform);
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
					.get(ServletActionContext.HTTP_REQUEST);
            ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
            String stoplist = request.getRealPath("stoplist.txt") ;
            String key1 = ek.ExtractSentenceKeyword(postform.getDescription(), new File(stoplist));
            if(key1 != null && !key1.isEmpty()) {
            	Emailer.sendMail("zcy19920424@gmail.com", detailBean.getName() + " send bully post",
            			detailBean.getName() + " send bully post, content:" + postform.getDescription());
            }
            
            
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	   
    private File upload; // �ļ�  
    private String uploadContentType; // �ļ�����  
    private String uploadFileName; // �ļ���  
    
    public File getUpload() {  
        return upload;  
    }  
  
    public void setUpload(File upload) {  
        this.upload = upload;  
    }  
  
    public String getUploadContentType() {  
        return uploadContentType;  
    }  
  
    public void setUploadContentType(String uploadContentType) {  
        this.uploadContentType = uploadContentType;  
    }  
  
    public String getUploadFileName() {  
        return uploadFileName;  
    }  
  
    public void setUploadFileName(String uploadFileName) {  
        this.uploadFileName = uploadFileName;  
    }  
	
	public String uploadpostimage() throws IOException {
		  // ���response,request  
        HttpServletResponse response = ServletActionContext.getResponse();  
        HttpServletRequest request = ServletActionContext.getRequest();  
  
        response.setCharacterEncoding("utf-8");  
        PrintWriter out = response.getWriter();  
        // CKEditor�ύ�ĺ���Ҫ��һ������  
        String callback = request.getParameter("CKEditorFuncNum");  
        String expandedName = ""; // �ļ���չ��  
        if (uploadContentType.equals("image/pjpeg")  
                || uploadContentType.equals("image/jpeg")) {  
            // IE6�ϴ�jpgͼƬ��headimageContentType��image/pjpeg����IE9�Լ�����ϴ���jpgͼƬ��image/jpeg  
            expandedName = ".jpg";  
        } else if (uploadContentType.equals("image/png")  
                || uploadContentType.equals("image/x-png")) {  
            // IE6�ϴ���pngͼƬ��headimageContentType��"image/x-png"  
            expandedName = ".png";  
        } else if (uploadContentType.equals("image/gif")) {  
            expandedName = ".gif";  
        } else if (uploadContentType.equals("image/bmp")) {  
            expandedName = ".bmp";  
        } else {  
            out.println("<script type=\"text/javascript\">");  
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
                    + ",''," + "'Format not correct(Must be.jpg/.gif/.bmp/.png�ļ�)');");  
            out.println("</script>");  
            return null;  
        }  
        if (upload.length() > 600 * 1024) {  
            out.println("<script type=\"text/javascript\">");  
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
                    + ",''," + "'File should be less than 600k');");  
            out.println("</script>");  
            return null;  
        }  
  
        InputStream is = new FileInputStream(upload);  
        //ͼƬ�ϴ�·��  
        String uploadPath = ServletActionContext.getServletContext().getRealPath("postimage");  
        String fileName = java.util.UUID.randomUUID().toString(); // ����ʱ��+UUID�ķ�ʽ�漴����  
        fileName += expandedName;  
        File file = new File(uploadPath);  
        if (!file.exists()) { // ���·�������ڣ�����  
            file.mkdirs();  
        }  
        File toFile = new File(uploadPath, fileName);  
        OutputStream os = new FileOutputStream(toFile);  
        byte[] buffer = new byte[1024];  
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {  
            os.write(buffer, 0, length);  
        }  
        is.close();  
        os.close();  
  
        // ����"ͼ��"ѡ�����ʾͼƬ  request.getContextPath()Ϊweb��Ŀ��   
        out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
                + ",'" + "postimage/" + fileName + "','')");  
        out.println("</script>");  
        return null;  
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
