package cn.wzheart.blog.service;

import cn.wzheart.blog.dao.UserRepository;
import cn.wzheart.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wz
 * @date 2020-02-21 18:57
 * 用户
 */
@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;
    /**
     * 检查用户账号密码
     * @param userName 账号
     * @param password 密码
     */
    public User checkUser(String userName, String password){
        User user = userRepository.findByUserNameAndPassword(userName,password);
        return user;
    }
}
