package com.qf.beautifulapp.controller;

import com.qf.beautifulapp.entity.Image;
import com.qf.beautifulapp.result.ResponseCode;
import com.qf.beautifulapp.result.ResponseData;
import com.qf.beautifulapp.service.ImageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Image)表控制层
 *
 * @author makejava
 * @since 2022-04-27 16:11:40
 */
@RestController
@RequestMapping("image")
public class ImageController {
    /**
     * 注入服务层对象
     */
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "根据图片类型查找图片",notes = "轮播图和导航图数据获取")
    @ApiImplicitParam(name="imagetype",value = "图片类型")
    @GetMapping("/app/imagetype/{imagetype}")
    /*控制层的返回值类型都被封装为ResponseData*/
    public ResponseData queryImages(@PathVariable("imagetype") String imagetype){
        List<Image> images = imageService.queryByType(imagetype);
        return new ResponseData(ResponseCode.SUCCESS, images);
    }

}

