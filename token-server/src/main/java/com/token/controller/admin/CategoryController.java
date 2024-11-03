package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.CategoryDTO;
import com.token.dto.CategoryPageQueryDTO;
import com.token.entity.Category;
import com.token.result.PageResult;
import com.token.result.Result;
import com.token.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增分类")
    public Result<String> insert(@RequestBody @Validated CategoryDTO categoryDTO) {
        log.info("新增分类{}", categoryDTO);
        categoryService.insert(categoryDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 根据id删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除分类")
    public Result<String> delete(Long[] ids) {
        log.info("删除了分类{}",ids);
        categoryService.delete(ids);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 根据id修改分类信息
     * @param categoryDTO
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改分类信息")
    public Result<String> update(CategoryDTO categoryDTO) {
        log.info("修改了分类{}",categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询分类信息")
   public Result<Category> personalInfo(@PathVariable Long id){
        log.info("查询分类信息{}",id);
        return Result.success(categoryService.getCategoryInfo(id));
   }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
   @GetMapping("/page")
   @ApiOperation(value = "分类分页查询")
   public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询:{}",categoryPageQueryDTO);
        return Result.success(categoryService.page(categoryPageQueryDTO));
   }

    /**
     * 修改分类状态
     * @param status
     * @param id
     * @return
     */
   @ApiOperation(value = "修改分类状态")
   @PostMapping("/status/{status}")
   public Result<String> status(@PathVariable Long status, Long id){
       log.info("修改{}分类状态:{}",id ,status);
       categoryService.status(id, status);
       return Result.success(MessageConstant.OPERATE_SUCCESS);
   }
}
