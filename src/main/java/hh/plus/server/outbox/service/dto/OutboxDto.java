package hh.plus.server.outbox.service.dto;

import hh.plus.server.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record OutboxDto (
        String id
) {}
