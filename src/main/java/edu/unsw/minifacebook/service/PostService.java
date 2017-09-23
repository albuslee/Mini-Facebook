package edu.unsw.minifacebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.unsw.minifacebook.DAO.FriendDAO;
import edu.unsw.minifacebook.DAO.PostDAO;
import edu.unsw.minifacebook.DAO.UserDAO;
import edu.unsw.minifacebook.bean.PostBean;
import edu.unsw.minifacebook.bean.UserBean;

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
		List<Integer> userList = friendDao.getAllFriendIdsByUsername(username);
		postsList = postDao.getPostsByUserlist(userList);
		return postsList;
	}
}
