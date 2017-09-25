<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/main.css">
<style type="text/css">
.UIContentTopper {
	padding: 14px 0 0 17px;
	margin: 50px auto 15px auto;
	border-top: 2px solid #d3dae6
}

.UIContentTopperSlim {
	padding: 0 0 0 17px;
	margin: 0 auto 15px auto
}

.UIContentTopper_footer {
	width: 578px;
	margin: 5px auto 0 auto;
	font-size: 9px
}

.UIContentTopper_text_container {
	margin-top: 6px;
	width: 465px;
	float: left
}

.UIContentTopper_text_headline {
	font-size: 15px;
	font-weight: bold
}

.UIContentTopper_text {
	font-size: 11px;
	margin-top: 4px
}

.UIContentTopper_large .UIContentTopper_text {
	width: 400px
}

.UIContentTopper_text_bold {
	font-weight: bold
}

.UIContentTopper_thumbnail {
	float: left;
	margin: 0 15px 0 0
}

.UIContentTopper .verticalAlign {
	margin-top: 8px
}

.unsupportedBrowser, .unsupportedBrowser #contentCurve {
	background-color: #e9ebee
}

.unsupportedBrowserPage {
	margin: 0 auto;
	width: 930px
}

.unsupportedBrowserBox {
	text-align: center;
	padding: 30px 20px
}

.unsupportedBrowserText {
	margin-left: auto;
	margin-right: auto;
	width: 570px
}

.unsupportedBrowserBrowser {
	display: inline-block;
	border: 1px solid #fff
}

.unsupportedBrowserBrowser:hover {
	text-decoration: none;
	background: #f6f7f9;
	border-color: #dddfe2
}

.unsupportedBrowserMobile {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 320px
}

#captcha fieldset {
	border-top: 1px solid #c0c0c0;
	border-bottom: 1px solid #c0c0c0;
	margin: 0;
	padding: 10px
}

#captcha legend {
	color: gray
}

#captcha .divider {
	display: none
}

#captcha .captcha_refresh {
	font-size: 9px;
	color: gray
}

#captcha .recaptcha_text {
	font-size: 11px;
	line-height: 16px
}

#captcha .captcha_optout {
	font-size: 11px;
	padding: 10px 0 5px
}

#captcha #recaptcha_image {
	font-weight: bold;
	margin: 10px 0 0 0
}

#captcha #recaptcha_image a.recaptcha_audio_cant_hear_link {
	display: block;
	font-weight: normal
}

#captcha .captcha_loading {
	border: 0
}

#captcha .captcha_image img {
	border: 1px solid #bec2c9
}

#captcha .captcha_input input {
	direction: ltr;
	margin-top: 4px;
	width: 137px
}

#captcha .captcha_input label {
	margin-right: 4px
}

.register #captcha .captcha_input label {
	color: #666;
	font-weight: bold
}

#generic_dialog.captcha .generic_dialog_popup {
	width: 340px
}

#generic_dialog.captcha #generic_dialog_popup {
	border: 0 solid white;
	margin: auto
}

#generic_dialog.captcha #generic_dialog_popup legend {
	display: none
}

.audiocaptcha {
	overflow: hidden;
	display: block;
	margin: 0;
	padding: 0
}

.audiocaptcha a {
	display: block
}

.recaptcha_is_showing_audio .recaptcha_only_if_image {
	display: none !important
}

.recaptcha_is_showing_audio .recaptcha_only_if_audio {
	display: inline !important
}

.captcha_interstitial {
	border: 1px solid #ccc;
	-moz-border-radius: 4px 4px 4px 4px;
	margin: 20px auto;
	padding: 20px;
	width: 555px
}

.fbIndex .uiWashLayoutBlueWash {
	background-color: #c4d2e7
}

.fbIndex .contentContainer {
	margin: 0 auto 0 auto;
	width: 980px
}

.fbIndex .bodyWash {
	margin-bottom: 0
}

.fbIndex .locales {
	margin: 10px auto 0;
	width: 980px
}

