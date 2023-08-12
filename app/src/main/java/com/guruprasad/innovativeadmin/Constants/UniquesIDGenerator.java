package com.guruprasad.innovativeadmin.Constants;

import java.util.HashSet;
import java.util.UUID;

public class UniquesIDGenerator {
    private HashSet<UUID> generatedIds = new HashSet<>();

    public UUID generateUniqueId() {
        UUID uniqueId;
        do {
            uniqueId = UUID.randomUUID();
        } while (!generatedIds.add(uniqueId)); // Keep generating until a unique ID is generated
        return uniqueId;
    }
}
