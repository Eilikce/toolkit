package com.eilikce.toolkit.kits.mysql;

import com.eilikce.toolkit.action.BaseAction;
import com.eilikce.toolkit.kits.mysql.dao.EilikceDao;
import com.eilikce.toolkit.kits.mysql.entity.Eilikce;

import java.util.List;

public class QueryAction implements BaseAction<List<Eilikce>> {

    EilikceDao eilikceDao;

    @Override
    public QueryAction init(Object... objects) {
        eilikceDao = (EilikceDao) objects[0];
        return this;
    }

    @Override
    public List<Eilikce> doAction() {
        return eilikceDao.pageList(0, 100);
    }

}
