package com.hanghae.payment.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.PaymentService;
import com.hanghae.payment.domain.dto.response.PaymentDto;
import com.hanghae.payment.presentation.mapper.PaymentDtoMapper;
import com.hanghae.payment.presentation.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public CustomResponse<PaymentResponse> attemptPayment(
        @RequestParam(name = "orderId") Long orderId
    ){
        PaymentDto paymentDto = paymentService.create(orderId);
        return CustomResponse.success(PaymentDtoMapper.toPaymentResponse(paymentDto));
    }

}
