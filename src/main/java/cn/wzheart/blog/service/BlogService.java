package cn.wzheart.blog.service;

import cn.wzheart.blog.dao.BlogRepository;
import cn.wzheart.blog.entity.Blog;
import cn.wzheart.blog.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wz
 * @date 2020-02-23 14:18
 * 博客
 */
@Service
@Transactional
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 获取单个blog
     * @param id
     * @return
     */
    public Blog getBlog(Long id){
        Blog blog = blogRepository.findById(id).get();
        return blog;
    }

    /**
     * 分页查询获取list
     */
    public Page<Blog> listBlog(Pageable pageable,Blog blog){
        Page<Blog> all = blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                // 判断 标题 分类 是否推荐
                if (null != blog.getTitle() && !"".equals(blog.getTitle())){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }

                if (null != blog.getType().getId() ){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }

                if (blog.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
        return all;
    }

    /**
     * 保存or更新
     */
    public Blog saveBlog(Blog blog){
        return blogRepository.save(blog);
    }

    /**
     * 删除
     */
    public void deleteBlog(Long id){
        blogRepository.deleteById(id);
    }
}
