package dev.hoerai.identity.service;

import java.util.Set;

public interface IDService {
    void register(String id, String phone);

    Set<String> getPhones(String id);
}
