package com.hanghae.payment.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.PaymentService;
import com.hanghae.payment.domain.dto.response.PaymentDto;
import com.hanghae.payment.presentation.mapper.PaymentDtoMapper;
import com.hanghae.payment.presentation.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/attempt")
    public CustomResponse<PaymentResponse> attemptPayment(
        @RequestHeader("X-User-Id") Long userId,
        @RequestParam(name = "orderId") Long orderId
    ){
        PaymentDto paymentDto = paymentService.create(userId, orderId);
        return CustomResponse.success(PaymentDtoMapper.toPaymentResponse(paymentDto));
    }


    @PostMapping("/{paymentId}")
    public CustomResponse<String> submitPayment(
        @RequestHeader("X-User-Id") Long userId,
        @RequestParam(name = "resultCode") String resultCode,
        @PathVariable(name = "paymentId") Long paymentId
    ){
        return CustomResponse.success(paymentService.processPayment(userId, paymentId, resultCode));
    }
}
