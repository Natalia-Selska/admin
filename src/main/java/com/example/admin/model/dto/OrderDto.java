package com.example.admin.model.dto;

import java.util.List;
import java.util.UUID;

public record OrderDto(
    UUID userId,
    List<UUID> productIds,
    float price
) {}
