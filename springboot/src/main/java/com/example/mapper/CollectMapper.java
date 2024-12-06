package com.example.mapper;

import com.example.entity.Collect;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Collect data.
 */
public interface CollectMapper {

    /**
     * Inserts a new Collect entity into the database.
     *
     * @param collect the Collect entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Collect collect);

    /**
     * Deletes a Collect entity from the database by its ID.
     *
     * @param id the ID of the Collect entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Collect entity in the database by its ID.
     *
     * @param collect the Collect entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Collect collect);

    /**
     * Retrieves a Collect entity from the database by its ID.
     *
     * @param id the ID of the Collect entity to be retrieved
     * @return the Collect entity with the specified ID, or null if not found
     */
    Collect selectById(Integer id);

    /**
     * Retrieves a list of all Collect entities that match the criteria specified in the given Collect entity.
     *
     * @param collect a Collect entity with fields used as filter criteria
     * @return a list of Collect entities that match the specified criteria
     */
    List<Collect> selectAll(Collect collect);

    /**
     * Retrieves a Collect entity from the database by user ID and goods ID.
     *
     * @param userId  the ID of the user associated with the Collect entity
     * @param goodsId the ID of the goods associated with the Collect entity
     * @return the Collect entity matching the specified user ID and goods ID, or null if not found
     */
    @Select("select * from collect where user_id = #{userId} and goods_id = #{goodsId}")
    Collect selectByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
}
