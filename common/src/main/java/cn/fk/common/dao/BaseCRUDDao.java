package cn.fk.common.dao;


import cn.fk.common.model.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;


public interface BaseCRUDDao<PK extends Serializable, M extends BaseModel<PK>> extends BaseDao{

    public PK insert(M model);

    public PK insertOrUpdate(M model);

    public Integer delete(@Param("id") PK id);

    public Integer update(M model);

    public M get(@Param("id") PK id);

    public List<M> getAll();

    public Integer count(@Param("bean") M bean, @Param("sort") String sort, @Param("order") String order);

    /*public List<M> getByParam(@Param("bean") M bean, @Param("offset") Integer offset, @Param("size") Integer size);*/

    public List<M> getByParam(@Param("bean") M bean, @Param("offset") Integer offset, @Param("size") Integer size, @Param("sort") String sort, @Param("order") String order);

}