.fbIndex .gradient {
	background: url(/rsrc.php/v3/yB/r/TwAHgQi2ZPB.png) 0 bottom repeat-x;
	background: linear-gradient(white, #D3D8E8)
}

.fbIndex .gradient {
	min-width: 980px
}

.fbIndex .gradient .gradientContent {
	margin: 0 auto;
	width: 980px
}

._2o9o {
	background-color: #edf0f5
}

._2oa4 {
	margin: 0 auto;
	overflow: hidden;
	padding: 20px 0;
	width: 940px
}

._2o9q {
	color: #333;
	font-size: 36px;
	font-weight: bold;
	margin-bottom: 1px;
	margin-top: -4px
}

._2o9t {
	color: #333;
	font-size: 19px;
	margin-bottom: 19px
}

._hlg {
	display: inline-block;
	margin-top: 45px;
	text-align: center;
	width: 485px
}

._hlj {
	display: inline-block;
	float: right;
	margin-top: 2px;
	vertical-align: top;
	width: 399px
}

._hlk {
	color: #333;
	font-size: 21px;
	line-height: 27px;
	margin-bottom: 9px
}

._hll {
	color: #4b4f56;
	font-size: 18px;
	line-height: 21px;
	padding: 0 30px
}

._hlm {
	margin-left: -12px
}

._5iyz {
	max-width: 400px
}

._5iyx {
	color: #0e385f;
	font-size: 20px;
	font-weight: bold;
	line-height: 29px;
	margin-top: 40px;
	width: 450px;
	word-spacing: -1px
}

._5iyy {
	margin-right: 15px;
	width: 565px
}

._7kq {
	margin-top: 52px;
	text-align: center;
	width: 475px
}

._7ku {
	text-align: center
}

._7kw {
	color: #3b5998;
	font-family: "HelveticaNeue-Thin", Helvetica, Arial, sans-serif;
	font-size: 45px;
	line-height: 48px;
	margin-left: 100px;
	margin-top: 20px;
	text-align: center;
	width: 270px;
	word-spacing: -1px
}

._15lf {
	color: #3b5998;
	font-family: "Freight Sans Semibold", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	font-size: 28px;
	line-height: 30px;
	margin-left: 100px;
	margin-top: 15px;
	text-align: center;
	width: 300px;
	word-spacing: -1px
}

._7kx {
	color: #4b4f56;
	font-family: "HelveticaNeue-Light", Helvetica, Arial, sans-serif;
	font-size: 25px;
	line-height: 30px;
	margin-left: 100px;
	margin-top: 15px;
	text-align: center;
	width: 270px;
	word-spacing: -1px
}

._15lg {
	color: #1d2129;
	font-family: "Freight Sans Pro Book", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	font-size: 20px;
	line-height: 26px;
	margin-left: 100px;
	margin-top: 15px;
	text-align: center;
	width: 300px;
	word-spacing: -1px
}

._7ky {
	margin-top: 20px;
	text-align: center;
	width: 475px
}

._7kz {
	height: 30px;
	text-align: center;
	width: 30px
}

._7k- {
	margin-right: 15px;
	width: 565px
}

._3y0_ {
	position: relative
}

._54-l {
	width: 170px
}

._54-m {
	width: 140px
}

._54-n {
	width: 110px
}

._3dk_ ._159u._3y0_ {
	background: white;
	border: 1px solid #ccc;
	border-radius: 3px;
	box-sizing: border-box;
	height: 314px;
	padding: 48px 12px 12px 12px;
	width: 290px
}

._50j7 {
	border-radius: 5px;
	box-shadow: inset 0 -1px 1px 0 rgba(0, 0, 0, .3), inset 0 1px 1px 0
		rgba(255, 255, 255, .5), 0 2px 4px 0 rgba(0, 0, 0, .3);
	vertical-align: middle
}

._583z {
	border: 4px solid white;
	border-radius: 2px;
	vertical-align: middle
}

._583- {
	background: #dddfe2;
	border-radius: 3px;
	display: block;
	padding: 1px;
	width: fit-content
}

._16a6:hover, ._16a6 {
	text-decoration: none
}

._3y11 {
	left: 0;
	position: relative;
	top: 0
}

html ._3dk_ ._40lf, html ._3dk_ ._40le {
	height: 28px;
	width: 160px
}

._3zkt {
	background-color: #fa3e3e;
	border-radius: 24px;
	color: #fff;
	cursor: default;
	font-size: 13px;
	height: 24px;
	line-height: 24px;
	position: absolute;
	right: -8px;
	top: 125px;
	-moz-user-select: none;
	width: 24px
}

._54-n ._3zkt {
	top: 95px
}

._3zkt._3zku {
	font-size: 10px
}

._3y12 {
	max-height: 45px;
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: break-word;
	color: #1d2129
}

._3dk_ ._159u ._3y12 {
	display: none
}

._3y11:hover ._227u._3v6q, ._227u._4f1s._3v6q {
	opacity: 1
}

._3y11:hover ._227u:hover, ._4f1s._227u:hover {
	cursor: pointer;
	opacity: 1;
	transition: none
}

._5ieh {
	padding-top: 7px
}

._227u {
	background: white;
	border: none
}

._3dk_ ._159u ._227u {
	display: none
}

._227u {
	border-radius: 24px;
	height: 24px;
	line-height: 24px;
	opacity: 0;
	position: absolute;
	right: -17px;
	top: -15px;
	transition: none;
	width: 24px
}

._54-o {
	right: -8px;
	top: -8px
}

._do- ._583z {
	border: 0;
	border-radius: 4px
}

._do- ._583- {
	background: none
}

._31nc {
	text-align: center
}

._31nd {
	margin-top: -4px;
	text-align: right
}

._31ne {
	display: inline-block
}

._3316 {
	margin-bottom: 18px
}

._31ni {
	width: 180px
}

._31nj {
	width: 180px
}

._5_bi {
	text-align: center
}

._2j4v {
	display: inline-block;
	margin-left: -5px;
	text-align: left;
	vertical-align: middle;
	width: 178px
}

._2j4v label {
	font-weight: normal
}

._3d7v {
	color: #90949c;
	margin: 12px auto 0;
	text-align: left;
	width: 170px
}

._3d7v li {
	font-size: 12px;
	list-style: none;
	margin-bottom: 14px;
	padding-left: 26px;
	position: relative
}

._3d7v li::before {
	background-image: url(/rsrc.php/v3/yw/r/LH5azbV8i9H.png);
	background-repeat: no-repeat;
	background-size: 24px 84px;
	background-position: 0 -48px;
	content: '';
	height: 16px;
	left: 0;
	position: absolute;
	top: 0;
	width: 16px
}

._2j4w ._3317, ._2j4w ._31nj, ._2j4w ._31ni {
	display: block;
	margin: 0 auto 12px;
	width: 230px
}

#facebook ._2j4w ._31nj {
	border-radius: 2px;
	letter-spacing: 3px
}

