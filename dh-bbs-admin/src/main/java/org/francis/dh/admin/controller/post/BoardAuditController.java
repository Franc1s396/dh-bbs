package org.francis.dh.admin.controller.post;


import io.swagger.annotations.Api;
import org.francis.dh.post.service.BoardAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author francis
 * @since 2022-06-19
 */
@RestController
@RequestMapping("/board/audit")
@Api(tags = "板块审核接口")
public class BoardAuditController {
    @Autowired
    private BoardAuditService boardAuditService;
}

