package com.bankitu.transaction_service.libs;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReferenceIDGenerator {
    public String generate() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        long timestamp = System.currentTimeMillis();
        return "TRX" + timestamp + uuid.toUpperCase();
    }
}
