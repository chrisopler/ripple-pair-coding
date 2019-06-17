package com.ripple.trustline.Service;

import com.ripple.trustline.application.BadCreditValueException;
import com.ripple.trustline.application.TrustLine;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreditService {


    public Integer CreateDeposit(Integer amount);


}
