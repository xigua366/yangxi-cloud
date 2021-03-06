package cn.xigua366.sample.mapper;


import cn.xigua366.sample.domain.dto.PageSchoolDTO;
import cn.xigua366.sample.domain.entity.SchoolDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangxi
 * @version 1.0
 */
@Mapper
public interface SchoolMapper extends BaseMapper<SchoolDO> {

    /**
     * 根据学校名称模糊查询
     * @param pageParam 分页参数
     * @param schoolName 查询条件
     * @return
     */
    IPage<PageSchoolDTO> pageSchool2(Page<?> pageParam, @Param("name") String schoolName);
}
