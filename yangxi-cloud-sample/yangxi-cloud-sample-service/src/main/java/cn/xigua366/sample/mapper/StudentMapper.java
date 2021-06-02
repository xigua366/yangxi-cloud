package cn.xigua366.sample.mapper;

import cn.xigua366.sample.domain.dto.PageStudentDTO;
import cn.xigua366.sample.domain.entity.StudentDO;
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
public interface StudentMapper extends BaseMapper<StudentDO> {

    /**
     * 分页查询学生列表
     * @param pageParam 分页参数
     * @param name 学生姓名
     * @return
     */
    IPage<PageStudentDTO> pageStudent(Page<?> pageParam, @Param("name") String name);

}
