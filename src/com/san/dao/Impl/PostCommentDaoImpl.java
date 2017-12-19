package com.san.dao.Impl;
import com.san.dao.PostCommentDao;
import com.san.model.Grade;
import com.san.model.PostComment;
import com.san.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.SQLException;
import java.util.List;

public class PostCommentDaoImpl implements PostCommentDao {
    public List<PostComment> listPostCommentByPostCreationId(int postCreationId) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from postComment where postCreationId =?";
            return (List<PostComment>) runner.query(sql,postCreationId,new BeanListHandler<PostComment>(PostComment.class));
        }
        catch (SQLException ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return null;
        }
    }

    public void insertPostComment(PostComment postComment) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="insert into postComment(postCreationId,reviewerId,evaluationResourcePath,commentContent) values(?,?,?.?)";
            Object[] params= {postComment.getPostCreationId(),postComment.getReviewerId(),postComment.getReviewerId(),postComment.getEvaluationResourcePath(),postComment.getCommentContent()};
            runner.update(sql,params);

        }
        catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public int totalPostComment(int postCreationId) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select count(*) from postComment where postCreationId=? ";
            long totalPostComment=Long.valueOf(String.valueOf(runner.query(sql, postCreationId,new ScalarHandler())));
            
            return (int) totalPostComment;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    public PostComment getLatestComment(int postCreationId) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from postComment where postCreationId =? order by commentTime desc limit 1";
            return (PostComment) runner.query(sql,postCreationId,new BeanHandler(PostComment.class));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    }
