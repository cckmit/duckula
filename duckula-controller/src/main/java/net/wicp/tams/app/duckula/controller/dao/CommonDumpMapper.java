package net.wicp.tams.app.duckula.controller.dao;

import java.util.List;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDump;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDumpExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CommonDumpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    long countByExample(CommonDumpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    int deleteByExample(CommonDumpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    @Delete({
        "delete from common_dump",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    @Insert({
        "insert into common_dump (id, name, ",
        "version, namespace, ",
        "rule)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{version,jdbcType=VARCHAR}, #{namespace,jdbcType=VARCHAR}, ",
        "#{rule,jdbcType=VARCHAR})"
    })
    int insert(CommonDump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    int insertSelective(CommonDump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    List<CommonDump> selectByExample(CommonDumpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, version, namespace, rule",
        "from common_dump",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("net.wicp.tams.app.duckula.controller.dao.CommonDumpMapper.BaseResultMap")
    CommonDump selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CommonDump record, @Param("example") CommonDumpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CommonDump record, @Param("example") CommonDumpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CommonDump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table common_dump
     *
     * @mbg.generated
     */
    @Update({
        "update common_dump",
        "set name = #{name,jdbcType=VARCHAR},",
          "version = #{version,jdbcType=VARCHAR},",
          "namespace = #{namespace,jdbcType=VARCHAR},",
          "rule = #{rule,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CommonDump record);
}