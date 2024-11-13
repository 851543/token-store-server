package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.Setmeal;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    @Insert("insert into setmeal(category_id, name, price, status, description, image, create_time, update_time, create_user, update_user) values " +
            "(#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Setmeal setmeal);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);



    void delete(Long[] ids);

    List<Long> getStatusByids(Long[] ids);

    /**
     * 根据id获取套餐详情
     * @param id
     * @return
     */
    @Select("select * from setmeal where id = #{id};")
    Setmeal getByIdSetmea(Long id);
}
