package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Article;
@Mapper
public interface ArticleDao {

	@Insert("""
			INSERT INTO article
				SET regDate = NOW()
					, memberId = #{loginMemberId}
					, updateDate = NOW()
					, title = #{title}
					, content = #{content}
					, boardId = #{boardId}
			""")
	public void writeArticle(String title, String content, int loginMemberId, int boardId);

	@Select("""
			SELECT COUNT(id)
				FROM article
				WHERE boardId = #{boardId}
			""")
	public int getArticlesCnt(int boardId);
	
	@Select("""
			<script>
			SELECT a.id, a.regDate, a.title, m.loginId AS writerName
				FROM article AS a
				INNER JOIN `member` AS m
				ON a.memberId = m.id
				WHERE a.boardId = #{boardId}
				<if test="keyword != null and keyword != ''">
					<choose>
						<when test="searchType == 'title'">
						 	AND a.title LIKE CONCAT('%', #{keyword}, '%')
						</when>
						<when test="searchType == 'content'">
						 	AND a.content LIKE CONCAT('%', #{keyword}, '%')
						</when>
						<otherwise>
							AND (a.title LIKE CONCAT('%', #{keyword}, '%')
							OR a.content LIKE CONCAT('%', #{keyword}, '%'))
						</otherwise>
					</choose>
				</if>
				ORDER BY a.id DESC
				LIMIT #{limitFrom}, #{itemsInAPage}
			</script>
			""")
	public List<Article> showList(@Param("boardId") int boardId, @Param("keyword") String keyword, @Param("searchType") String searchType, @Param("limitFrom") int limitFrom, @Param("itemsInAPage")int itemsInAPage);

	@Select("""
			SELECT a.id, a.regDate, a.updateDate, a.title, a.content, m.loginId AS writerName
				FROM article AS a
				INNER JOIN `member` AS m
				ON a.memberId = m.id
				WHERE a.id = #{id}
			""")
	public Article getArticleById(int id);

	@Update("""
			<script>
			UPDATE article
				SET updateDate = NOW()
					<if test="title != null and title != ''">
						, title = #{title}
					</if>
					<if test="content != null and content != ''">
						, content = #{content}
					</if>
				WHERE id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String content);

	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT COUNT(*)
				FROM goodPoint
				WHERE memberId = #{loginedMemberId}
			 	AND articleId = #{id}
			""")
	public int getGoodPoint(int loginedMemberId, int id);

	@Select("""
			SELECT COUNT(*)
				FROM goodPoint
				WHERE articleId = #{id}
			""")
	public int getGoodCnt(int id);
	
	@Insert ("""
			INSERT INTO goodPoint
				SET memberId = #{loginedMemberId},
					articleId = #{id}
			""")

	public void goodAdd(int id, int loginedMemberId);
	
	@Delete("""
			DELETE FROM goodPoint
				WHERE memberId = #{loginedMemberId}
				AND	articleId = #{id}
			""")
	public void goodRemove(int id, int loginedMemberId);
}
