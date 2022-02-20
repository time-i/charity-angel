package com.whu.charityangel.service;


import com.whu.charityangel.model.entity.Transfer;

import java.util.List;

public interface TransferService {
    Transfer insertTransfer(Transfer info);
    int updateSource(Transfer info);
    int updateDestination(Transfer info);
    List<Transfer> queryMyRecord(Long id);
    List<Transfer> selectTransferByTo(Long accountId);
    List<Transfer> selectTransferByFrom(Long accountId);
}
