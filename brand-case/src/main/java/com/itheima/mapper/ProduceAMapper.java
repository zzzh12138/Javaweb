package com.itheima.mapper;

import com.itheima.pojo.ProduceA;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProduceAMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from sell_a")
    @ResultMap("aResultMap")
    List<ProduceA> selectAll();
    /**
     * 添加数据
     * @param produceA
     */
    @Insert("insert into sell_a set produce_brand_a_name = #{produceBrandAName},company_a_name=#{companyAName}," +
            "price= #{price},description= #{description},ordered= #{ordered},percentage= #{percentage}," +
            "_match_=#{_match_},status= #{status}")

    void addA(ProduceA produceA);
    //只有带param的才用.访问，返回对象用#{属性}访问
    /**
     * 添加数据
     * @param produceA
     */
    @Update("update sell_a set produce_brand_a_name = #{produceBrandAName},company_a_name=#{companyAName}," +
            "price= #{price},description= #{description},percentage= #{percentage}," +
            "_match_=#{_match_},status= #{status} where ordered= #{ordered}")
    void update(ProduceA produceA);

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
    @Select("select * from sell_a limit #{begin} , #{size}")
    @ResultMap("aResultMap")
    List<ProduceA> selectAByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from sell_a ")
    int selectTotalCount();

    /**
     * 分页条件查询
     *
     * @param begin
     * @param size
     * @return
     */
    List<ProduceA> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("produceA") ProduceA produceA);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(ProduceA produceA);

    List<ProduceA> selectConsumerByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("produceA") ProduceA produceA);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectConsumerTotalCountByCondition(ProduceA produceA);

    /**
     * 更新A商品的评价
     * @return
     */
    @Update("update sell_a set star=#{star},evaluation=#{evaluation} where id=#{id}")
    void updateById(ProduceA produceA);

    /**
     * 更新A商品的评价
     * @return
     */
    @Select("select _match_ from sell_a where id=#{id}")
    int returnCID(ProduceA produceA);
}
