package com.xl.youliao.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

/**
 * @author WeiC
 * @date 2020/5/6 17:09
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, DeleteByIdsMapper<T> {
}
