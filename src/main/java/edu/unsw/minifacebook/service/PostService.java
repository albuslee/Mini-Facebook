package edu.unsw.minifacebook.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.unsw.minifacebook.DAO.FriendDAO;
import edu.unsw.minifacebook.DAO.LikeDAO;
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

	@Autowired
	private LikeDAO likeDAO;

	public List<PostBean> loadAllPosts() {
		List<PostBean> allPosts = null;
		// TODO: load all posts, including friend's and non-friends'
		return allPosts;
	}

	public List<PostBean> loadFriendPosts(String username) {
		List<PostBean> postsList = null;
		UserBean userBean = userDao.getUserByUsername(username);
		List<Integer> userList = friendDao.getAllFriendIdsByUserid(userBean.getUserid());
		userList.add(userBean.getUserid());
		postsList = postDao.getPostsByUserlist(userList);
		if (postsList != null) {
			for (PostBean pb : postsList) {
				pb.setLikenum(likeDAO.numLikes(pb.getId()));
			}
		}
		return postsList;
	}

	public List<PostBean> loadFriendPosts(Integer userId) {
		List<PostBean> postsList = null;
		List<Integer> userList = friendDao.getAllFriendIdsByUserid(userId);
		postsList = postDao.getPostsByUserlist(userList);
		for (PostBean pb : postsList) {
			pb.setLikenum(likeDAO.numLikes(pb.getId()));
		}
		return postsList;
	}

	public void createNewPost(String creatorUsername, PostForm postForm) {
		PostBean postBean = new PostBean();
		UserBean creator = userDao.getUserByUsername(creatorUsername);
		BeanUtils.copyProperties(postForm, postBean);
		postBean.setCreatorid(creator.getUserid());
		postBean.setPosttime(new Date());
		postDao.saveObject(postBean);
	}
}
