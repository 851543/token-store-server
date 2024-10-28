package com.token.service;

import com.token.dto.EmployeeLoginDTO;
import com.token.dto.EmployeePageQueryDTO;
import com.token.entity.Employee;
import com.token.result.PageResult;
import com.token.vo.EmployeeLoginVO;

public interface EmployeeService {

    /**
     * 员工登陆
     * @param employeeLoginDTO
     * @return
     */
    EmployeeLoginVO login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    void update(Employee employee);

    /**
     * 删除员工
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 修改员工状态
     * @param status
     */
    void status(Long id, Long status);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 查询员工信息
     * @param id
     */
    Employee getEmployeeInfo(Long id);
}
