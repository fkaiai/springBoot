package cn.fk.common.model;

import java.io.Serializable;

/**
 * @author xianlong.liu
 * @date 2018/6/19
 */
public interface BaseModel<PK extends Serializable> extends Serializable {

   public PK getId();

   public void setId(PK id);

}
