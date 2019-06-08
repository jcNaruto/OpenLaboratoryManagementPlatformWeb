package com.jiachenzh.service;

import com.jiachenzh.entity.VerificationCodePO;

public interface EmailService {
    void emailVerify(VerificationCodePO verificationCodePO);

    void sendEmailToUser(VerificationCodePO verificationCodePO);
}
