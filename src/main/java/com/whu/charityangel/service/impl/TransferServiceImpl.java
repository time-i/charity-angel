package com.whu.charityangel.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.charityangel.mapper.TransferMapper;
import com.whu.charityangel.model.entity.Transfer;
import com.whu.charityangel.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements TransferService {
    @Override
    public Transfer insertTransfer(Transfer info){
        info.setDateTime(new Date());
        baseMapper.insertTransfer(info);
        return info;
    }

    @Override
    public int updateSource(Transfer info){
        return baseMapper.updateSource(info);
    }

    @Override
    public int updateDestination(Transfer info){
        return baseMapper.updateDestination(info);
    }

    @Override
    public List<Transfer> queryMyRecord(Long id) {
        return baseMapper.queryMyRecord(id);
    }

    @Override
    public List<Transfer> selectTransferByTo(Long accountId) {
        return this.baseMapper.selectTransferByTo(accountId);
    }

    @Override
    public List<Transfer> selectTransferByFrom(Long accountId) {
        return this.baseMapper.selectTransferByFrom(accountId);
    }

}
