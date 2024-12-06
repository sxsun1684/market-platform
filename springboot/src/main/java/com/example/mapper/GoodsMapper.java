package com.example.mapper;

import com.example.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Goods data.
 */
public interface GoodsMapper {

    /**
     * Inserts a new Goods entity into the database.
     *
     * @param goods the Goods entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Goods goods);

    /**
     * Deletes a Goods entity from the database by its ID.
     *
     * @param id the ID of the Goods entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Goods entity in the database by its ID.
     *
     * @param goods the Goods entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Goods goods);

    /**
     * Retrieves a Goods entity from the database by its ID.
     *
     * @param id the ID of the Goods entity to be retrieved
     * @return the Goods entity with the specified ID, or null if not found
     */
    Goods selectById(Integer id);

    /**
     * Retrieves a list of all Goods entities that match the criteria specified in the given Goods entity.
     *
     * @param goods a Goods entity with fields used as filter criteria
     * @return a list of Goods entities that match the specified criteria
     */
    List<Goods> selectAll(Goods goods);

    /**
     * Retrieves the top 15 Goods entities ordered by their count value in descending order.
     *
     * @return a list of the top 15 Goods entities
     */
    @Select("select * from goods order by count desc limit 15")
    List<Goods> selectTop15();

    /**
     * Retrieves a list of Goods entities by their type ID.
     *
     * @param id the ID of the type to filter the Goods entities
     * @return a list of Goods entities with the specified type ID
     */
    @Select("select * from goods where type_id = #{id}")
    List<Goods> selectByTypeId(Integer id);

    /**
     * Retrieves a list of Goods entities by their business ID.
     *
     * @param id the ID of the business associated with the Goods entities
     * @return a list of Goods entities associated with the specified business ID
     */
    @Select("select * from goods where business_id = #{id}")
    List<Goods> selectByBusinessId(Integer id);

    /**
     * Retrieves a list of Goods entities whose names contain the specified substring.
     *
     * @param name the substring to filter Goods by their name
     * @return a list of Goods entities whose names match the given substring
     */
    @Select("select * from goods where name like concat('%', #{name}, '%')")
    List<Goods> selectByName(String name);
}
