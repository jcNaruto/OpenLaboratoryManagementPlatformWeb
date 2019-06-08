package com.jiachenzh.dao;

import com.jiachenzh.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<UserDO,String>, JpaSpecificationExecutor<UserDO> {

    UserDO findByName(String userName);

    /**
     * @Author
     * @Description 根据id查询
     * @Date 20:26 2019/3/4
     * @Param [userId]
     * @throws
     * @return com.jiachenzh.entity.UserDO
     */
    UserDO findByUserId(String userId);

    /**
     * @Author
     * @Description 判断用户email是否注册
     * @Date 8:47 2019/3/3
     * @Param [email]
     * @throws
     * @return com.jiachenzh.entity.UserDO
     */
    public UserDO findByEmail(String email);

}
