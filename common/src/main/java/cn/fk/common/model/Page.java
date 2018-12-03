package cn.fk.common.model;

import cn.fk.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Page<PK extends Serializable, M extends BaseModel<PK>> {

    private List<M> data;
    private Integer total;
    private Integer pageSize;
    private Integer pageNum;

}
