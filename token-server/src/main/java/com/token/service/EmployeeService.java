package com.token.service;

import com.token.dto.EmployeeLoginDTO;
import com.token.entity.Employee;
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
}
