package org.francis.dh.admin.controller.post;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.common.utils.SecurityUtils;
import org.francis.dh.post.entity.Post;
import org.francis.dh.post.entity.dto.PostDto;
import org.francis.dh.post.entity.vo.PostAddVo;
import org.francis.dh.post.entity.vo.PostQueryVo;
import org.francis.dh.post.entity.vo.PostUpdateVo;
import org.francis.dh.post.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/post")
@Api(tags = "帖子接口")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 分页查询帖子
     * @param postQueryVo 查询参数
     * @return 帖子列表
     */
    @GetMapping("/page")
    //@PreAuthorize("@Perms.hasPerm('admin:post:list')")
    @ApiOperation("分页查询帖子")
    public RespResult getPostPage(@Valid PostQueryVo postQueryVo){
        IPage<PostDto> postList=postService.getPostPage(postQueryVo);
        return RespResult.ok().data("postList",postList);
    }

    /**
     * 根据id查询帖子
     * @param id 帖子id
     * @return 帖子信息
     */
    @GetMapping("/{id}")
    //@PreAuthorize("@Perms.hasPerm('admin:post:id')")
    @ApiOperation("根据id查询帖子")
    public RespResult getPostById(@RequestParam(name = "帖子id")@PathVariable Long id){
        PostDto post = postService.getPostById(id);
        if (post==null) {
            return RespResult.error().message("帖子不存在");
        }
        return RespResult.ok().data("post",post);
    }

    /**
     * 添加帖子
     * @param postAddVo 添加参数
     * @return 添加结果
     */
    @PostMapping("")
//    @PreAuthorize("@Perms.hasPerm('admin:post:add')")
    @ApiOperation("添加帖子")
    public RespResult addPost(@Valid @RequestBody PostAddVo postAddVo){
        if (postService.addPost(postAddVo)){
            return RespResult.ok().message("添加成功");
        }
        return RespResult.error().message("添加失败，请重试");
    }

    /**
     * 更新管理员帖子
     * @param postUpdateVo 更新参数
     * @return 更新结果
     */
    @PutMapping("")
//    @PreAuthorize("@Perms.hasPerm('admin:post:update')")
    @ApiOperation("更新管理员帖子")
    public RespResult updatePost(@Valid @RequestBody PostUpdateVo postUpdateVo){
        Post post = postService.getById(postUpdateVo.getId());
        if (post==null) {
            return RespResult.error().message("帖子不存在");
        }
        BeanUtils.copyProperties(postUpdateVo,post);
        if (postService.updateById(post)) {
            return RespResult.ok().message("更新成功");
        }
        return RespResult.error().message("更新失败，请重试");
    }

    /**
     * 置顶帖子
     * @param postId 帖子编号
     * @return 置顶结果
     */
    @PutMapping("/top/{postId}")
//    @PreAuthorize("@Perms.hasPerm('admin:post:top')")
    @ApiOperation("置顶帖子")
    public RespResult topPostByPId(@RequestParam(name = "帖子编号")@PathVariable Long postId){
        if (postService.getById(postId)==null) {
            return RespResult.error().message("帖子不存在");
        }
        LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Post::getId,postId)
                .set(Post::getIsTop,1);
        if (postService.update(updateWrapper)) {
            return RespResult.ok().message("置顶成功");
        }
        return RespResult.error().message("置顶失败,请重试");
    }

    /**
     * 删除帖子
     * @param id 帖子编号
     * @return 返回删除结果
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("@Perms.hasPerm('admin:post:top')")
    @ApiOperation("删除帖子")
    public RespResult deletePostByPId(@RequestParam(name = "帖子编号")@PathVariable Long id){
        if (postService.removeById(id)) {
            return RespResult.ok().message("删除成功");
        }
        return RespResult.error().message("删除失败，请重试");
    }
}

