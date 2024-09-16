package com.token.controller.admin;

import com.token.dto.EmployeeLoginDTO;
import com.token.result.Result;
import com.token.service.EmployeeService;
import com.token.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation(value = "员工登陆")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody @Validated(value = EmployeeLoginDTO.Group.class) EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登陆:{}",employeeLoginDTO);
        EmployeeLoginVO employeeLoginVO =  employeeService.login(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }

    // TODO 更新新增员工功能
}