package com.lmq.pay.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.tx.annotation.TxTransaction;
import com.lmq.common.base.BaseResponse;
import com.lmq.common.constants.Constants;
import com.lmq.pay.dao.PaymentInfoDao;
import com.lmq.pay.entity.PaymentInfo;
import com.lmq.pay.feignservice.OrderServiceFegin;
import com.lmq.pay.service.PayOrderService;


@RestController
public class PayOrderServiceImpl implements PayOrderService {

	
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private OrderServiceFegin orderServiceFegin;

	@SuppressWarnings("unused")
	@TxTransaction(isStart = true)
	@Transactional
	public String payOrder(String orderId, int temp) {
		// TODO Auto-generated method stub
		// 商户订单号
		
		PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
		if (paymentInfo == null) {
			return Constants.PAY_FAIL;
		}

		// 支付宝重试机制
		Integer state = paymentInfo.getState();
		if (state == 1) {
			return Constants.PAY_SUCCESS;
		}

		// 支付宝交易号
		String tradeNo = "12345673231";
		// 付款金额
		// String totalAmount = params.get("total_amount");
		// 判断实际付款金额与商品金额是否一致
		// 修改 支付表状态
		paymentInfo.setState(1);// 标识为已经支付
		paymentInfo.setPayMessage("textdd");
		paymentInfo.setPlatformorderId(tradeNo);
		// 手动 begin begin
		Integer updateResult = paymentInfoDao.updatePayInfo(paymentInfo);
		if (updateResult <= 0) {
			return Constants.PAY_FAIL;
		}
		// 调用订单接口通知 支付状态
		BaseResponse orderResult = orderServiceFegin.updateOrderIdInfo(1l, tradeNo, orderId);
		if (!orderResult.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
			// 回滚 手动回滚 rollback
			return Constants.PAY_FAIL;
		} // 2PC 3PC TCC MQ
			// 手动 提交 comiit;
		
		int i=1/temp;
		return Constants.PAY_SUCCESS;
		//return null;
	}

}
