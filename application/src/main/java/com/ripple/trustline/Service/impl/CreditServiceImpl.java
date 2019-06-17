package com.ripple.trustline.Service.impl;

import com.ripple.trustline.Service.CreditService;
import com.ripple.trustline.application.TrustLine;
import javax.annotation.Resource;

public class CreditServiceImpl implements CreditService {

    @Resource(name = "trustline")
    private TrustLine trustline;

    @Override
    public Integer CreateDeposit(Integer amount) {
        trustline.credit(amount);
        return null;
    }
}
