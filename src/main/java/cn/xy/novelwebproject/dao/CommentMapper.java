package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Comment;
import cn.xy.novelwebproject.bean.CommentExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		long countByExample (CommentExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int deleteByExample (CommentExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int deleteByPrimaryKey (String id);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int insert (Comment record);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int insertSelective (Comment record);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		List<Comment> selectByExample (CommentExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		Comment selectByPrimaryKey (String id);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByExampleSelective (@Param("record") Comment record, @Param("example") CommentExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByExample (@Param("record") Comment record, @Param("example") CommentExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByPrimaryKeySelective (Comment record);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table comment
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByPrimaryKey (Comment record);
}