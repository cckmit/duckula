package net.wicp.tams.app.duckula.controller.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import java.util.List;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTaskExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CommonTaskMapper extends BaseMapper<CommonTask> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	long countByExample(CommonTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	int deleteByExample(CommonTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	@Delete({ "delete from common_task", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	@Insert({ "insert into common_task (id, name, ", "buffer_type, send_num, ", "rule, version, deploy_id, ",
			"user_id, middleware_id, ", "instance_id, checkpoint, ", "group_id, client_id, ",
			"ha_type, gtids, attr_config)", "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
			"#{bufferType,jdbcType=VARCHAR}, #{sendNum,jdbcType=INTEGER}, ",
			"#{rule,jdbcType=VARCHAR}, #{version,jdbcType=BIGINT}, #{deployId,jdbcType=BIGINT}, ",
			"#{userId,jdbcType=BIGINT}, #{middlewareId,jdbcType=BIGINT}, ",
			"#{instanceId,jdbcType=BIGINT}, #{checkpoint,jdbcType=BIGINT}, ",
			"#{groupId,jdbcType=INTEGER}, #{clientId,jdbcType=INTEGER}, ",
			"#{haType,jdbcType=VARCHAR}, #{gtids,jdbcType=VARCHAR}, #{attrConfig,jdbcType=VARCHAR})" })
	int insert(CommonTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	int insertSelective(CommonTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	List<CommonTask> selectByExample(CommonTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	@Select({ "select", "id, name, buffer_type, send_num, rule, version, deploy_id, user_id, middleware_id, ",
			"instance_id, checkpoint, group_id, client_id, ha_type, gtids, attr_config", "from common_task",
			"where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("net.wicp.tams.app.duckula.controller.dao.CommonTaskMapper.BaseResultMap")
	CommonTask selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") CommonTask record, @Param("example") CommonTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") CommonTask record, @Param("example") CommonTaskExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CommonTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_task
	 * @mbg.generated
	 */
	@Update({ "update common_task", "set name = #{name,jdbcType=VARCHAR},",
			"buffer_type = #{bufferType,jdbcType=VARCHAR},", "send_num = #{sendNum,jdbcType=INTEGER},",
			"rule = #{rule,jdbcType=VARCHAR},", "version = #{version,jdbcType=BIGINT},",
			"deploy_id = #{deployId,jdbcType=BIGINT},", "user_id = #{userId,jdbcType=BIGINT},",
			"middleware_id = #{middlewareId,jdbcType=BIGINT},", "instance_id = #{instanceId,jdbcType=BIGINT},",
			"checkpoint = #{checkpoint,jdbcType=BIGINT},", "group_id = #{groupId,jdbcType=INTEGER},",
			"client_id = #{clientId,jdbcType=INTEGER},", "ha_type = #{haType,jdbcType=VARCHAR},",
			"gtids = #{gtids,jdbcType=VARCHAR},", "attr_config = #{attrConfig,jdbcType=VARCHAR}",
			"where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(CommonTask record);

}
