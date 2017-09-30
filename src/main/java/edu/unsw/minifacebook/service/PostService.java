package edu.unsw.minifacebook.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.DAO.FriendDAO;
import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;
import edu.unsw.minifacebook.forms.PostForm;

@Repository
@Transactional
public class PostService {
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private FriendDAO friendDao;
	
	public List<PostBean> loadAllPosts(){
		List<PostBean> allPosts = null;
		//TODO: load all posts, including friend's and non-friends'
		return allPosts;
	}
	
	public List<PostBean> loadFriendPosts(String username){
		List<PostBean> postsList = null;
		UserBean userBean = userDao.getUserByUsername(username);
		List<Integer> userList = friendDao.getAllFriendIdsByUserid(userBean.getUserId());

		postsList = postDao.getPostsByUserlist(userList);
		return postsList;
	}
	
	public List<PostBean> loadFriendPosts(Integer userId){
		List<PostBean> postsList = null;
		List<Integer> userList = friendDao.getAllFriendIdsByUserid(userId);
		postsList = postDao.getPostsByUserlist(userList);
		return postsList;
	}
	
	public void createNewPost(String creatorUsername, PostForm postForm) {
		PostBean postBean = new PostBean();
		UserBean creator = userDao.getUserByUsername(creatorUsername);
		BeanUtils.copyProperties(postForm, postBean);
		postBean.setCreator(creator);
		postDao.saveObject(postBean);
	}
}
