package org.francis.dh.admin.controller.post;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.dh.common.core.entity.RespResult;
import org.francis.dh.post.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/post/like")
@Api(tags = "帖子点赞接口")
public class PostLikeController {
    @Autowired
    private PostLikeService postLikeService;

    @GetMapping("/total")
    @ApiOperation("获取帖子点赞数")
    public RespResult getPostLikeTotalByPid(Long postId){
        Integer postLikeTotal=postLikeService.getPostLikeTotalByPid(postId);
        return RespResult.ok().data("postLikeTotal",postLikeTotal);
    }
}

