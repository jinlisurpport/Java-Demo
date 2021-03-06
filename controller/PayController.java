package demo.pays.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.pays.api.model.PaySaPi;
import demo.pays.api.util.PayUtil;

@Controller
@RequestMapping("/pays")
public class PayController {

	@RequestMapping("/pay")
	@ResponseBody
	public Map<String, Object> pay(HttpServletRequest request, String price, int istype) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remoteMap = new HashMap<String, Object>();
		remoteMap.put("price", price);
		remoteMap.put("istype", istype);
		remoteMap.put("orderid", PayUtil.getOrderIdByUUId());
		remoteMap.put("orderuid", "您自己想要传输的ID");
		remoteMap.put("goodsname", "您自己的商品名称");
		resultMap.put("data", PayUtil.payOrder(remoteMap));
		return resultMap;
	}

	@RequestMapping("/notifyPay")
	public void notifyPay(HttpServletRequest request, HttpServletResponse response, PaySaPi paySaPi) {
		// 保证密钥一致性
		if (PayUtil.checkPayKey(paySaPi)) {
			// TODO 做自己想做的
		} else {
			// TODO 该怎么做就怎么做
		}
	}

	@RequestMapping("/returnPay")
	public ModelAndView returnPay(HttpServletRequest request, HttpServletResponse response, String orderid) {
		boolean isTrue = false;
		ModelAndView view = null;
		// 根据订单号查找相应的记录:根据结果跳转到不同的页面
		if (isTrue) {
			view = new ModelAndView("/正确的跳转地址");
		} else {
			view = new ModelAndView("/没有支付成功的地址");
		}
		return view;
	}
}
