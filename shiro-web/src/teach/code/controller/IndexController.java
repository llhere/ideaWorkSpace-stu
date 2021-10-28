package teach.code.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import teach.code.model.User;
import teach.common.jwtUtil.*;

/**
 * 主页面
 */
@Controller
@RequestMapping("index")
public class IndexController extends ExceptionController{

    /**
     * shiro登陆页面
     */
    //@RequiresRoles({"admin","user"})  有admin和user角色的账户才可访问的方法
    @RequestMapping("login.do")
    @ResponseBody
    public Result goLogin(String username, String password) {

        //获取shiro的subject
        Subject subject = SecurityUtils.getSubject();
        String jwt = "";
        //判断是否被验证 没被验证则执行登陆
        if (!subject.isAuthenticated()) {
            jwt  = new TokenUtil().creatToken("123","user");
            JwtToken jwtToken = new JwtToken(jwt);
            //登陆
            try {
                subject.login(jwtToken);
                User user = new User();
                user.setAccount("0ec2e7133879403f8854cd48ba13256c");
                user.setId(1);
                TokenMap.getInstance().put(jwt, user);
            } catch (AuthenticationException e) {
                //登陆异常
                System.err.println("登陆失败");
                e.printStackTrace();
            }
        }

        return new Result(ResultStatus.SUCCESS,jwt);
    }


    @RequestMapping("add.do")
    @ResponseBody
    public Result goLogin(@RequestHeader("Authorization") String token) {
        return new Result(ResultStatus.SUCCESS,TokenMap.getInstance().get(token));
    }


}