package com.example.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.example.entity.RelatedAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * Core Algorithm Utilities
 * <p>
 * This class contains methods for computing correlation coefficients
 * and calculating Pearson correlation distances.
 *
 * @version 1.0
 */
public class CoreMath {

    /**
     * Compute correlation distances for a specific key and sort them.
     *
     * @param key  The reference key (e.g., user ID or item ID).
     * @param map  A map where keys represent entities (users or items)
     *             and values are lists of related algorithms.
     * @param type The correlation type (e.g., 0 for goods-based, 1 for user-based).
     * @return A sorted map of entity IDs and their correlation distances.
     */
    public static Map<Integer, Double> computeNeighbor(Integer key, Map<Integer, List<RelatedAlgo>> map, int type) {
        Map<Integer, Double> distMap = new TreeMap<>();
        List<RelatedAlgo> userItems = map.get(key);

        // Check if the list for the given key is not empty
        if (CollectionUtil.isNotEmpty(userItems)) {
            map.forEach((k, v) -> {
                // Exclude the current entity (key) from calculations
                if (! k.equals(key)) {
                    // Calculate the correlation coefficient between the two lists
                    double coefficient = relateDist(v, userItems, type);
                    // Compute the absolute correlation distance
                    double distance = Math.abs(coefficient);
                    distMap.put(k, distance);
                }
            });
        }
        return distMap;
    }

    /**
     * Calculate the correlation coefficient between two lists of data.
     *
     * @param xList The first list of related algorithms.
     * @param yList The second list of related algorithms.
     * @param type  The type of correlation to compute (0 for goods-based, 1 for user-based).
     * @return The calculated correlation coefficient.
     */
    private static double relateDist(List<RelatedAlgo> xList, List<RelatedAlgo> yList, int type) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        // Iterate through both lists to find matching elements based on the type
        xList.forEach(x -> yList.forEach(y -> {
            if (type == 0) {
                // Goods-based correlation: Match by goods ID
                if (x.getGoodsId().equals(y.getGoodsId())) {
                    xs.add(x.getIndex());
                    ys.add(y.getIndex());
                }
            }
            else {
                // User-based correlation: Match by user ID
                if (x.getUseId().equals(y.getUseId())) {
                    xs.add(x.getIndex());
                    ys.add(y.getIndex());
                }
            }
        }));

        // Calculate and return the Pearson correlation coefficient
        return getRelate(xs, ys);
    }

    /**
     * Compute the Pearson correlation coefficient for two data sequences.
     *
     * @param xs List of integers representing the first sequence.
     * @param ys List of integers representing the second sequence.
     * @return The Pearson correlation coefficient as a double value.
     */
    public static double getRelate(List<Integer> xs, List<Integer> ys) {
        int n = xs.size();

        // Ensure there are at least two elements to compute correlation
        if (n < 2) {
            return 0D;
        }

        // Compute sums and squares of the sequences
        double Ex = xs.stream().mapToDouble(x -> x).sum();
        double Ey = ys.stream().mapToDouble(y -> y).sum();
        double Ex2 = xs.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = ys.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> xs.get(i) * ys.get(i)).sum();

        // Calculate numerator and denominator for the Pearson formula
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));

        // Handle cases where the denominator is zero (no correlation)
        if (denominator == 0) {
            return 0D;
        }

        // Return the Pearson correlation coefficient
        return numerator / denominator;
    }

}
