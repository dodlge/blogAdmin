package cn.wzheart.blog.service;

import cn.wzheart.blog.NotFoundException;
import cn.wzheart.blog.dao.TypeRepository;
import cn.wzheart.blog.entity.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author wz
 * @date 2020-02-22 15:21
 */
@Service
@Transactional
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public Type saveType(Type type){
        return typeRepository.save(type);
    }

    public Type getType(Long id){
        Optional<Type> o = typeRepository.findById(id);
        Type type = o.get();
        return type;
    }

    public Page<Type> listType(Pageable pageable){
        Page<Type> all = typeRepository.findAll(pageable);
        return all;
    }

    /**
     * 获取所有的Types
     */
    public List<Type> getAll(){
        List<Type> all = typeRepository.findAll();
        return all;
    }

    public Type updateType(Long id,Type type){
        Type t = typeRepository.getOne(id);
        if (null == t){
            throw new NotFoundException("不存在此id");
        }
        // 把type的值复制到t
        BeanUtils.copyProperties(type,t);
        // 只要保存的有id，就会更新
        return typeRepository.save(t);
    }

    public void deleteType(Long id){
        typeRepository.deleteById(id);
    }

    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
