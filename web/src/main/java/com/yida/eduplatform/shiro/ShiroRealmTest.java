package com.yida.eduplatform.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroRealmTest {
    @Test
    public void test(){
//        ShiroRealm shiroRealm = new ShiroRealm();
//
//        //构建SecurityManager环境
//        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(shiroRealm);
//
//        //以下为密码加密
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5"); //算法名称
//        matcher.setHashIterations(1);   //加密次数
//        shiroRealm.setCredentialsMatcher(matcher);
//
//        SecurityUtils.setSecurityManager(defaultSecurityManager);
//        Subject subject = SecurityUtils.getSubject();

        //输出用Md5加密且盐为admin的密码
        Md5Hash md5Hash = new Md5Hash("998888","1010");
        System.out.println(md5Hash.toString());

        //设置凭证
//        UsernamePasswordToken token = new UsernamePasswordToken("1008","123456");
//        subject.login(token);
//
//        System.out.println(subject.isAuthenticated());
//
//        subject.checkRole("admin");
//        subject.checkPermissions("user:add","user:delete");

    }

}