#facebook ._2j4w ._31nj::-moz-placeholder, #facebook ._2j4w ._31nj:-moz-placeholder
	{
	letter-spacing: normal
}

._2j4w ._2j4v {
	margin-left: 0;
	width: 250px
}

._2j4w ._3d7v {
	width: 230px
}

._2j4w ._3d7w {
	font-size: 13px
}

._2d4g {
	left: 4px;
	position: absolute;
	text-align: center;
	top: 4px;
	-moz-user-select: none;
	z-index: 1
}

._t7b {
	background-color: rgba(0, 0, 0, .3);
	border-radius: 16px;
	height: 16px;
	transition: all 100ms cubic-bezier(.08, .52, .52, 1);
	width: 16px
}

._t7b::before, ._t7b::after {
	bottom: 2px;
	content: '';
	display: block;
	position: absolute;
	right: 2px;
	transform-origin: bottom right;
	transition: inherit
}

._t7b::before {
	background-image: url(/rsrc.php/v3/yT/r/a9PyydRUfP_.png);
	background-repeat: no-repeat;
	background-size: 24px 302px;
	background-position: 0 -250px;
	height: 12px;
	opacity: 1;
	width: 12px
}

._t7b::after {
	background-image: url(/rsrc.php/v3/yT/r/a9PyydRUfP_.png);
	background-repeat: no-repeat;
	background-size: 24px 302px;
	background-position: 0 -88px;
	height: 20px;
	opacity: 0;
	transform: scale(.6);
	width: 20px
}

._csi ._t7b:hover {
	transition: none
}

._csi:hover ._t7b {
	background-color: #fff;
	box-shadow: 1px 1px 2px 0 rgba(0, 0, 0, .3);
	transform: scale(1.5);
	transform-origin: bottom right
}

._csi:hover ._t7b::before {
	opacity: 0
}

._csi:hover ._t7b::after {
	opacity: 1
}

._1gax {
	align-items: center;
	background-color: #f6f7f9;
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
	display: flex;
	height: 160px;
	justify-content: center;
	width: 160px
}

._1gaz {
	background-color: #fff;
	border-bottom-left-radius: 4px;
	border-bottom-right-radius: 4px;
	overflow: hidden;
	padding: 12px 0;
	text-align: center;
	text-decoration: none;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 160px
}

._1ga- {
	white-space: normal
}

._csi ._1gb4 {
	background-color: #fa3e3e;
	border-radius: 24px;
	box-shadow: -1px 1px 2px 0 rgba(0, 0, 0, .3);
	color: #fff;
	cursor: default;
	font-size: 13px;
	height: 24px;
	line-height: 24px;
	position: absolute;
	right: -4px;
	text-align: center;
	top: -4px;
	-moz-user-select: none;
	width: 24px
}

._1gb4._51lr {
	font-size: 10px
}

._1gbd {
	border-radius: 4px;
	box-shadow: 0 0 0 1px #dddfe2;
	display: block;
	transition: all .2s ease-out;
	-moz-user-select: none
}

._1gbd:hover {
	box-shadow: 0 1px 8px 5px #dddfe2;
	text-decoration: none
}

._1gbg ._1gax {
	height: 100px;
	width: 100px
}

._1gbg ._1gaz {
	padding: 6px 0;
	width: 100px
}

._gt2 {
	position: relative;
	text-align: center
}

._55no {
	display: inline-block
}

._5lzt {
	padding-left: 11px;
	padding-right: 11px;
	vertical-align: top
}

