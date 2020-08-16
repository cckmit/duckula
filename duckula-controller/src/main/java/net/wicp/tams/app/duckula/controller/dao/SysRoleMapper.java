package net.wicp.tams.app.duckula.controller.dao;

import java.util.List;

import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.bean.models.SysRole;
import net.wicp.tams.app.duckula.controller.bean.models.SysRoleExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    long countByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    int deleteByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_role",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_role (role_id, role_name)",
        "values (#{roleId,jdbcType=BIGINT}, #{roleName,jdbcType=VARCHAR})"
    })
    int insert(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    int insertSelective(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    List<SysRole> selectByExample(SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "role_id, role_name",
        "from sys_role",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    @ResultMap("net.wicp.tams.app.duckula.controller.dao.SysRoleMapper.BaseResultMap")
    SysRole selectByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    @Update({
        "update sys_role",
        "set role_name = #{roleName,jdbcType=VARCHAR}",
        "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRole record);
}