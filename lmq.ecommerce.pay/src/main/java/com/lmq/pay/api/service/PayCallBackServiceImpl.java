package com.lmq.pay.api.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.lmq.common.base.BaseApiService;
import com.lmq.common.base.BaseResponse;
import com.lmq.common.constants.Constants;
import com.lmq.pay.alipay.AlipayConfig;
import com.lmq.pay.dao.PaymentInfoDao;
import com.lmq.pay.entity.PaymentInfo;
import com.lmq.pay.feignservice.OrderServiceFegin;
import com.lmq.pay.service.PayCallBackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PayCallBackServiceImpl extends BaseApiService implements PayCallBackService {
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	
	
	@Autowired
	private OrderServiceFegin orderServiceFegin;


	// 同步回调
	@Override
	public BaseResponse synCallBack(@RequestParam Map<String, String> params,@RequestParam("haha")String haha) {
		// 获取支付宝GET过来反馈信息
		try {
			log.info("####同步回调开始####params:{}",params);
			log.info("####同步回调开始####haha:{}",haha);
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
			// ——请在这里编写您的程序（以下代码仅作参考）——
			if (!signVerified) {
				log.info("眼前失败{}",haha);
				//return setResultError("验签失败!");
			}
			// 商户订单号
			String out_trade_no = params.get("out_trade_no");
			// 支付宝交易号
			String trade_no = params.get("trade_no");
			// 付款金额
			String total_amount = params.get("total_amount");
			JSONObject data = new JSONObject();
			data.put("out_trade_no", out_trade_no);
			data.put("trade_no", trade_no);
			data.put("total_amount", total_amount);
			return setResultSuccess(data);
		} catch (Exception e) {
			log.info("######PayCallBackServiceImpl##ERROR:#####", e);
			return setResultError("系统错误!");
		}finally{
			log.info("####同步回调结束####params:",params);
		}

	}

	// 异步回调
	@Override
	public String asynCallBack(@RequestParam Map<String, String> params) {
		// 获取支付宝GET过来反馈信息
		// 1.日志记录
				log.info("#####支付宝异步通知synCallBack#####开始,params:{}", params);
				// 2.验签操作
				try {
					boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
							AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
					log.info("#####支付宝异步通知signVerified:{}######", signVerified);
					// ——请在这里编写您的程序（以下代码仅作参考）——
					if (!signVerified) {
						return Constants.PAY_FAIL;
					}
					// 商户订单号
					String outTradeNo = params.get("out_trade_no");
					PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(outTradeNo);
					if (paymentInfo == null) {
						return Constants.PAY_FAIL;
					}

					// 支付宝重试机制
					Integer state = paymentInfo.getState();
					if (state == 1) {
						return Constants.PAY_SUCCESS;
					}

					// 支付宝交易号
					String tradeNo = params.get("trade_no");
					// 付款金额
					// String totalAmount = params.get("total_amount");
					// 判断实际付款金额与商品金额是否一致
					// 修改 支付表状态
					paymentInfo.setState(1);// 标识为已经支付
					paymentInfo.setPayMessage(params.toString());
					paymentInfo.setPlatformorderId(tradeNo);
					// 手动 begin begin
					Integer updateResult = paymentInfoDao.updatePayInfo(paymentInfo);
					if (updateResult <= 0) {
						return Constants.PAY_FAIL;
					}
					// 调用订单接口通知 支付状态
					BaseResponse orderResult = orderServiceFegin.updateOrderIdInfo(1l, tradeNo, outTradeNo);
					if (!orderResult.getRtnCode().equals(Constants.HTTP_RES_CODE_200)) {
						// 回滚 手动回滚 rollback
						return Constants.PAY_FAIL;
					} // 2PC 3PC TCC MQ
						// 手动 提交 comiit;
					return Constants.PAY_SUCCESS;
				} catch (Exception e) {
					log.error("####支付宝异步通知出现异常,ERROR:", e);
					// 回滚 手动回滚 rollback
					return Constants.PAY_FAIL;
				} finally {
					log.info("#####支付宝异步通知synCallBack#####结束,params:{}", params);
				}
	}

}
