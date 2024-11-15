package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.dto.SetmealQueryDTO;
import com.token.entity.Setmeal;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 新增套餐
     * @param setmeal
     */
    @Insert("insert into setmeal(category_id, name, price, status, description, image, create_time, update_time, create_user, update_user) values " +
            "(#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Setmeal setmeal);

    /**
     * 根据id修改套餐
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);


    /**
     * 删除套餐
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 根据id获取套餐详情
     * @param id
     * @return
     */
    @Select("select * from setmeal where id = #{id};")
    Setmeal getByIdSetmea(Long id);
    /**
     * 根据id查询启用的套餐
     * @param ids
     * @return
     */
    List<Long> getEnableStatusByIds(Long[] ids);


    /**
     * 套餐分页查询
     * @param setmealQueryDTO
     * @return
     */
    List<Setmeal> queryList(SetmealQueryDTO setmealQueryDTO);
}
