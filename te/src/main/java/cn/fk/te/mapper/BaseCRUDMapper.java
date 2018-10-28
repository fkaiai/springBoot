package cn.fk.te.mapper;


import cn.fk.te.vo.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author xianlong.liu
 * @date 2018/6/19
 */
public interface BaseCRUDMapper<PK extends Serializable, M extends BaseModel<PK>>{

    public PK insert(M model);

    public Integer delete(@Param("id") PK id);

    public Integer update(M model);

    public M get(@Param("id") PK id);

    public List<M> getAll();

    public Integer count(@Param("bean") M bean);

    public List<M> getByParam(@Param("bean") M bean, @Param("offset") Integer offset, @Param("size") Integer size);

    public List<M> getByParam(@Param("bean") M bean, @Param("offset") Integer offset, @Param("size") Integer size, @Param("sort") String sort, @Param("order") String order);

}
