package net.wicp.tams.app.duckula.controller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeployExample;

public interface CommonDeployMapper extends BaseMapper<CommonDeploy> {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	long countByExample(CommonDeployExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	int deleteByExample(CommonDeployExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	@Delete({ "delete from common_deploy", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	@Insert({ "insert into common_deploy (id, name, ", "deploy, env, url, ", "token, config, host, ", "pwd, remark)",
			"values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
			"#{deploy,jdbcType=VARCHAR}, #{env,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
			"#{token,jdbcType=VARCHAR}, #{config,jdbcType=VARCHAR}, #{host,jdbcType=VARCHAR}, ",
			"#{pwd,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})" })
	int insert(CommonDeploy record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	int insertSelective(CommonDeploy record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	List<CommonDeploy> selectByExample(CommonDeployExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	@Select({ "select", "id, name, deploy, env, url, token, config, host, pwd, remark", "from common_deploy",
			"where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper.BaseResultMap")
	CommonDeploy selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") CommonDeploy record, @Param("example") CommonDeployExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") CommonDeploy record, @Param("example") CommonDeployExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CommonDeploy record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table common_deploy
	 * @mbg.generated
	 */
	@Update({ "update common_deploy", "set name = #{name,jdbcType=VARCHAR},", "deploy = #{deploy,jdbcType=VARCHAR},",
			"env = #{env,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},", "token = #{token,jdbcType=VARCHAR},",
			"config = #{config,jdbcType=VARCHAR},", "host = #{host,jdbcType=VARCHAR},",
			"pwd = #{pwd,jdbcType=VARCHAR},", "remark = #{remark,jdbcType=VARCHAR}",
			"where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(CommonDeploy record);
}