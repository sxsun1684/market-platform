package com.example.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.RelateDTO;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserCF {

    /**
     * Method Description: Recommend a list of product IDs
     *
     * @param userId Current user
     * @param list   User product rating data
     * @return {@link List<Integer>}
     */
    public static List<Integer> recommend(Integer userId, List<RelateDTO> list) {
        // Group by user
        Map<Integer, List<RelateDTO>> userMap = list.stream().collect(Collectors.groupingBy(RelateDTO::getUseId));
        // Get the similarity values between other users and the current user
        Map<Integer, Double> userDisMap = CoreMath.computeNeighbor(userId, userMap, 0);
        if (CollectionUtil.isEmpty(userDisMap)) {
            return Collections.emptyList();
        }
        // Get the user with the closest similarity
        double maxValue = Collections.max(userDisMap.values());
        Set<Integer> userIds = userDisMap.entrySet().stream().filter(e -> e.getValue() == maxValue).map(Map.Entry::getKey).collect(Collectors.toSet());
        // Select the user with the closest relationship
        Integer nearestUserId = userIds.stream().findAny().orElse(null);
        if (nearestUserId == null) {
            return Collections.emptyList();
        }
        // List of products viewed by the nearest neighbor user
        List<Integer> neighborItems = userMap.get(nearestUserId).stream().map(RelateDTO::getGoodsId).collect(Collectors.toList());
        // List of products viewed by the specified user
        List<Integer> userItems = userMap.get(userId).stream().map(RelateDTO::getGoodsId).collect(Collectors.toList());
        // Find products viewed by the nearest neighbor but not viewed by the current user
        neighborItems.removeAll(userItems);
        return neighborItems;
    }

}
