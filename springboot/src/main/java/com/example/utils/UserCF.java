package com.example.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.RelatedAlgo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User-Based Collaborative Filtering (UserCF) Utility
 * <p>
 * This class provides a method to recommend a list of product IDs
 * for a given user based on user-user similarity.
 */
public class UserCF {

    /**
     * Recommends a list of product IDs for the specified user based on user similarity.
     *
     * @param userId The ID of the current user for whom recommendations are generated.
     * @param list   A list of user-product rating data, where each item represents
     *               the interaction between a user and a product.
     * @return A list of product IDs recommended for the user. If no recommendations
     * are found, an empty list is returned.
     */
    public static List<Integer> recommend(Integer userId, List<RelatedAlgo> list) {
        // Group the provided data by user ID, resulting in a map of user ID to their product interactions.
        Map<Integer, List<RelatedAlgo>> userMap = list.stream()
                                                      .collect(Collectors.groupingBy(RelatedAlgo::getUseId));

        // Compute similarity values between the specified user and all other users.
        Map<Integer, Double> userDisMap = CoreMath.computeNeighbor(userId, userMap, 0);

        // If there are no other users or similarity values, return an empty list.
        if (CollectionUtil.isEmpty(userDisMap)) {
            return Collections.emptyList();
        }

        // Find the maximum similarity value from the computed similarity map.
        double maxValue = Collections.max(userDisMap.values());

        // Identify the user(s) with the highest similarity to the specified user.
        Set<Integer> userIds = userDisMap.entrySet().stream()
                                         .filter(e -> e.getValue() == maxValue)
                                         .map(Map.Entry::getKey)
                                         .collect(Collectors.toSet());

        // Select one user ID with the highest similarity as the nearest neighbor.
        Integer nearestUserId = userIds.stream().findAny().orElse(null);

        // If no nearest neighbor is found, return an empty list.
        if (nearestUserId == null) {
            return Collections.emptyList();
        }

        // Retrieve the list of product IDs that the nearest neighbor has interacted with.
        List<Integer> neighborItems = userMap.get(nearestUserId).stream()
                                             .map(RelatedAlgo::getGoodsId)
                                             .collect(Collectors.toList());

        // Retrieve the list of product IDs that the specified user has interacted with.
        List<Integer> userItems = userMap.get(userId).stream()
                                         .map(RelatedAlgo::getGoodsId)
                                         .collect(Collectors.toList());

        // Remove products that the specified user has already interacted with
        // from the list of the nearest neighbor's products.
        neighborItems.removeAll(userItems);

        // Return the remaining product IDs as recommendations.
        return neighborItems;
    }

}
