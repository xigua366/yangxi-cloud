package cn.xigua366.sample.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.xigua366.sample.domain.entity.UserDO;

/**
 * <P>
 *
 * </P>
 *
 * @author yangxi
 * @version 1.0
 */
public interface UserDAO extends IService<UserDO> {

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    UserDO findUserByPhone(String phone);

}