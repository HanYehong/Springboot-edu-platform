package com.yida.eduplatform.shiro;

import com.yida.eduplatform.domain.Student;
import com.yida.eduplatform.domain.Teacher;
import com.yida.eduplatform.repository.StudentRepository;
import com.yida.eduplatform.repository.TeacherRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private HashMap<String,String> map;

    {
        map = new HashMap<>();
        map.put("1008","e10adc3949ba59abbe56e057f20f883e");
    }

    @Autowired
    private void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Autowired
    private void setTeacherRepository(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object obj = principalCollection.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();
        if(obj instanceof String){
            roles.add("admin");
        }else if(obj instanceof Student){
            roles.add("student");
        }else{
            roles.add("teacher");
        }
//        Set<String> permission = getPermissionByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setStringPermissions(permission);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

//    private Set<String> getPermissionByUsername(String username) {
//        Set<String> sets = new HashSet<>();
//        sets.add("user:delete");
//        sets.add("user:add");
//        return sets;
//    }

    private Set<String> getRoleByUsername(String username) {
        Set<String> sets = new HashSet<>();
        if(username.equals("admin")){
            sets.add("admin");
        }else if(username.substring(0,1).equals("2")){
            sets.add("teacher");
        }else{
            sets.add("student");
        }
        return sets;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal(); //获得用户名
    //    String password = getPasswordByUsername(username);
        System.out.println("doGetAuthentictionInfo():::username:"+username);
        if(username.equals("admin")){
            System.out.println("doGetAuthentictionInfo()====admin");
            ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    username,"2268e314735438ffff5020c88ed1fade",credentialsSalt,getName());
            return simpleAuthenticationInfo;
        }
        Object obj = getByUsername(username);
 //       Student student = studentRepository.findByStuId(Integer.parseInt(username));
        if(obj == null){
            System.out.println("doGetAuthentictionInfo()====null");
            throw new AccountException("用户名不正确");
        }else if(obj instanceof Student){
            System.out.println("doGetAuthentictionInfo()====student");
            ByteSource credentialsSalt = ByteSource.Util.bytes(((Student) obj).getStuId()+"");
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    obj, ((Student)obj).getStuPassword(),credentialsSalt,getName());
            return simpleAuthenticationInfo;
        }else{
            System.out.println("doGetAuthentictionInfo()====teacher");
            ByteSource credentialsSalt = ByteSource.Util.bytes(((Teacher)obj).getTeaId()+"");
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                    obj,((Teacher) obj).getTeaPassword(),credentialsSalt,getName());
            return simpleAuthenticationInfo;
        }
    }

    private Object getByUsername(String username) {
        System.out.println("getByUsername():::username" + username);
        Student student = studentRepository.findByStuId(Integer.parseInt(username));
        if (student == null) {
            Teacher teacher = teacherRepository.findByTeaId(Integer.parseInt(username));
            if (teacher == null) {
                return null;
            } else {
                return teacher;
            }
        } else {
            return student;
        }
    }
}
