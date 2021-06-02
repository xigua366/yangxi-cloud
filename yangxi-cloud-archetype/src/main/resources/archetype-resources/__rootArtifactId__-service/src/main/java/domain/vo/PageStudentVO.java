package ${package}.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yangxi.cloud.framework.domain.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangxi
 * @version 1.0
 */
@Data
@ApiModel
public class PageStudentVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("学校名称")
    @JsonProperty("school_name")
    private String schoolName;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("姓别")
    private Integer sex;

    /**
     * 其它字段1
     */
    @ApiModelProperty("其它字段1")
    private String other1;

    /**
     * 其它字段2
     */
    @ApiModelProperty("其它字段2")
    private String other2;

}