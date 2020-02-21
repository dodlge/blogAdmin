package cn.wzheart.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wz
 * @date 2020-02-21 14:56
 * 评论
 */
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 内容
     */
    private String content;
    /**
     * 头像链接
     */
    private String avatra;

    @Temporal(TemporalType.TIMESTAMP)// 数据库时间类型
    private Date createTime;

    @ManyToOne()
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> repltComments = new ArrayList<>();

    /**
     * 一个子类对应一个父类
     */
    @OneToOne
    private Comment parentComment;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatra() {
        return avatra;
    }

    public void setAvatra(String avatra) {
        this.avatra = avatra;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }


    public List<Comment> getRepltComments() {
        return repltComments;
    }

    public void setRepltComments(List<Comment> repltComments) {
        this.repltComments = repltComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatra='" + avatra + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
