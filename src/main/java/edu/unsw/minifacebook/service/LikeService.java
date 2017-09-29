package edu.unsw.minifacebook.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.LikeDAO;
import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

public class LikeService {
	
	@Autowired
	private LikeDAO likeDao;
	
	@Autowired
	private PostBean postBean;
	
	@Autowired
	private LikeBean likeBean;
	
	
	public int addLike(PostBean postBean, UserBean userBean, int thumb) {
		likeBean.setLikeFrom(userBean);
		likeBean.setPostId(postBean);
		likeBean.setThumb(thumb);
		return postBean.getId();
	}
	
	public void delLike(int postid) {
		likeDao.deleteLikes(postid);
	}

}
