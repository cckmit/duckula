package net.wicp.tams.app.duckula.controller.dao;

import java.util.List;

import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddleware;
import net.wicp.tams.app.duckula.controller.bean.models.CommonMiddlewareExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CommonMiddlewareMapper extends BaseMapper<CommonMiddleware> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	long countByExample(CommonMiddlewareExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	int deleteByExample(CommonMiddlewareExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	@Delete({ "delete from common_middleware", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	@Insert({ "insert into common_middleware (id, name, ", "middleware_type, version, ", "user_id, com_id, host, ",
			"port, username, ", "password, config, remark)",
			"values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
			"#{middlewareType,jdbcType=VARCHAR}, #{version,jdbcType=VARCHAR}, ",
			"#{userId,jdbcType=VARCHAR}, #{comId,jdbcType=VARCHAR}, #{host,jdbcType=VARCHAR}, ",
			"#{port,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
			"#{password,jdbcType=VARCHAR}, #{config,jdbcType=CHAR}, #{remark,jdbcType=VARCHAR})" })
	int insert(CommonMiddleware record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	int insertSelective(CommonMiddleware record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	List<CommonMiddleware> selectByExample(CommonMiddlewareExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	@Select({ "select", "id, name, middleware_type, version, user_id, com_id, host, port, username, password, ",
			"config, remark", "from common_middleware", "where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("net.wicp.tams.app.duckula.controller.dao.CommonMiddlewareMapper.BaseResultMap")
	CommonMiddleware selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") CommonMiddleware record,
			@Param("example") CommonMiddlewareExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") CommonMiddleware record, @Param("example") CommonMiddlewareExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CommonMiddleware record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_middleware
	 * @mbg.generated
	 */
	@Update({ "update common_middleware", "set name = #{name,jdbcType=VARCHAR},",
			"middleware_type = #{middlewareType,jdbcType=VARCHAR},", "version = #{version,jdbcType=VARCHAR},",
			"user_id = #{userId,jdbcType=VARCHAR},", "com_id = #{comId,jdbcType=VARCHAR},",
			"host = #{host,jdbcType=VARCHAR},", "port = #{port,jdbcType=VARCHAR},",
			"username = #{username,jdbcType=VARCHAR},", "password = #{password,jdbcType=VARCHAR},",
			"config = #{config,jdbcType=CHAR},", "remark = #{remark,jdbcType=VARCHAR}",
			"where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(CommonMiddleware record);
}