package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.CategoryDTO;
import com.token.dto.CategoryPageQueryDTO;
import com.token.entity.Category;
import com.token.result.PageResult;
import com.token.result.Result;
import com.token.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    // TODO Mapper修改需要完善
    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除分类")
    public Result<String> delete(Integer id) {
        log.info("删除了分类{}",id);
        categoryService.delete(id);
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
}
