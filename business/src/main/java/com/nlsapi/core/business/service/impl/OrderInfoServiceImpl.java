package com.nlsapi.core.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.nlsapi.core.business.alipay.AliPayService;
import com.nlsapi.core.business.context.LoginMemberContext;
import com.nlsapi.core.business.entity.OrderInfoEntity;
import com.nlsapi.core.business.entity.OrderInfoEntityExample;
import com.nlsapi.core.business.enums.OrderInfoChannelEnum;
import com.nlsapi.core.business.enums.OrderInfoStatusEnum;
import com.nlsapi.core.business.enums.exception.PayExceptionEnum;
import com.nlsapi.core.business.mapper.OrderInfoEntityMapper;
import com.nlsapi.core.business.mapper.cust.CustOrderInfoEntityMapper;
import com.nlsapi.core.business.req.OrderInfoPayReq;
import com.nlsapi.core.business.resp.OrderInfoPayResp;
import com.nlsapi.core.business.service.OrderInfoService;
import com.nlsapi.core.common.exception.BusinessException;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.LogUtil;
import com.nlsapi.core.common.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

// 支付宝接入平台： https://open.alipay.com/
// 支付宝开发文档和工具: https://opendocs.alipay.com/open
// 支付宝沙箱账号： https://open.alipay.com/develop/sandbox/account
@Service
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {

   private final OrderInfoEntityMapper orderInfoEntityMapper;
   private final CustOrderInfoEntityMapper custOrderInfoEntityMapper;
   private final AliPayService aliPayService;

   @Override
   public OrderInfoPayResp pay(OrderInfoPayReq req) {

      OrderInfoEntity orderInfo = new OrderInfoEntity();
      orderInfo.setId(IdWorkerUtil.getId());
      String orderNo = genOrderNo();
      orderInfo.setOrderNo(orderNo);
      orderInfo.setOrderType(req.getOrderType());
      orderInfo.setInfo(req.getInfo());
      orderInfo.setMemberId(LoginMemberContext.getId());
      orderInfo.setAmount(req.getAmount());
      orderInfo.setChannel(req.getChannel());
      orderInfo.setStatus(OrderInfoStatusEnum.I.getCode());
      orderInfo.setDesc(req.getDesc());
      custOrderInfoEntityMapper.insertSelective(orderInfo);

      OrderInfoPayResp orderInfoPayResp = new OrderInfoPayResp();
      orderInfoPayResp.setOrderNo(orderNo);

      // 请求支付宝接口
      if (OrderInfoChannelEnum.ALI_PAY.getCode().equals(req.getChannel())) {
         // 调用支付宝下单接口
         var response = aliPayService.pay(req.getDesc(), orderNo, req.getAmount().toPlainString());
         orderInfoPayResp.setChannelResult(response.getBody());
         return orderInfoPayResp;
      } else {
         LogUtil.warn("支付渠道【{}】不存在", req.getChannel());
         throw new BusinessException(PayExceptionEnum.PAY_ERROR);
      }
   }

   /**
    * 查询本地订单状态并返回
    * @param orderNo
    * @return
    */
   public String queryOrderStatus(String orderNo) {
      var orderInfo = selectByOrderNo(orderNo);
//      // 全链路查询
//      if (OrderInfoStatusEnum.I.getCode().equals(orderInfo.getStatus())) {
//         if (OrderInfoChannelEnum.ALI_PAY.getCode().equals(orderInfo.getChannel())) {
//            AlipayTradeQueryResponse response = aliPayService.query(orderNo);
//            String tradeStatus = response.getTradeStatus();
//            if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
//               String sendPayDate = response.getSendPayDate();
//               Date date = DateUtil.parse(sendPayDate, "yyyy-MM-dd HH:mm:ss");
//               afterPayService.afterPaySuccess(orderNo, date);
//               return OrderInfoStatusEnum.S.getCode();
//            }
//         }
//      }
      return orderInfo.getStatus();
   }

   public OrderInfoEntity selectByOrderNo(String orderNo) {
      OrderInfoEntityExample orderInfoExample = new OrderInfoEntityExample();
      OrderInfoEntityExample.Criteria criteria = orderInfoExample.createCriteria();
      criteria.andOrderNoEqualTo(orderNo);
      var list = orderInfoEntityMapper.selectByExample(orderInfoExample);
      if (CollectionUtils.isEmpty(list)) {
         return new OrderInfoEntity();
      }
      return list.get(0);
   }


   private String genOrderNo() {
      String no = DateUtil.format(TimeUtil.getCurrentTime(), "yyyyMMddHHmmssSSS");
      int random = (int) (Math.random() * 900 + 100);
      no = no + random;
      return no;
   }

}
