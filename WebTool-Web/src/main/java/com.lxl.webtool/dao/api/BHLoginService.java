package com.lxl.webtool.dao.api;

import com.lxl.webtool.commonutils.UUIDUtils;
import com.lxl.webtool.dao.mapper.UsersMapper;
import com.lxl.webtool.dao.pojo.Users;
import com.lxl.webtool.model.api.response.BHLoginApiResponse;
import com.lxl.webtool.model.api.response.BaseApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("bhLoginService")
public class BHLoginService {

	@Autowired
	private UsersMapper usersMapper;

	public BaseApiResponse Excute(String companyId, String timeStamp) {

		/*
		 * 1.查询系统中是否包含可用用户 2.如可用,则生成唯一标识,并更新最新状态，更新数据库，后返回
		 * 3.如不可用，或者不存在，则直接返回异常即可
		 */
		BHLoginApiResponse bhLoginApiResult = new BHLoginApiResponse();
		Users findUser = new Users();
		findUser.setUsername(companyId);
		Users users = usersMapper.selectByPrimaryKey(companyId);
		if (users != null && StringUtils.isNotBlank(users.getUsername())) {
			bhLoginApiResult.setCode("0");
			bhLoginApiResult.setMessage("获取成功");
			String token = UUIDUtils.GetUUID();
			bhLoginApiResult.setData(token);

			// 更新用户token和最后登录日期
			// info1用来存储api用户登录token
			// info2用来存储api用户登录最近一次时间
			users.setInfo1(token);
			users.setInfo2(Long.toString(System.currentTimeMillis()));
			usersMapper.updateByPrimaryKey(users);
		} else {
			bhLoginApiResult.setCode("-10000");
			bhLoginApiResult.setData("");
			bhLoginApiResult.setMessage("id无效");
		}
		return bhLoginApiResult;
	}
}
