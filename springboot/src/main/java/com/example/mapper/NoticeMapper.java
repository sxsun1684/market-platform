package com.example.mapper;

import com.example.entity.Notice;
import java.util.List;

/**
 * Mapper interface for performing operations related to Notice data.
 */
public interface NoticeMapper {

    /**
     * Inserts a new Notice entity into the database.
     *
     * @param notice the Notice entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Notice notice);

    /**
     * Deletes a Notice entity from the database by its ID.
     *
     * @param id the ID of the Notice entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Notice entity in the database by its ID.
     *
     * @param notice the Notice entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Notice notice);

    /**
     * Retrieves a Notice entity from the database by its ID.
     *
     * @param id the ID of the Notice entity to be retrieved
     * @return the Notice entity with the specified ID, or null if not found
     */
    Notice selectById(Integer id);

    /**
     * Retrieves a list of all Notice entities that match the criteria specified in the given Notice entity.
     *
     * @param notice a Notice entity with fields used as filter criteria
     * @return a list of Notice entities that match the specified criteria
     */
    List<Notice> selectAll(Notice notice);
}
