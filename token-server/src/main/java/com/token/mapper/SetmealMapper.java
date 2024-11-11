package com.token.mapper;

import com.token.annotation.AutoFill;
import com.token.entity.Setmeal;
import com.token.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface SetmealMapper {

    @Insert("insert into setmeal(category_id, name, price, status, description, image, create_time, update_time, create_user, update_user) values " +
            "(#{categoryId},#{name},#{price},#{status},#{description},#{image},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Setmeal setmeal);
}
