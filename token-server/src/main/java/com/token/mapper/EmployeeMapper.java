package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.dto.EmployeePageQueryDTO;
import com.token.entity.Employee;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工数据
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工数据
     *
     * @param employee
     */
    @Insert("insert into employee(name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) values" +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    void insert(Employee employee);

    /**
     * 修改员工数据
     * @param employee
     */
    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 删除员工数据
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 查询员工数据集合
     * @param employeePageQueryDTO
     * @return
     */
    List<Employee> queryList(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 查询员工数据
     *
     * @param id
     */
    @Select("select * from employee where id = #{id}")
    void getEmployeeById(Long id);
}
