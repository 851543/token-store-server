package com.token.controller.admin;

import com.token.constant.MessageConstant;
import com.token.dto.EmployeeLoginDTO;
import com.token.dto.EmployeePageQueryDTO;
import com.token.entity.Employee;
import com.token.result.PageResult;
import com.token.result.Result;
import com.token.service.EmployeeService;
import com.token.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation(value = "员工登陆")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody @Validated(value = EmployeeLoginDTO.Group.class) EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登陆:{}", employeeLoginDTO);
        EmployeeLoginVO employeeLoginVO = employeeService.login(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "新增员工")
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody @Validated(value = Employee.Group.class) Employee employee) {
        log.info("新增员工:{}", employee);
        employeeService.insert(employee);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改员工
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "修改员工")
    @PostMapping("/update")
    public Result<String> update(@RequestBody Employee employee) {
        log.info("修改员工:{}", employee);
        employeeService.update(employee);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }


    /**
     * 删除员工-可批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除员工")
    @DeleteMapping("/delete")
    public Result<String> delete(@RequestParam Long[] ids) {
        log.info("删除员工:{}", ids);
        employeeService.delete(ids);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 修改员工状态
     * @param status
     * @param id
     * @return
     */
    @ApiOperation(value = "修改员工状态")
    @PostMapping("/status/{status}")
    public Result<String> status(@PathVariable Long status, Long id) {
        log.info("修改{}员工状态:{}", id, status);
        employeeService.status(id, status);
        return Result.success(MessageConstant.OPERATE_SUCCESS);
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @ApiOperation(value = "员工分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询:{}", employeePageQueryDTO);
        PageResult pageList = employeeService.page(employeePageQueryDTO);
        return Result.success(pageList);
    }
}