package com.qf.beautifulapp.controller;

import com.qf.beautifulapp.entity.Business;
import com.qf.beautifulapp.result.ResponseData;
import com.qf.beautifulapp.service.BusinessService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Business)表控制层
 *
 * @author makejava
 * @since 2022-04-27 16:21:42
 */
@RestController
@RequestMapping("business")
public class BusinessController {

    /**
     * 服务对象
     */
    @Resource
    private BusinessService businessService;

    /**
     * 分页查询
     *
     * @param business    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Business>> queryByPage(Business business, PageRequest pageRequest) {
        return ResponseEntity.ok(this.businessService.queryByPage(business, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Business> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.businessService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param business 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Business> add(Business business) {
        return ResponseEntity.ok(this.businessService.insert(business));
    }

    /**
     * 编辑数据
     *
     * @param business 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Business> edit(Business business) {
        return ResponseEntity.ok(this.businessService.update(business));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.businessService.deleteById(id));
    }


    @ApiOperation(value = "通过项目id查找商家名称",notes="通过项目id查找商家名称")
    @ApiImplicitParam(name="id",value = "项目的ID")
    @GetMapping("/app/busName/{id}")
    public ResponseData queryProInfo(@PathVariable("id")Integer id){
        String busname = businessService.queryNameByPid(id);
        return new ResponseData(busname);
    }
}

