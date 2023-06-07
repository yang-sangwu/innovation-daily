package com.daily.dto;

import com.daily.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author a1002
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLearnTimeDto {
    private User user;

    private Long learnTimeSum;
}
