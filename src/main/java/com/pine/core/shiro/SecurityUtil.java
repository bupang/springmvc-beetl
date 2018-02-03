package com.pine.core.shiro;

import com.pine.moudles.sys.model.entity.auth.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 用户登录信息工具类
 * @author chen
 *
 */
public class SecurityUtil {
	public static final String CURRENT_USER = "CURRENT_USER";
	public static final int USER_STATUS_LOCKED = 0;//用户状态：停用（锁定）
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static User getCurrentUser(){
        Subject sub = SecurityUtils.getSubject();
        Session session = sub.getSession();
        User user = (User) session.getAttribute(CURRENT_USER);
        return user ;
    }
}
