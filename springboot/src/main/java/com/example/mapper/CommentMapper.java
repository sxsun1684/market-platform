package com.example.mapper;

import com.example.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Comment data.
 */
public interface CommentMapper {

    /**
     * Inserts a new Comment entity into the database.
     *
     * @param comment the Comment entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Comment comment);

    /**
     * Deletes a Comment entity from the database by its ID.
     *
     * @param id the ID of the Comment entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Comment entity in the database by its ID.
     *
     * @param comment the Comment entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Comment comment);

    /**
     * Retrieves a Comment entity from the database by its ID.
     *
     * @param id the ID of the Comment entity to be retrieved
     * @return the Comment entity with the specified ID, or null if not found
     */
    Comment selectById(Integer id);

    /**
     * Retrieves a list of all Comment entities that match the criteria specified in the given Comment entity.
     *
     * @param comment a Comment entity with fields used as filter criteria
     * @return a list of Comment entities that match the specified criteria
     */
    List<Comment> selectAll(Comment comment);

    /**
     * Retrieves a list of Comment entities by goods ID, including user avatar and name information.
     *
     * @param id the ID of the goods associated with the comments
     * @return a list of Comment entities along with user avatar and user name for each comment
     */
    @Select("select comment.*, user.avatar as userAvatar, user.name as userName from comment left join user on comment.user_id = user.id where comment.goods_id = #{id}")
    List<Comment> selectByGoodsId(Integer id);
}
