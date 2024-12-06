package com.example.mapper;

import com.example.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Orders data.
 */
public interface OrdersMapper {

    /**
     * Inserts a new Orders entity into the database.
     *
     * @param orders the Orders entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Orders orders);

    /**
     * Deletes an Orders entity from the database by its ID.
     *
     * @param id the ID of the Orders entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Orders entity in the database by its ID.
     *
     * @param orders the Orders entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Orders orders);

    /**
     * Retrieves an Orders entity from the database by its ID.
     *
     * @param id the ID of the Orders entity to be retrieved
     * @return the Orders entity with the specified ID, or null if not found
     */
    Orders selectById(Integer id);

    /**
     * Retrieves a list of all Orders entities that match the criteria specified in the given Orders entity.
     *
     * @param orders an Orders entity with fields used as filter criteria
     * @return a list of Orders entities that match the specified criteria
     */
    List<Orders> selectAll(Orders orders);

    /**
     * Retrieves a list of all Orders entities that are either completed or reviewed.
     *
     * @return a list of Orders entities with status 'Completed' or 'Reviewed'
     */
    @Select("select * from orders where status = 'Completed' or status = 'Reviewed'")
    List<Orders> selectAllOKOrders();

}
