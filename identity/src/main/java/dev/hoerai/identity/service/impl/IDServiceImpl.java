package dev.hoerai.identity.service.impl;

import dev.hoerai.identity.service.IDService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class IDServiceImpl implements IDService {
    private Map<String, Set<String>> idData = new HashMap<>();

    @Override
    public void register(String id, String phone){
        Set<String> phones = idData.getOrDefault(id, new HashSet<>());
        phones.add(phone);
        idData.put(id,phones);
    }

    @Override
    public Set<String> getPhones(String id){
        return idData.get(id);
    }

}
