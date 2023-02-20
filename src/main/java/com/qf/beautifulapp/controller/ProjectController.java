package com.qf.beautifulapp.controller;

import com.qf.beautifulapp.dto.ProjectDto;
import com.qf.beautifulapp.entity.Project;
import com.qf.beautifulapp.result.ResponseCode;
import com.qf.beautifulapp.result.ResponseData;
import com.qf.beautifulapp.service.ProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Project)表控制层
 *
 * @author makejava
 * @since 2022-04-27 16:18:53
 */
@RestController
@RequestMapping("project")
public class ProjectController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectService projectService;

    /**
     * 分页查询
     *
     * @param project     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Project>> queryByPage(Project project, PageRequest pageRequest) {
        return ResponseEntity.ok(this.projectService.queryByPage(project, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Project> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.projectService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param project 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Project> add(Project project) {
        return ResponseEntity.ok(this.projectService.insert(project));
    }

    /**
     * 编辑数据
     *
     * @param project 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Project> edit(Project project) {
        return ResponseEntity.ok(this.projectService.update(project));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.projectService.deleteById(id));
    }

    @ApiOperation(value = "通过项目类型查找项目列表",notes = "项目列表数据")
    @ApiImplicitParam(name="name",value = "项目的类型")
    @GetMapping("/app/nav/{name}")
    public ResponseData queryProjects(@PathVariable("name") String name){
        List<ProjectDto> projects =  projectService.queryByType(name);
        return new ResponseData(projects);
    }


    @ApiOperation(value = "通过点击项目列表中的元数，跳转倒项目详情页",notes="某个项目详情")
    @ApiImplicitParam(name="id",value = "项目的ID")
    @GetMapping("/app/detail/{id}")
    public ResponseData queryProInfo(@PathVariable("id")Integer id){
        ProjectDto projectDto = projectService.queryInfoById(id);
        return new ResponseData(projectDto);
    }
}

