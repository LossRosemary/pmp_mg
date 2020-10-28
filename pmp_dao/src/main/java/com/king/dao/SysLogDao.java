package com.king.dao;

import com.king.domain.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {

    // 保存日志
    void save(SysLog sysLog);

    // 查询日志
    List<SysLog> findAll();

}
