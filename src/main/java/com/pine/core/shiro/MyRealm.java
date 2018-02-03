package com.pine.core.shiro;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.pine.core.util.PineConst;
import com.pine.moudles.sys.model.entity.auth.User;
import com.pine.moudles.sys.service.auth.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class MyRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
		User user = SecurityUtil.getCurrentUser();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        simpleAuthorInfo.addRoles(getRoles(user));
        simpleAuthorInfo.addStringPermissions(getPermCodes(user));
        return simpleAuthorInfo;
    }
	
	/**
     * 获取权限，string存放的是权限编码
     * @param user
     * @return
     */
    private List<String> getPermCodes(User user) {

        List<String> perms = new ArrayList<String>();
//        List<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            List<Permission> _perms = role.getPermissions();
//            for (Permission _perm : _perms) {
//                perms.add(_perm.getPermCode());
//            }
//        }
        return perms;
    }


    /**
     * 获取角色集合，string存放的角色名称
     * @param user
     * @return
     */
    private List<String> getRoles(User user) {

        List<String> roles = new ArrayList<String>();
//        for (Role role : user.getRoles()) {
//            roles.add(role.getRoleName());
//        }
        return roles;
    }
    
    /**
     *  认证回调函数,登录时调用.
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
    	String username = (String) authenticationToken.getPrincipal();
        User user = userService.selectOne(new EntityWrapper<User>().where("username={0}",username));
        // 查出是否有此用户
        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        if (Boolean.TRUE.equals(user.getStatus().equals(PineConst.STATUS_IS_UNUSE))) {
            throw new LockedAccountException(); // 帐号锁定
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user
                .getPassword(), user.getNickname());
        setSessionInfo(user);
        return authenticationInfo;
    }
    
    /**
     * 存放一些信息到session中，便于获取，可以通过httpsession获取相应的信息
     * @param user
     */
    private void setSessionInfo(User user){
        Subject sub = SecurityUtils.getSubject();
        Session session = sub.getSession();

        //显示的设置权限和角色，避免下次再去数据库获取，提高效率
//        List<Role> roles = user.getRoles();
//        for (int i = 0; i < roles.size(); i++) {
//            Role role = roles.get(i);
//            List<Permission> perms = role.getPermissions();
//            role.setPermissions(perms);
//            roles.set(i,role);
//        }
//        user.setRoles(roles);
        session.setAttribute(SecurityUtil.CURRENT_USER, user);
    }
}