._1ddf {
	margin-left: 12px;
	margin-right: 12px;
	vertical-align: top
}

._1ddg {
	padding-bottom: 24px
}

._4mow {
	margin: auto;
	width: 980px
}

html ._520k ._z36 {
	font-size: 36px
}

._8wm {
	padding-right: 48px
}

._4mow ._5h-b {
	width: 400px
}

._3nm9 {
	text-align: center
}

._4mow ._z34 {
	margin: auto;
	width: 450px
}

._3nma {
	padding-bottom: 43px
}

#facebook ._ihd {
	font-size: 36px
}

#facebook ._ihf {
	overflow: visible
}

._6_ {
	display: inline-block;
	vertical-align: top
}

._70 {
	width: 67px
}

._71 {
	width: 150px
}

._72 {
	width: 233px
}

._73 {
	width: 316px
}

._74 {
	width: 399px
}

._75 {
	width: 482px
}

._76 {
	width: 565px
}

._77 {
	width: 648px
}

._78 {
	width: 731px
}

._79 {
	width: 814px
}

._7a {
	width: 897px
}

._7b {
	width: 980px
}

._7c {
	margin-left: 15.999px !important
}

._7d {
	margin-right: 15.999px !important
}

._6j {
	border: 1px solid;
	border-radius: 5px;
	color: #fff;
	cursor: pointer;
	display: inline-block;
	letter-spacing: 1px;
	position: relative;
	text-shadow: 0 1px 2px rgba(0, 0, 0, .5)
}

._6nw ._6j {
	font-size: 23px
}

._6j:hover {
	text-decoration: none
}

._6wk {
	padding: 20px 30px
}

