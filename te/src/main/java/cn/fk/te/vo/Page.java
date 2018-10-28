package cn.fk.te.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xianlong.liu
 * @date 2018/6/19
 */
@Data
public class Page<PK extends Serializable, M extends BaseModel<PK>> {

    private List<M> data;
    private Integer total;
    private Integer pageSize;
    private Integer pageNum;

}
