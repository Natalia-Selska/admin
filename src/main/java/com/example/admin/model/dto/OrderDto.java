package com.example.admin.model.dto;

import java.util.List;
import java.util.UUID;

public record OrderDto(
    List<UUID> productIds
) {}
