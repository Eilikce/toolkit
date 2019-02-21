package com.eilikce.toolkit.kits.mysql.dao;

import com.eilikce.toolkit.kits.mysql.entity.Eilikce;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * eilikce
 * @author Eilikce 2019-02-21
 */
@Component
public interface EilikceDao {

    /**
     * 新增
     */
    public int insert(@Param("eilikce") Eilikce eilikce);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("eilikce") Eilikce eilikce);

    /**
     * Load查询
     */
    public Eilikce load(@Param("id") int id);

    /**
     * 分页查询Data
     */
    public List<Eilikce> pageList(@Param("offset") int offset,
                                  @Param("pagesize") int pagesize);

    /**
     * 分页查询Count
     */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
