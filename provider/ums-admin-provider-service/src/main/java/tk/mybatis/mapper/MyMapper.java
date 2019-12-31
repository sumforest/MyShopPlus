package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 17:00
 * @Description:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
