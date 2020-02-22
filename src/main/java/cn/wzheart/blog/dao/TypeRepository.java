package cn.wzheart.blog.dao;

import cn.wzheart.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author wz
 * @date 2020-02-22 15:24
 * 分类
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
