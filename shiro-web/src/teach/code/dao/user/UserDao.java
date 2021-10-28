package teach.code.dao.user;

import org.springframework.stereotype.Repository;


import teach.code.model.User;
@Repository
public interface  UserDao{

	/**
	 * 根据用户名查询用户
	 * @return
	 */
	User selectUser(String userName);
	

}
