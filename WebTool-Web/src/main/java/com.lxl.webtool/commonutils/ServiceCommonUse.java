package com.lxl.webtool.commonutils;

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import com.lxl.webtool.model.AIOJsonModel.Fee_info;

import java.math.BigDecimal;


public class ServiceCommonUse {
	

	/**
	 * 解析费用分类
	 * 
	 * @param feeInfo
	 * @param fType
	 *            1 个人费用 2公记/社保费用
	 */
	public static BigDecimal getFeeByFtype(Fee_info feeInfo, String fType) {
		BigDecimal fee = BigDecimal.ZERO;
		try {
			if ("1".equals(fType)) {
				fee = MyCommonUtils.getBigDecimalVal(feeInfo.getCalc_amount());
			} else if ("2".equals(fType)) {
				if (feeInfo.getDeduct_sum() != null) {
					BigDecimal fee1 = MyCommonUtils.getBigDecimalVal(
							feeInfo.getDeduct_sum().getDeduct1());
					BigDecimal fee2 = MyCommonUtils.getBigDecimalVal(
							feeInfo.getDeduct_sum().getDeduct2());
					BigDecimal fee3 = MyCommonUtils.getBigDecimalVal(
							feeInfo.getDeduct_sum().getDeduct3());
					fee = fee1.add(fee2).add(fee3);
				}
			}
		} catch (Exception e) {
			MyLoggerFactory.log(LoggerEnum.Error, e.getMessage());
		}
		return fee;
	}

}
