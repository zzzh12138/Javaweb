package com.itheima.mapper;

import com.itheima.pojo.ProduceA;
import com.itheima.pojo.ProduceC;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProduceCMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from sell_c")
    @ResultMap("cResultMap")
    List<ProduceC> selectAll();

    /**
     * 添加数据
     * @param produceC
     */
    @Insert("insert into sell_c set produce_brand_c_name = #{produceC.produceBrandCName},company_c_name=#{produceC.companyCName}," +
            "price= #{produceC.price},description= #{produceC.description},ordered= #{produceC.ordered},percentage= #{produceC.percentage}," +
            "status= #{produceC.status}")
    void add(ProduceC produceC);

    /**
     * 添加数据
     * @param produceC
     */
    @Update("update sell_c set  produce_brand_c_name = #{produceC.produceBrandCName},company_a_name=#{produceC.companyCName}," +
            "price= #{produceC.price},description= #{produceC.description},ordered= #{produceC.ordered},percentage= #{produceC.percentage}," +
            "status= #{produceA.status} where id =#{produceC.id}")
    void update(ProduceC produceC);

    @Update("update sell_c set star=#{star},evaluation=#{evaluation} where id=#{id}")
    void updateById(ProduceC produceC);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from sell_c limit #{begin} , #{size}")
    @ResultMap("cResultMap")
    List<ProduceC> selectCByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from sell_c ")
    int selectTotalCount();

    /**
     * 分页条件查询
     *
     * @param begin
     * @param size
     * @return
     */
     List<ProduceC> selectServiceByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("produceC") ProduceC produceC);


    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectServiceTotalCountByCondition(ProduceC produceC);
}
