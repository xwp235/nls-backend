package com.nlsapi.core.business.service.impl;

import com.nlsapi.core.business.context.LoginMemberContext;
import com.nlsapi.core.business.entity.MastFileTransEntity;
import com.nlsapi.core.business.enums.FileTransPayStatusEnum;
import com.nlsapi.core.business.enums.FileTransStatusEnum;
import com.nlsapi.core.business.enums.OrderInfoTypeEnum;
import com.nlsapi.core.business.mapper.cust.CustMastFileTransEntityMapper;
import com.nlsapi.core.business.req.FileTransPayReq;
import com.nlsapi.core.business.req.OrderInfoPayReq;
import com.nlsapi.core.business.resp.OrderInfoPayResp;
import com.nlsapi.core.business.service.FileTransService;
import com.nlsapi.core.business.service.OrderInfoService;
import com.nlsapi.core.common.utils.AliyunVodUtil;
import com.nlsapi.core.common.utils.IdWorkerUtil;
import com.nlsapi.core.common.utils.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileTransServiceImpl implements FileTransService {

    private final CustMastFileTransEntityMapper custMastFileTransEntityMapper;
    private final OrderInfoService orderInfoService;

    @Override
    public OrderInfoPayResp pay(FileTransPayReq req) throws Exception {
        // 获取视频信息
        var videoInfo = AliyunVodUtil.getVideoInfo(req.getVod());
        var duration = videoInfo.getVideo().getDuration();
        LogUtil.info("视频：{}，时长：{}", req.getVod(), duration);
        var second = Math.round(duration);
        var filetrans = new MastFileTransEntity();
        var id = IdWorkerUtil.getId();
        filetrans.setId(id);
        filetrans.setMemberId(LoginMemberContext.getId());
        filetrans.setName(req.getName());
        filetrans.setSecond(second);
        filetrans.setAmount(req.getAmount());
        filetrans.setAudio(req.getAudio());
        filetrans.setFileSign(req.getFileSign());
        filetrans.setPayStatus(FileTransPayStatusEnum.I.getCode());
        filetrans.setStatus(FileTransStatusEnum.INIT.getCode());
        filetrans.setLang(req.getLang());
        filetrans.setVod(req.getVod());
        custMastFileTransEntityMapper.insertSelective(filetrans);

        // 保存订单信息
        OrderInfoPayReq orderInfoPayReq = new OrderInfoPayReq();
        orderInfoPayReq.setOrderType(OrderInfoTypeEnum.FILE_TRANS_PAY.getCode());
        // 订单表的info保存语音识别表的id
        orderInfoPayReq.setInfo(String.valueOf(id));
        orderInfoPayReq.setAmount(req.getAmount());
        orderInfoPayReq.setChannel(req.getChannel());
        orderInfoPayReq.setDesc("语音识别付费");
        return orderInfoService.pay(orderInfoPayReq);
    }

}
