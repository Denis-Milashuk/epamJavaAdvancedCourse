package com.epam.javaAdvanced.rest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {
    private Long id;
    private Long userId;
    private String startDate;
}
