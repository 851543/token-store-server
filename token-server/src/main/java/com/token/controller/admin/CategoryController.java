package com.token.controller.admin;

import com.token.annotation.Id;
import com.token.constant.MessageConstant;
import com.token.dto.CategoryDTO;
import com.token.dto.CategoryPageQueryDTO;
import com.token.dto.GoodsDTO;
import com.token.entity.Category;
import com.token.result.PageResult;
import com.token.result.Result;
import com.token.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类")
@Slf4j
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @return
     */
    @PreAuthorize("@ss.hasPermi('admin:category:insert')")
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
    @PreAuthorize("@ss.hasPermi('admin:category:delete')")
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
    @PreAuthorize("@ss.hasPermi('admin:category:update')")
    @PostMapping("/update/{id}")
    @ApiOperation(value = "修改分类信息")
    public Result<String> update(@PathVariable Long id, CategoryDTO categoryDTO) {
        log.info("修改了{}分类：{}",id,categoryDTO);
        categoryService.update(categoryDTO,id);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 根据id查询分类信息
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('admin:category:select')")
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
   @PreAuthorize("@ss.hasPermi('admin:category:select')")
   @GetMapping("/page")
   @ApiOperation(value = "分类分页查询")
   public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询:{}",categoryPageQueryDTO);
        return Result.success(categoryService.page(categoryPageQueryDTO));
   }

    /**
     * 修改分类状态
     * @param status
     * @param Id
     * @return
     */
   @PreAuthorize("@ss.hasPermi('admin:category:update')")
   @ApiOperation(value = "修改分类状态")
   @PostMapping("/status/{status}")
   public Result<String> status(@PathVariable Long status,@RequestParam @Id(message = "id为空") Long Id){
       log.info("修改{}分类状态:{}",Id ,status);
       categoryService.status(Id, status);
       return Result.success(MessageConstant.OPERATE_SUCCESS);
   }
}
