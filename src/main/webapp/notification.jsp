<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="edu.unsw.minifacebook.bean.DetailBean"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>

	<%@ page import="edu.unsw.minifacebook.bean.NotificationBean"%>
	<%@ page import="edu.unsw.minifacebook.DAO.NotificationDAO"%>
	<%@ page import="edu.unsw.minifacebook.DAO.FriendDAO"%>
	<%@ page import="edu.unsw.minifacebook.bean.UserBean"%>
	
	<%@ page import="edu.unsw.minifacebook.service.NotificationService"%>


<%
DetailBean detail = (DetailBean) request.getSession().getAttribute("detailbean");
int nl_size = 0;
if(detail!=null){
	List<NotificationBean> notificationlist = (List<NotificationBean>) request.getSession().getAttribute("notificationList");
	if (notificationlist != null) {
		
		String comment = new String();
		
		for(NotificationBean fr: notificationlist){
			if (fr.getnotification_status().equals("unread")) {
				nl_size = nl_size + 1;
			}
			String nlstr=String.valueOf(nl_size);
			request.getSession().setAttribute("nl_size", nlstr);
			UserBean nfrom = fr.getFrom2();
			DetailBean dBean = nfrom.getDetailBean();
			if (fr.getType().equals("like")) {
				comment = fr.getcommented_record();
				//out.println("[" + fr.getnotification_status() + "]");

				if (comment.contains("<img") && comment.contains("height") && comment.contains("width")) {
					comment = comment.replaceAll("style=\"height:960px; width:1280px\"", "style=\"height:32px; width:32px\"");
				}
				out.println("Your post " + comment + " was liked by user " + dBean.getName() + ". ");%><br><%

			}
			else if (fr.getType().equals("friend")) {
				//out.println("[" + fr.getnotification_status() + "]");
				String output="User " + dBean.getName() + " sends a friend request. ";
				%><a href="#" class="list-group-item" ><%=output%></a><%
			}
			else if (fr.getType().equals("accept")) {
				//out.println("[" + fr.getnotification_status() + "]");
				String output="User " + dBean.getName() + " has accepted your friend request. ";
				%><a href="#" class="list-group-item" ><%=output%></a><%
			}
		}
	}
}
%>
