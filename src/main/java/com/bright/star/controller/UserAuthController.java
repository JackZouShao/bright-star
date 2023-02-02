package com.bright.star.controller;

import com.bright.star.service.UserAuthService;
import com.bright.star.entity.UserAuth;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bright.star.common.config.PageResult;
import com.bright.star.common.config.result.R;
import com.bright.star.model.dto.UserAuthDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.collection.CollectionUtil;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
* @since 2023-02-02
*/

@Slf4j
@Api(tags = "")
@RestController
@RequestMapping("/userAuth")
        public class UserAuthController {

        @Autowired
        private UserAuthService userAuthService;


        @ApiOperation(value = "新增")
        @PostMapping("")
        public R save(@RequestBody UserAuthDTO userAuthDTO){
        var userAuth = BeanUtil.copyProperties(userAuthDTO, UserAuth.class);
        userAuthService.save(userAuth);
        return R.success();
        }

        @ApiOperation(value = "根据id删除")
        @DeleteMapping("/{id}")
        public R delete(@PathVariable("id") String id){
        userAuthService.removeById(id);
        return R.success();
        }

        @ApiOperation(value = "根据id修改")
        @PutMapping("/{id}")
        public R update(@PathVariable("id") String id, @RequestBody UserAuthDTO userAuthDTO){
        var userAuth = BeanUtil.copyProperties(userAuthDTO, UserAuth.class);
        userAuthService.updateById(userAuth);
        return R.success();
        }


        @ApiOperation(value = "条件查询")
        @PostMapping("/pageQuery")
        public R list(@RequestBody UserAuth userAuth){
        List<UserAuth> userAuthList = userAuthService.list(new QueryWrapper<>(userAuth));
        return R.success(userAuthList);
        }

        @ApiOperation(value = "查询所有")
        @GetMapping("")
        public R list() {
        var userAuthList = userAuthService.list(new QueryWrapper<>());
        var userAuthDTOList = BeanUtil.copyToList(userAuthList, UserAuthDTO.class);
        return R.success(userAuthDTOList);
        }

        @ApiOperation(value = "列表（分页）")
        @GetMapping("/list")
        public R list(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        var page = userAuthService.page(new Page<>(pageNum, pageSize), null);
        return R.success(new PageResult<>(page.getTotal(),page.getRecords()));
        }

        @ApiOperation(value = "详情")
        @GetMapping("/{id}")
        public R get(@PathVariable("id") String id){
        var userAuth= userAuthService.getById(id);
        if ( userAuth  != null) {
        var userAuthDTO = BeanUtil.copyProperties(userAuth, UserAuthDTO.class);
        return R.success(userAuthDTO);
        }
        return R.fail(404,"找不到实体,id:"+ id);
        }
    }
