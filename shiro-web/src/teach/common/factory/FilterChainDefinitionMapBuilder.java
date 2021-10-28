package teach.common.factory;

import java.util.LinkedHashMap;

/**
 * 权限类，实际开发中从数据库读取
 */
public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		/*map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");

		map.put("/**", "authc");shiro-web/index.jsp*/


//	配置哪些页面需要受保护.
//	以及访问这些页面需要的权限.
//		 anon 可以被匿名访问
//		 authc 必须认证(即登录)后才可能访问的页面.
//		 logout 登出.
//		 roles 角色过滤器


		map.put("/login.jsp", "anon");
		map.put("/index.jsp", "anon");
		map.put("/user.jsp", "authc , roles[user]");
		map.put("/admin.jsp", "authc , roles[admin]");
		map.put("/logout", "logout");


		map.put("/index/login.do", "anon");
		map.put("/**", "authc");


		return map;
	}
	
}
