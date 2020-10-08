package net.wicp.tams.app.duckula.controller.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.wicp.tams.app.duckula.controller.bean.models.CommonDeploy;
import net.wicp.tams.app.duckula.controller.bean.models.CommonTask;
import net.wicp.tams.app.duckula.controller.config.constant.CommandType;
import net.wicp.tams.app.duckula.controller.config.constant.DeployType;
import net.wicp.tams.app.duckula.controller.dao.CommonDeployMapper;
import net.wicp.tams.app.duckula.controller.service.deploy.IDeploy;
import net.wicp.tams.common.Result;
import net.wicp.tams.common.apiext.IOUtil;
import net.wicp.tams.common.apiext.StringUtil;
import net.wicp.tams.common.beans.Host;
import net.wicp.tams.common.constant.dic.YesOrNo;
import net.wicp.tams.common.exception.ProjectException;
import net.wicp.tams.common.os.SSHAssit;
import net.wicp.tams.common.os.pool.SSHConnection;
import net.wicp.tams.common.spring.autoconfig.SpringAssit;

@Service
@Slf4j
public class DeployService {
	@Autowired
	private CommonDeployMapper commonDeployMapper;

	public void deployTask(CommonTask commonTask) {
		CommonDeploy commonDeploy = commonDeployMapper.selectById(commonTask.getDeployId());
		DeployType deployType = DeployType.valueOf(commonDeploy.getDeploy());
		IDeploy deploy = (IDeploy) SpringAssit.context.getBean(deployType.name());
		Result checkExit = deploy.checkExit(commonTask.getDeployId(), CommandType.task, commonTask.getId());
		if (!checkExit.isSuc()) {
			deploy.addConfig(commonTask.getDeployId(), CommandType.task, commonTask.getId());
		}
		// deploy.start();
		log.info("the task:{} start sucess", commonTask.getName());
	}

	public Result initHost(CommonDeploy commonDeploy, String pwd) {
		DeployType deployType = DeployType.valueOf(commonDeploy.getDeploy());
		if (deployType == DeployType.k8s) {
			return Result.getError("k8s类型不需要初始化");
		}
		// 1、登陆
		SSHConnection conn = null;
		try {
			conn = SSHAssit.getConn(commonDeploy.getHost(), commonDeploy.getPort(), "root", pwd, 0);
		} catch (ProjectException e) {
			log.error("连接服务器失败", e);
			return Result.getError("连接服务器失败：" + e.getMessage());
		}
		// 3、检查是否支持docker
		YesOrNo checkDocker = conn.checkDocker();
		if (checkDocker != null && checkDocker == YesOrNo.yes && deployType == DeployType.host) {
			commonDeploy.setDeploy(DeployType.docker.name());// 强制设置为docker
			commonDeployMapper.updateByPrimaryKey(commonDeploy);
		}
		deployType = DeployType.valueOf(commonDeploy.getDeploy());
		// 4、复制配置信息等 (由运行程序来完成)
		// 5、初始化logs目录（TODO）
		// 6、复制程序(TODO)
		InputStream fileToInputStream = IOUtil.fileToInputStream("/deploy/bin/duckula-init.sh", DeployService.class);
		byte[] slurpbyte = null;
		try {
			slurpbyte = IOUtil.slurpbyte(fileToInputStream);
		} catch (IOException e) {
			log.error("获取duckula-init.sh错误", e);
			return Result.getError("获取duckula-init.sh错误");
		}
		conn.scp(slurpbyte, "duckula-init.sh", "~", "0744");
		// 7、执行shell脚本
		Result executeCommand = null;
		String hosts = "";// 主机，如果是host，没有权限配置/etc/hosts，
		if (deployType == DeployType.host && StringUtil.isNotNull(commonDeploy.getHostsconfig())) {
			hosts = Host.getHostStr(Host.jsonToHosts(commonDeploy.getHostsconfig()));
		}
		if (StringUtil.isNotNull(hosts)) {
			executeCommand = conn.executeCommand(
					String.format("sh ~/duckula-init.sh %s %s \"%s\"", "duckula", commonDeploy.getPwdDuckula(), hosts));
		} else {
			executeCommand = conn.executeCommand(
					String.format("sh ~/duckula-init.sh %s %s", "duckula", commonDeploy.getPwdDuckula()));
		}
		conn.close();
		return executeCommand;

	}
}
