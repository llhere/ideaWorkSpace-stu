package teach.code.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teach.code.dao.user.UserDao;
import teach.code.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User selectUser(String userName){
        return userDao.selectUser(userName);
    }


}
