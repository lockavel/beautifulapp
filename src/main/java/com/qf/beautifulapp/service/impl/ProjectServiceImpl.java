package com.qf.beautifulapp.service.impl;

import com.qf.beautifulapp.dao.ProjecttypeDao;
import com.qf.beautifulapp.dto.ProjectDto;
import com.qf.beautifulapp.entity.Project;
import com.qf.beautifulapp.dao.ProjectDao;
import com.qf.beautifulapp.result.ResponseData;
import com.qf.beautifulapp.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2022-04-27 16:18:54
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    /*注入dao对象*/
    @Resource
    private ProjectDao projectDao;
    /*注入projecttype的dao对象*/
    @Resource
    private ProjecttypeDao projecttypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Project queryById(Long id) {
        return this.projectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param project     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Project> queryByPage(Project project, PageRequest pageRequest) {
        long total = this.projectDao.count(project);
        return new PageImpl<>(this.projectDao.queryAllByLimit(project, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project insert(Project project) {
        this.projectDao.insert(project);
        return project;
    }

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project update(Project project) {
        this.projectDao.update(project);
        return this.queryById(project.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.projectDao.deleteById(id) > 0;
    }
    /*
    * 通过数据名称去数据库中找项目类表
    * 数据库中project表只有typeid
    * 先通过类型的name去找到对应类型的id
    * 在通过类型的id去project表中表相关的项目
    * */
    @Override
    public List<ProjectDto> queryByType(String name) {
        if (name != null && !name.equals("")) {
            List<ProjectDto> projects = null;
            if (name.equals("推荐")) {
                projects = projectDao.queryAll();
            } else {
                projects = projectDao.queryByTypeName(name);
            }
            return projects;
        }
        return null;
    }

    @Override
    public ProjectDto queryInfoById(Integer id) {
        if(id != null && id>0){
            ProjectDto projectDto = projectDao.queryInfoById(id);
            return projectDto;
        }
        return null;
    }
}
