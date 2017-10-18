package edu.unsw.minifacebook.service;

import java.util.List;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.LikeDAO;
import edu.unsw.minifacebook.bean.LikeBean;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = false)
public class LikeService {
	
	@Autowired
	private LikeDAO likeDao;
	
	
	
	public void addLikes(String username, int postid, int thumb) {
		likeDao.addLikes(username, postid, thumb);
	}
	
	public void delLike(String username, int postid) {
		likeDao.deleteLikes(username, postid);
	}
	
	public int numLikes(int postid) {
		return likeDao.numLikes(postid);
	}

}
