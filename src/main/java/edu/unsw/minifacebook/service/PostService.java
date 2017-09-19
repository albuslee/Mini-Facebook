package edu.unsw.minifacebook.service;

import java.util.List;

import edu.unsw.minifacebook.bean.PostBean;

public class PostService {
	
	
	public List<PostBean> loadAllPosts(){
		List<PostBean> allPosts = null;
		//TODO: load all posts, including friend's and non-friends'
		return allPosts;
	}
	
	public List<PostBean> loadFriendPosts(){
		List<PostBean> postsList = null;
		//TODO: load all friends' posts
		return postsList;
	}
}