._6wl {
	background: linear-gradient(#67ae55, #578843);
	background-color: #69a74e;
	box-shadow: inset 0 1px 1px #a4e388;
	border-color: #3b6e22 #3b6e22 #2c5115
}

._6wl:hover {
	background: linear-gradient(#79bc64, #578843)
}

._6wm {
	border-width: 0;
	box-shadow: inset 0 1px 1px #acee8f, 0 1px 5px #000
}

._6wm:active {
	box-shadow: inset 0 1px 1px #acee8f, 0 0 3px #000
}

._6wn {
	background: linear-gradient(#698fbf, #506fa5);
	background-color: #4267b2;
	box-shadow: inset 0 1px 1px #96b7f1;
	border-color: #29487d #29487c #1a3a6d
}

._6wn:hover {
	background: linear-gradient(#7d9dce, #506fa5)
}

._6wo {
	border-width: 0;
	box-shadow: inset 0 1px 1px #96b7f1, 0 1px 5px #000
}

._6wo:active {
	box-shadow: inset 0 1px 1px #96b7f1, 0 0 3px #000
}

._5z9j {
	background: linear-gradient(#ce6565, #a84646);
	background-color: #fa3e3e;
	box-shadow: inset 0 1px 1px #f09595;
	border-color: #631010 #7c2828 #6d1a1a
}

._5z9j:hover {
	background: linear-gradient(#e07d7d, #af4b4b)
}

._5z9k {
	border-width: 0;
	box-shadow: inset 0 1px 1px #f09595, 0 1px 5px #000
}

._5z9k:active {
	box-shadow: inset 0 1px 1px #f09595, 0 0 3px #000
}

._6j:active {
	top: 1px
}

._67u, ._67u:hover, ._67u:active {
	background: linear-gradient(#fdfdfd, #e5e5e5);
	background-color: #f6f7f9;
	border-color: #a6a6a6 #959595 #959595;
	box-shadow: inset 0 1px 1px #fff;
	color: #b8b8b8;
	cursor: default;
	text-shadow: none;
	top: 0
}

._6wp {
	box-shadow: inset 0 1px 1px #fff, 0 0 3px #000
}

._50qd {
	padding-top: 9px;
	position: absolute
}

._6n {
	font-size: 23px;
	line-height: 120%
}

._6o {
	font-size: 19px;
	line-height: 126%
}

._6p {
	font-size: 17px;
	line-height: 22px
}

._6q {
	font-size: 15px;
	line-height: 20px
}

._6s {
	color: #333
}

._6t {
	color: #666
}

._6u {
	color: #999
}

._7g {
	color: #fff
}

._6v {
	font-weight: bold
}

._mf {
	font-weight: normal
}

._6w {
	text-align: left
}

._6x {
	text-align: center
}

._6y {
	text-align: right
}

._6z {
	text-align: justify
}

._58mq select {
	font-size: 13px;
	height: 30px;
	padding: 5px
}

._5k_4 {
	display: inline-block
}

._5k_5 {
	position: relative
}

._58ms {
	display: inline-block;
	max-width: 140px;
	vertical-align: middle
}

._5dva {
	padding-right: 6px
}

._58ms {
	font-size: 11px;
	max-width: 150px
}

._58mq ._5k_5 ._5k_6, ._58mq ._5k_5 ._5k_7 {
	top: 4px
}

._58mw {
	line-height: 16px;
	padding-top: 10px
}

._58mw h2 {
	padding-bottom: 3px
}

._58mw .captcha_input {
	margin: 5px 0
}

._58mx {
	margin-right: 50px
}

._58my {
	float: left;
	font-size: 12px;
	font-weight: bold;
	line-height: 14px;
	margin-top: 6px
}

._58mz {
	background-image: url(/rsrc.php/v3/yw/r/LH5azbV8i9H.png);
	background-repeat: no-repeat;
	background-size: 24px 84px;
	background-position: 0 -66px;
	float: left;
	height: 17px;
	margin-top: 5px;
	width: 12px
}

#captcha_response {
	padding: 3px
}

._5633 {
	font-size: 13px;
	max-width: 300px;
	padding: 13px
}

._1pd0 {
	color: red;
	font-size: 14px;
	font-weight: bold;
	line-height: 17px;
	max-width: 400px;
	padding: 6px 6px 6px 0
}

._1pd0 a {
	color: red;
	text-decoration: underline
}

._1ixn ._1pd0 {
	margin-top: -10px
}

._1ixn ._1pc_ {
	display: none;
	margin-top: -10px
}

._1ixn._5634 ._1pc_ {
	display: block
}

._1pc- ._1iy_ ._5633 {
	display: none
}

._5633._5634 {
	color: #fff
}

._1pc_ ._5633._5634 {
	background: none;
	color: red;
	font-size: 14px;
	font-weight: bold;
	max-width: 400px;
	padding: 6px 6px 6px 0
}

._5633._5635 {
	background: #ddae2b;
	color: #fff
}

._58mf div._5634 ._5dba {
	border-color: red;
	border-style: solid
}

._58mf ._5635 ._5dba {
	border-color: #ddae2b;
	border-style: solid
}

._58mf ._5636 ._5dba {
	border-color: #6fb554;
	border-style: solid
}

._58mf div._1pd1 ._5dba {
	border-color: red;
	border-style: solid
}

._5dbb {
	position: relative
}

._5dbb ._5dbc, ._5dbb ._5dbd {
	display: none;
	position: absolute;
	right: 9px;
	top: 9px
}

._5dbb._5634 ._5dbc, ._5dbb._5635 ._5dbd {
	display: inline
}

._5dbb ._1pc_ {
	display: none
}

._5634._1pd1 ._1pc_ {
	color: red;
	display: inline
}

._5634._1pd1 ._5dbc {
	display: none
}

._58mf {
	margin: 0 auto 0 auto;
	padding-bottom: 30px
}

.timelineSignUpDialog ._58mf {
	padding-bottom: 0
}

._58mf ._6o {
	font-size: 19px
}

._58mf ._6p {
	font-size: 17px
}

._2_68 {
	color: #1d2129;
	font-size: 18px;
	font-weight: normal
}

._59zo ._2_68 {
	color: #90949c;
	float: left;
	font-size: 16px
}

._58mi {
	min-width: 194px;
	padding: 7px 20px;
	text-align: center
}

._ngk {
	background: #5b93fc;
	background-image: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, .1))
}

._58mj {
	font-size: 16px
}

._58mk {
	border-top: 1px solid #dddfe2;
	color: #666;
	font-size: 13px;
	font-weight: bold;
	margin-top: 10px;
	padding-top: 15px
}

._58ml {
	margin-left: 10px;
	position: relative;
	top: 3px
}

._58mm {
	float: left
}

._58mn {
	background: #ffebe8;
	border: 1px solid #dd3c10;
	line-height: 15px;
	margin: 12px 0 12px 0;
	overflow: hidden;
	text-align: center
}

._58mo {
	padding: 7px 3px
}

._28r2 {
	text-align: right;
	width: 399px
}

._2rs6 ._2rs9 {
	font-weight: bold
}

._2rs6 ._2rsa {
	min-width: 50px
}

._a4y {
	display: inline-block;
	position: relative
}

._a4y ._a4z {
	background-color: #fff;
	border: 1px solid #1d2129;
	border-radius: 6px;
	box-shadow: 0 3px 8px rgba(0, 0, 0, .3);
	color: #1d2129;
	font-size: 14px;
	opacity: 0;
	padding: 12px;
	position: absolute;
	right: 105%;
	text-align: left;
	top: -5px;
	transition: opacity 1s;
	visibility: hidden;
	width: 200px;
	z-index: 1
}

._a4y ._a4z::before {
	border-color: transparent transparent transparent #1d2129;
	border-style: solid;
	border-width: 6px;
	content: " ";
	left: 100%;
	margin-top: -6px;
	position: absolute;
	top: 20%
}

._a4y ._a4z::after {
	border-color: transparent transparent transparent #fff;
	border-style: solid;
	border-width: 5px;
	content: " ";
	left: 100%;
	margin-top: -5px;
	position: absolute;
	top: 20%
}

._5k_2 {
	border-radius: 4px;
	border-width: 1px;
	display: inline-block;
	padding: 5px 0 5px 4px
}

._5k_2:first-child {
	margin-right: 4px
}

._5k_3 {
	display: inline-block
}

._17id {
	font-size: 13px;
	height: 30px
}

._5wa2 .uiPopover>a {
	padding-left: 10px;
	padding-right: 11px
}

._5wa2 ._55pe {
	padding-right: 10px;
	text-align: left
}

._17ie {
	position: relative
}

._58mt {
	color: #1d2129;
	font-size: 18px;
	font-weight: normal;
	line-height: 18px;
	padding: 0 10px 0 3px
}

._nf- {
	font-size: 13px;
	font-weight: bold;
	margin: 5px 0
}

._58mf ._58mg {
	border-color: #bdc7d8;
	border-radius: 5px; /*! margin:0; */
	width: 377px;
}

._58mf .uiStickyPlaceholderInput {
	background: white;
	border-radius: 5px;
	width: 399px
}

._58mf .uiStickyPlaceholderInput .placeholder {
	box-sizing: border-box;
	overflow: hidden;
	padding-left: 11px;
	text-overflow: ellipsis;
	white-space: nowrap
}

.gecko.mac ._58mf ._58mg, ._58mf ._58mg, ._58mf .uiStickyPlaceholderInput .placeholder
	{
	font-size: 18px;
	padding: 8px 10px;
}

._58mh {
	width: 399px
}

._58mh ._58mg {
	width: 172px
}

._58mh .uiStickyPlaceholderInput {
	width: 194px
}

._14f2 {
	outline: none
}

._4f2w ._54ne ._54nc {
	color: #4267b2
}

._4f2w {
	font-size: 18px;
	margin-top: -4px
}

._4f2w ._54ng {
	background-color: #fff;
	border: 1px solid #bdc7d8;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	border-top: none;
	box-shadow: 0 4px 8px -5px #999;
	width: 397px
}

._4f2w ._54nc {
	color: #999;
	padding: 8px 10px
}

._58mu {
	width: 399px
}

._1s-y ._58mu {
	width: 442px
}

._58mu ._58mv {
	color: #777;
	font-size: 11px;
	width: 316px
}

._5cg9 {
	margin-bottom: 5px;
	margin-top: 5px
}

._5bmw ._58mv {
	border: 1px solid #90949c;
	max-height: 120px;
	overflow-y: scroll;
	padding: 4px
}

._5bmw ._58mv ~.uiInputLabel {
	margin-bottom: 5px;
	margin-top: 5px
}

._5bmw ._5cga {
	font-size: 12px
}

._4tjo ._52lp ._52lq {
	color: #1d2129;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 40px;
	font-weight: bold;
	white-space: nowrap
}

#facebook ._4tjo ._52lp ._52lq:lang(en) {
	font-family: 'Freight Sans Bold', Helvetica, Arial, sans-serif;
	font-weight: normal;
	letter-spacing: normal
}

._52lp._59d- ._52lq {
	font-size: 36px
}

._52lp._59zm ._52lq {
	color: #1d2129;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 30px;
	text-align: center
}

._4tjo ._52lp ._52lr {
	color: #4b4f56;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 22px;
	font-weight: normal;
	line-height: 28px
}

#facebook ._4tjo ._52lp ._52lr:lang(en) {
	font-family: 'Freight Sans', Helvetica, Arial, sans-serif;
	letter-spacing: normal
}

._52lp._59d- ._52lr {
	color: #1d2129;
	font-size: 19px;
}

._52lp._59zm ._52lr {
	color: #90949c;
	font-family: Helvetica, Arial, sans-serif;
	font-size: 16px;
	text-align: center
}

._4tjo ._52lp ._2z12 {
	font-family: Helvetica, Arial, sans-serif;
	font-size: 18px;
	font-weight: bold;
	line-height: 26px;
	margin: 0;
	padding-left: 20px;
	padding-right: 20px;
	text-align: center;
	white-space: normal
}

._572t ._53iv {
	padding: 12px
}

._572t ._53ij {
	background-color: #be4b49;
	border: 0;
	border-radius: 2px;
	box-shadow: 0 0 0 1px rgba(139, 3, 0, .75), 0 1px 10px
		rgba(0, 0, 0, .35);
	color: #fff
}

._572t ._53ij a {
	color: #fff;
	font-weight: bold
}

._572t ._572u {
	background-color: #be4b49;
	border-color: #dddfe2;
	margin: 0 12px;
	padding: 8px 0
}

._572t ._5v-0 {
	padding-bottom: 18px
}

._572t ._53il {
	padding-top: 18px
}

._572t ._53im {
	padding-right: 18px
}

._572t ._53ik {
	padding-bottom: 18px
}

._572t ._53in {
	padding-left: 18px
}

._572t ._53il ._53io {
	background-image: url(/rsrc.php/v3/ym/r/WxHGkT5jz83.png);
	background-repeat: no-repeat;
	background-size: 22px 72px;
	background-position: 0 -58px;
	height: 11px;
	top: 7px;
	width: 22px
}

._572t ._53im ._53io {
	background-image: url(/rsrc.php/v3/ym/r/WxHGkT5jz83.png);
	background-repeat: no-repeat;
	background-size: 22px 72px;
	background-position: 0 -22px;
	height: 22px;
	right: 7px;
	width: 11px
}

._572t ._53ik ._53io {
	background-image: url(/rsrc.php/v3/ym/r/WxHGkT5jz83.png);
	background-repeat: no-repeat;
	background-size: 22px 72px;
	background-position: 0 -44px;
	bottom: 5px;
	height: 13px;
	width: 22px
}

._572t ._53in ._53io {
	background-image: url(/rsrc.php/v3/ym/r/WxHGkT5jz83.png);
	background-repeat: no-repeat;
	background-size: 22px 72px;
	background-position: 0 0;
	height: 22px;
	left: 7px;
	width: 11px
}

.sp_3p4Qgpsh7Ta_1_5x {
	background-image: url(/rsrc.php/v3/y5/r/2nb5RxeEcao.png);
	background-size: 122px 324px;
	background-repeat: no-repeat;
	display: inline-block;
	height: 120px;
	width: 120px
}

.sp_3p4Qgpsh7Ta_1_5x.sx_376ebe {
	background-position: 0 0
}

.sp_3p4Qgpsh7Ta_1_5x.sx_fdcb90 {
	background-position: 0 -122px
}

.sp_3p4Qgpsh7Ta_1_5x.sx_d94c09 {
	width: 80px;
	height: 78px;
	background-position: 0 -244px
}

.sp_WsUp3ca6zQF_1_5x {
	background-image: url(/rsrc.php/v3/ym/r/WxHGkT5jz83.png);
	background-size: 22px 72px;
	background-repeat: no-repeat;
	display: inline-block;
	height: 20px;
	width: 20px
}

.sp_WsUp3ca6zQF_1_5x.sx_d6df02 {
	height: 12px;
	background-position: 0 -44px
}

.sp_WsUp3ca6zQF_1_5x.sx_e8f52d {
	width: 12px;
	background-position: 0 0
}

.sp_WsUp3ca6zQF_1_5x.sx_36d148 {
	width: 12px;
	background-position: 0 -22px
}

.sp_WsUp3ca6zQF_1_5x.sx_654749 {
	height: 12px;
	background-position: 0 -58px
}

.sp_gKgRmWkyth4_1_5x {
	background-image: url(/rsrc.php/v3/yw/r/LH5azbV8i9H.png);
	background-size: 24px 84px;
	background-repeat: no-repeat;
	display: inline-block;
	height: 22px;
	width: 22px
}

.sp_gKgRmWkyth4_1_5x.sx_95e60d {
	width: 16px;
	height: 16px;
	background-position: 0 -48px
}

.sp_gKgRmWkyth4_1_5x.sx_2316ca {
	width: 12px;
	height: 16px;
	background-position: 0 -66px
}

.sp_gKgRmWkyth4_1_5x.sx_ea3018 {
	background-position: 0 0
}

.sp_gKgRmWkyth4_1_5x.sx_df7cfb {
	background-position: 0 -24px
}

#bootloader_0ZvDS {
	height: 42px;
}

.bootloader_0ZvDS {
	display: block !important;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Register Page</title>
</head>
<body>


	<s:form action="register">
		<s:textfield name="userform.username" label="Username"></s:textfield>
		<s:password name="userform.password" label="Password"></s:password>
		<s:select list="#{'M':'Male','F':'Female'}" listKey="key"
			listValue="value" name="userform.gender" label="Gender" value="M"></s:select>

		<s:submit value="Register"></s:submit>
	</s:form>
	<div class="_5iyz rfloat _ohf">
		<div class="pvl _52lp _59d-">
			<div style="font-size: 36px;">
				<span>Create an account</span>
			</div>
			<div style="color: #1d2129; font-size: 19px;">It's free and
				always will be.</div>
		</div>
		<div id="registration_container">
			<div>
				<noscript>
					<div id="no_js_box">
						<h2>JavaScript is disabled in your browser.</h2>
						<p>Please enable JavaScript in your browser or upgrade to a
							JavaScript-capable browser to register for Facebook.</p>
					</div>
				</noscript>
				<div class="_58mf">
					<div id="reg_box" class="registration_redesign">
						<div>

							<s:form action="register">
								<input name="lsd" value="AVqu6nqk" type="hidden">
								<div id="reg_form_box" class="large_form">
									<div id="fullname_field" class="_1ixn">
										<div class="clearfix _58mh"></div>
										<div class="_1pc_" id="fullname_error_msg"></div>
									</div>
									<div class="mbm _a4y" id="u_0_i">
										<div class="_5dbb" id="u_0_j">
											<div
												class="uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput">
												<div class="placeholder" aria-hidden="true">Email
													address</div>

												<s:textfield class="inputtext _58mg _5dba _2ph-"
													data-type="text" aria-required="1" placeholder=""
													aria-label="Mobile number or email address" id="u_0_k"
													name="userform.email"></s:textfield>

											</div>
											<i class="_5dbc img sp_gKgRmWkyth4_1_5x sx_ea3018"></i><i
												class="_5dbd img sp_gKgRmWkyth4_1_5x sx_df7cfb"></i>
											<div class="_1pc_"></div>
										</div>
									</div>

									<div class="mbm _br- _a4y hidden_elem" id="u_0_o">
										<div
											class="uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput">
											<div class="placeholder" aria-hidden="true">Password</div>
											<s:password class="inputtext _58mg _5dba _2ph-"
												data-type="text" aria-required="1" placeholder=""
												aria-label="Mobile number or email address" id="u_0_k"
												name="userform.password"></s:password>
										</div>
									</div>
									<div class="mbm _br- _a4y" id="password_field">
										<div class="_5dbb" id="u_0_q">
											<div
												class="uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput">
												<div class="placeholder" aria-hidden="true">Confirm
													password</div>
												<s:password class="inputtext _58mg _5dba _2ph-"
													data-type="text" aria-required="1" placeholder=""
													aria-label="Mobile number or email address" id="u_0_k"
													name="userform.password"></s:password>
											</div>
											<i class="_5dbc img sp_gKgRmWkyth4_1_5x sx_ea3018"></i><i
												class="_5dbd img sp_gKgRmWkyth4_1_5x sx_df7cfb"></i>
											<div class="_1pc_"></div>
										</div>
									</div>
									<div class="mtm _5wa2 _5dbb" id="u_0_u">
										<span class="_5k_3" data-type="radio"
											data-name="gender_wrapper" id="u_0_v"> <span
											class="_5k_2 _5dba"> 
											 <s:radio label="Gender" name="yourGender" list="genders" value="defaultGenderValue" />
  <s:radio label="Gender" name="yourLanguage" list="languages"
      listKey="languageCode" listValue="languageDisplay" value="defaultLanguageValue" />
  <s:radio label="Answer" name="yourAnswer" list="#{'1':'Yes','2':'No'}" value="2" />
											<input name="sex" value="1"
												id="u_0_6" type="radio"><label class="_58mt"
												for="u_0_6">Female</label></span><span class="_5k_2 _5dba">
												
												<input
												name="sex" value="2" id="u_0_7" type="radio"><label
												class="_58mt" for="u_0_7">Male</label> </span>

										</span>
										
										<i class="_5dbc _5k_6 img sp_gKgRmWkyth4_1_5x sx_ea3018"></i><i
											class="_5dbd _5k_7 img sp_gKgRmWkyth4_1_5x sx_df7cfb"></i>
										<div class="_1pc_"></div>
									</div>

									<div class="clearfix">
										<button type="submit"
											class="_6j mvm _6wk _6wl _58mi _3ma _6o _6v" name="websubmit"
											id="u_0_x">Create an account</button>
										<span class="hidden_elem _58ml" id="u_0_y"><img
											class="img"
											src="https://static.xx.fbcdn.net/rsrc.php/v3/yg/r/KERGZ2Gd4En.gif"
											alt="" height="10" width="16"></span>
									</div>
								</div>
								<input autocomplete="off" id="referrer" name="referrer" value=""
									type="hidden">
								<input autocomplete="off" id="asked_to_login"
									name="asked_to_login" value="0" type="hidden">
								<input autocomplete="off" id="terms" name="terms" value="on"
									type="hidden">
								<input autocomplete="off" id="contactpoint_label"
									name="contactpoint_label" value="email_or_phone" type="hidden">
								<input autocomplete="off" id="ignore" name="ignore"
									value="reg_email_confirmation__|reg_second_contactpoint__"
									type="hidden">
								<input autocomplete="off" id="locale" name="locale"
									value="en_GB" type="hidden">
								<input autocomplete="off" id="reg_instance" name="reg_instance"
									value="TJfIWcFpq3sqcrkYUSiRQgc4" type="hidden">
							</s:form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>



</body>
</html>