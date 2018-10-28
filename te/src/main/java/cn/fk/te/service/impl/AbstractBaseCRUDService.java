package cn.fk.te.service.impl;

import cn.fk.te.mapper.BaseCRUDMapper;

import cn.fk.te.service.BaseCRUDService;
import cn.fk.te.vo.BaseModel;

import cn.fk.te.vo.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author xianlong.liu
 * @date 2018/6/19
 */
public abstract class AbstractBaseCRUDService<PK extends Serializable, M extends BaseModel<PK>, D extends BaseCRUDMapper<PK, M>> implements BaseCRUDService<PK, M> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected D dao;

    @Override
    @Transactional
    public PK insert(M model){
        return dao.insert(model);
    }

    @Override
    @Transactional
    public Integer delete(PK id){
        return dao.delete(id);
    }

    @Override
    @Transactional
    public Integer update(M model){
        return dao.update(model);
    }

    @Override
    public Page<PK, M> getPageList(M model, Integer pageNum, Integer pageSize){
        Page<PK, M> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(dao.count(model));
        page.setData(dao.getByParam(model, (pageNum - 1) * pageSize, pageSize));
        return page;
    }

    @Override
    public Page<PK, M> getPageList(M model, Integer pageNum, Integer pageSize, String sort, String order){
        Page<PK, M> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotal(dao.count(model));
        page.setData(dao.getByParam(model, (pageNum - 1) * pageSize, pageSize, sort, order));
        return page;
    }

    @Override
    public M get(PK id){
        return dao.get(id);
    }

    @Override
    public List<M> getAll(){
        return dao.getAll();
    }

    public D getDao() {
        return dao;
    }

    @Autowired
    public void setDao(D dao) {
        this.dao = dao;
    }
}
