package com.abc.messagehash.buisness.service;

import com.abc.messagehash.buisness.domain.MessageHashNotFoundException;
import com.abc.messagehash.data.entity.MessageHash;
import com.abc.messagehash.data.repo.MessageHashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Service
public class MessageHashService {

    private final MessageHashRepository msgHashRepo;
    private MessageDigest digest;

    @Autowired
    public MessageHashService(MessageHashRepository repository) {
        super();
        this.msgHashRepo = repository;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public MessageHash getMessageByHash(String hash) throws Exception {
        List<MessageHash> byHash = this.msgHashRepo.findByHash(hash);

        if(byHash == null || byHash.isEmpty())
            throw new MessageHashNotFoundException(hash);

        return byHash.get(0);
    }

    public String createMessageHash(String message) {
        String hash = createHash(message);

        MessageHash msgHash = new MessageHash();
        msgHash.setHash(hash);
        msgHash.setMessage(message);

        msgHashRepo.save(msgHash);

        return hash;

    }

    public void deleteMessageByHash(MessageHash messageByHash) {
        msgHashRepo.delete(messageByHash);
    }

    private String createHash(String message) {
        String hash;
        synchronized (digest) {
            hash = toHexString(digest.digest(message.getBytes(StandardCharsets.UTF_8)));
        }
        return hash;
    }

    private String toHexString(byte[] input) {
        BigInteger num = new BigInteger(1,input);
        return num.toString(16);
    }
}
