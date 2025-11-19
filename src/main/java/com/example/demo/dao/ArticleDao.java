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
    void writeArticle(String title, String content, int loginMemberId, int boardId);

    @Select("""
    		<script>
    		SELECT COUNT(*) 
    			FROM article WHERE boardId = #{boardId}
    			<if test="keyword != null and keyword != ''">
                    <choose>
                        <when test="searchType == 'title'">
                            AND title LIKE CONCAT('%', #{keyword}, '%')
                        </when>
                        <when test="searchType == 'content'">
                            AND content LIKE CONCAT('%', #{keyword}, '%')
                        </when>
                        <otherwise>
                            AND (title LIKE CONCAT('%', #{keyword}, '%')
                                 OR content LIKE CONCAT('%', #{keyword}, '%'))
                        </otherwise>
                    </choose>
                </if>
    		</script>
    		""")
    int getArticlesCnt(int boardId, String keyword, String searchType);

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
    List<Article> showList(@Param("boardId") int boardId,
                           @Param("keyword") String keyword,
                           @Param("searchType") String searchType,
                           @Param("limitFrom") int limitFrom,
                           @Param("itemsInAPage") int itemsInAPage);

    @Select("""
            SELECT a.id, a.regDate, a.updateDate, a.title, a.content, m.loginId AS writerName
                FROM article AS a
                INNER JOIN `member` AS m
                ON a.memberId = m.id
                WHERE a.id = #{id}
            """)
    Article getArticleById(int id);

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
    void modifyArticle(int id, String title, String content);

    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteArticle(int id);

    @Select("SELECT LAST_INSERT_ID()")
    int getLastInsertId();

    @Select("SELECT COUNT(*) FROM article WHERE boardId = #{boardId}")
    int getTotalCountBoardId(int boardId);
    
    @Select("""
    		SELECT COUNT(*)
    			FROM goodPoint
    			WHERE articleId = #{articleID}
    			AND memberID = #{memberID}
    		""")
	int hasLiked(int articleId, int memberId);
    
    @Select("""
    		SELECT COUNT(*)
    			FROM article_like
    			WHERE articleId = #{articleId}
    		""")
	int getLikeCount(int articleId);
    
    @Delete("""
    		  DELETE FROM article_like
    			WHERE articleId = #{articleId}
    			AND memberId = #{memberId}
    		""")
	void cancleLike(int articleId, int memberId);
    
    @Insert("""
    		 INSERT INTO article_like (articleId, memberId)
    			VALUES (#{articleId}, #{memberId})
    		""")
	void addLike(int articleId, int memberId);
}
