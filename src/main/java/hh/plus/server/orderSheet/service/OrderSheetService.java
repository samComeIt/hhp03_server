package hh.plus.server.orderSheet.service;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.orderSheet.controller.dto.OrderSheetItemDto;
import hh.plus.server.orderSheet.controller.dto.request.OrderSheetItemRequestDto;
import hh.plus.server.orderSheet.controller.dto.request.OrderSheetRequestDto;
import hh.plus.server.orderSheet.domain.entity.OrderSheet;
import hh.plus.server.orderSheet.domain.entity.OrderSheetItem;
import hh.plus.server.orderSheet.domain.repository.OrderSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderSheetService {
    private final OrderSheetRepository orderSheetRepository;

    public OrderSheet createOrder(OrderSheetRequestDto orderSheetRequestDto)
    {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setCreatedAt(LocalDateTime.now());

        for (OrderSheetItemRequestDto itemRequestDto : orderSheetRequestDto.getOrderSheetItem()) {
            OrderSheetItem orderSheetItem = new OrderSheetItem();
            //orderSheetItem.setStatus(itemRequestDto.get);
        }
        return orderSheetRepository.save(orderSheet);
    }

    public Optional<OrderSheet> findById(Long orderSheetId)
    {
        Optional<OrderSheet> order = orderSheetRepository.findById(orderSheetId);
        return order;
    }

    public void delete(Long orderSheetId)
    {
        Optional<OrderSheet> orderSheet = orderSheetRepository.findById(orderSheetId);

        if (orderSheet.isPresent()) {
            orderSheetRepository.delete(orderSheet.get());
        } else {
            throw new RuntimeException("Order Sheet not found with order_sheet_id: " + orderSheetId);
        }
    }


}
