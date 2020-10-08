package cn.xy.novelwebproject.dao;

import cn.xy.novelwebproject.bean.Catalog;
import cn.xy.novelwebproject.bean.CatalogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogMapper {
		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		long countByExample (CatalogExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int deleteByExample (CatalogExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int insert (Catalog record);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int insertSelective (Catalog record);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		List<Catalog> selectByExample (CatalogExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByExampleSelective (@Param("record") Catalog record, @Param("example") CatalogExample example);

		/**
		 * This method was generated by MyBatis Generator.
		 * This method corresponds to the database table catalog
		 *
		 * @mbg.generated Sat Jun 06 19:29:15 CST 2020
		 */
		int updateByExample (@Param("record") Catalog record, @Param("example") CatalogExample example);
}