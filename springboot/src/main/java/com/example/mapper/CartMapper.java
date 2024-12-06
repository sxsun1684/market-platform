package com.example.mapper;

import com.example.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Cart data.
 */
public interface CartMapper {

    /**
     * Inserts a new Cart entity into the database.
     *
     * @param cart the Cart entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Cart cart);

    /**
     * Deletes a Cart entity from the database by its ID.
     *
     * @param id the ID of the Cart entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Cart entity in the database by its ID.
     *
     * @param cart the Cart entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Cart cart);

    /**
     * Retrieves a Cart entity from the database by its ID.
     *
     * @param id the ID of the Cart entity to be retrieved
     * @return the Cart entity with the specified ID, or null if not found
     */
    Cart selectById(Integer id);

    /**
     * Retrieves a list of all Cart entities that match the criteria specified in the given Cart entity.
     *
     * @param cart a Cart entity with fields used as filter criteria
     * @return a list of Cart entities that match the specified criteria
     */
    List<Cart> selectAll(Cart cart);

    /**
     * Retrieves a Cart entity from the database by user ID and goods ID.
     *
     * @param userId  the ID of the user associated with the Cart entity
     * @param goodsId the ID of the goods associated with the Cart entity
     * @return the Cart entity matching the specified user ID and goods ID, or null if not found
     */
    @Select("select * from cart where user_id = #{userId} and goods_id = #{goodsId}")
    Cart selectByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
}
