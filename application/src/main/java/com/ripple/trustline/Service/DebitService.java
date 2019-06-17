package com.ripple.trustline.Service;

import com.ripple.trustline.application.TrustLine;

public interface DebitService {

    public Integer debitAmount(TrustLine trustline, String amount);

}
