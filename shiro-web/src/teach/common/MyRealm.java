package teach.common;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import teach.code.model.User;
import teach.code.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //强转成UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //从token中获取name
        String username = token.getUsername();
        System.err.println(token.getPassword());

        //查询username对应的记录
        User user = userService.selectUser(username);

        //若用户不存在则抛出UnknownAccountException异常
        if(null == user)
            //throw new UnknownAccountException().printStackTrace();
            System.err.println("用户不存在");

        //根据情况判断是否抛出其他的AuthenticationException,比如状态值为1则此账号为锁定状态
        else if("4".equals(user.getIdentity()))
            //throw new AuthenticationExceptio().printStackTrace();
            System.err.println("用户被锁定");

       String password = new SimpleHash("SHA1", user.getPassword(),  null, 1024).toHex();

        //构建AuthenticationInfo对象返回,通常实现类为SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());

        return info;
    }

    //shiro回调执行的方法  shiro权限角色验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登陆信息
        Object principal = principalCollection.getPrimaryPrincipal();

        //模拟角色（权限）添加到set集合
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if("admin".equals(principal)){   //模拟admin权限登陆，即有user和admin角色（权限）
            roles.add("admin");
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);


        return info;
    }
}
