package com.hsjy.manager.hsjymanager.controller;

import com.hsjy.manager.hsjymanager.entity.Post;
import com.hsjy.manager.hsjymanager.service.PostService;
import com.hsjy.manager.hsjymanager.utils.result.Result;
import com.hsjy.manager.hsjymanager.utils.result.ResultGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: hsjymanager
 * @Package: com.hsjy.manager.hsjymanager.controller
 * @ClassName: PostController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/8/8 9:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/8 9:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("post")
public class PostController {
    private String prefix = "system/post";

    @Autowired
    private PostService postService;

    /**
     * 页面的跳转
     * @return
     */
    @GetMapping("/jump")
    public String pageJump(){
        return prefix+"/post";
    }

    /**\
     * 查询岗位
     * @param post
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<List<Post>> getList(Post post){
        return ResultGenerator.genSuccessResult(postService.selectPostList(post));
    }

    /**
     * 删除用户
     * @param ids
     * @return
     * @throws Exception
     */
    @GetMapping("/removePost")
    @ResponseBody
    public Result removePost(String ids) throws Exception {
        return ResultGenerator.genSuccessResult(postService.deletePostByIds(ids));
    }

    /**
     * 新增岗位
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    /**
     * 新增部门
     * @param post
     * @return
     */
    @PostMapping("insertPost")
    @ResponseBody
    public Result insertPost(Post post){
        return ResultGenerator.genSuccessResult(post);
    }

    /**
     * 修改岗位
     */
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") String postId, ModelMap mmap)
    {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result editSave(Post post)
    {
        return ResultGenerator.genSuccessResult(postService.updatePost(post));
    }

}
