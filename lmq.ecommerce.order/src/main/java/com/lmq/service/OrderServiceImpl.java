package com.lmq.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.tx.annotation.ITxTransaction;
import com.lmq.common.base.BaseApiService;
import com.lmq.common.base.BaseResponse;
import com.lmq.dao.OrderDao;
import com.lmq.order.service.OrderService;


@RestController
public class OrderServiceImpl extends BaseApiService implements OrderService,ITxTransaction{
	@Autowired
	private OrderDao orderDao;

	@Transactional
	public BaseResponse updateOrderIdInfo(@RequestParam("isPay") Long isPay, @RequestParam("payId") String aliPayId,
			@RequestParam("orderNumber") String orderNumber) {
		int updateOrder = orderDao.updateOrder(isPay, aliPayId, orderNumber);
		if (updateOrder <= 0) {
			return setResultError("系统错误!");
		}
		return setResultSuccess();
	}

}
