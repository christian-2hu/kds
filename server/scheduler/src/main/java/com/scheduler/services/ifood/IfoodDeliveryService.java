package com.scheduler.services.ifood;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.scheduler.models.ifood.IfoodEventPolling;
import com.scheduler.models.ifood.OauthToken;
import com.scheduler.repositories.OauthTokenRepository;
import com.scheduler.request.EventAcknowledgmentRequest;
import com.scheduler.services.ApiConsumerService;
import com.scheduler.services.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IfoodDeliveryService implements DeliveryService<IfoodEventPolling> {

    @Value("${ifood.production.merchantApiHost}")
    private String merchantApiHost;
    @Value("${ifood.production.merchantId}")
    private String merchantId;
    @Value("${ifood.production.clientId}")
    private String clientId;
    @Value("${ifood.production.clientSecret}")
    private String clientSecret;
    @Value("${ifood.production.eventPollingDelayMilliseconds}")
    private Integer fixedDelayMilliseconds;
    private final ApiConsumerService apiConsumerService;
    private final OauthTokenRepository oAuthTokenRepository;

    @Override
    public IfoodEventPolling[] getOrders() {
        checkBearerToken();    
        return apiConsumerService.getContents(merchantApiHost + "/order/v1.0/events:polling", IfoodEventPolling[].class);
    }

    @Override
    public void confirmOrder(String orderId) {
        String confirmOrderEndpoint = String.format("%s/order/v1.0/orders/%s/confirm", merchantApiHost, orderId);
        apiConsumerService.postContent(confirmOrderEndpoint);
    }

    @Override
    public void prepareOrder(String orderId) {
        String prepareOrderEndpoint = String.format("%s/order/v1.0/orders/%s/startPreparation", merchantApiHost, orderId);
        apiConsumerService.postContent(prepareOrderEndpoint);
    }

    @Override
    public void finishOrder(String orderId) {
        String readyToPickupEndpoint = String.format("%s/order/v1.0/orders/%s/readyToPickup", merchantApiHost, orderId);
        apiConsumerService.postContent(readyToPickupEndpoint);    
    }

    @Override
    public void acknowledgeOrder(String orderId) {
        checkBearerToken();
        EventAcknowledgmentRequest eventAcknowledgmentRequest = new EventAcknowledgmentRequest(orderId);
        EventAcknowledgmentRequest[] eventAcknowledgments = {eventAcknowledgmentRequest};
        apiConsumerService.postContent(merchantApiHost + "/order/v1.0/events/acknowledgment", eventAcknowledgments, Object[].class);
    }

    private void checkBearerToken() {
        if(isTokenExpired()) {
            updateBearerToken(getAuthResponse());
        }
    }

    private OauthToken getAuthResponse() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grantType", "client_credentials");
        formData.add("clientId", clientId);
        formData.add("clientSecret", clientSecret);
        return apiConsumerService.postAppFormUrlEncodedAuth(merchantApiHost + "/authentication/v1.0/oauth/token", formData, OauthToken.class);
    }

    private OauthToken updateBearerToken(OauthToken token) {
        token.setId(1L);
        token.setCreatedAt(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        injectBearerToken(token.getAccessToken());
        return oAuthTokenRepository.save(token);
    }

    private void injectBearerToken(String token) {
        apiConsumerService.setBearerToken(token);
    }
    
    private boolean isTokenExpired() {
        Optional<OauthToken> tokenOptional = oAuthTokenRepository.findById(1L);
        if(tokenOptional.isEmpty()) {
            return true;
        }
        OauthToken token = tokenOptional.get();
        long currentTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if(token.getCreatedAt() + token.getExpiresIn() >= currentTimeInSeconds) {
            return true;
        }
        return false;
    }

}
