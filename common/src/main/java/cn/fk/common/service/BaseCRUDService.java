package cn.fk.common.service;


import cn.fk.common.model.BaseModel;
import cn.fk.common.model.vo.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author xianlong.liu
 * @date 2018/6/19
 */
public interface BaseCRUDService <PK extends Serializable, M extends BaseModel<PK>>{

    public PK insert(M model);

    public PK insertOrUpdate(M model);

    public Integer delete(PK id);

    public Integer update(M model);

    public M get(PK id);

    public List<M> getAll();

    /*public Page<PK, M> getPageList(M model, Integer pageNum, Integer pageSize);*/

    public Page<PK, M> getPageList(M model, Integer pageNum, Integer pageSize, String sort, String order);

}
