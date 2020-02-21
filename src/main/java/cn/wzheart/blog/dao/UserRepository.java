package cn.wzheart.blog.dao;

import cn.wzheart.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wz
 * @date 2020-02-21 18:59
 * 用户
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameAndPassword(String userName, String password);
}
