/**
 * 定义包的种类
 */
package com.qq.common;

public interface MessageType {
	String message_want_login="请求登录的包";  
	String message_succeed="表明登陆成功";  
	String message_login_fail = "表明登录失败"; 
	String message_comm_mes="普通消息包";
	String message_get_onLineFriend="要求在线好友的包";
	String message_res_onLineFriend="返回在线好友的包";
	String message_allowed_login="允许登录";
	String message_server_close="要求关闭服务器的包";
	String message_server_will_close="服务器将要关闭";
	String message_client_will_close="客户端将要关闭";
	String message_client_active_close="客户端主动关闭";
	String message_register="申请注册";
	String message_register_success="注册成功";
	String message_register_fail="注册失败";
}
