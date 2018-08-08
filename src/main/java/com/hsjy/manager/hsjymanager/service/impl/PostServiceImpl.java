package com.hsjy.manager.hsjymanager.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsjy.manager.hsjymanager.dao.PostDao;
import com.hsjy.manager.hsjymanager.dao.UserPostDao;
import com.hsjy.manager.hsjymanager.entity.Post;
import com.hsjy.manager.hsjymanager.service.PostService;
import com.hsjy.manager.hsjymanager.utils.Convert;
import com.hsjy.manager.hsjymanager.utils.MakeUUID;
import com.hsjy.manager.hsjymanager.utils.constant.CodeConstants;
import com.hsjy.manager.hsjymanager.utils.exception.AuthException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.service.impl
 * @ClassName: PostServiceImpl
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/8 9:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/8 9:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostDao,Post> implements PostService {

    @Resource
    private UserPostDao userPostDao;

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<Post> selectPostList(Post post) {
        return baseMapper.selectPostList(post);
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<Post> selectPostAll() {
        return baseMapper.selectPostAll();
    }

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    @Override
    public List<Post> selectPostsByUserId(String userId) {
        List<Post> userPost = baseMapper.selectPostsByUserId(userId);
        List<Post> allPost = baseMapper.selectPostAll();
        for (Post post: allPost) {
            for (Post userRole : userPost ){
                if (post.getPostId().equals(userRole.getPostId())){
                    post.setFlag(true);
                    break;
                }
            }
        }
        return allPost;
    }


    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public Post selectPostById(String postId) {

        return baseMapper.selectPostById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deletePostByIds(String ids) throws Exception {
        String[] postIds = Convert.toStrArray(ids);
        for (String postid : postIds){
            Post post =  selectPostById(postid);
            if (countUserPostById(postid) > 0){
                throw new AuthException("已经分配好权限不用需删除", CodeConstants.DELETE_EXCEPTION);
            }
        }
        return baseMapper.deletePostByIds(postIds);
    }

    /**
     * 新增当前用户
     * @param post 岗位信息
     * @return
     */
    @Override
    public int insertPost(Post post) {
        post.setPostId(MakeUUID.makerandomuuid());
        post.setCreateBy("获取当前用户");

        return baseMapper.insertPost(post);
    }

    /**
     * 更新当前用户信息
     * @param post 岗位信息
     * @return
     */
    @Override
    public int updatePost(Post post) {
        post.setUpdateBy("获取当前登录用户");
        return baseMapper.updatePost(post);
    }

    @Override
    public int countUserPostById(String postId) {
        return userPostDao.countUserPostById(postId);
    }
}
