package com.san.dao.Impl;

import com.san.dao.PostCreationDao;
import com.san.model.Grade;
import com.san.model.PostCreation;
import com.san.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PostCreationDaoImpl implements PostCreationDao {
    @Override
    public void insertPostCreation(PostCreation postCreation) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="insert into postCreation(postTitle,postCreatorId,creationResourcePath,postContent,postPriority) values(?,?,?.?,?)";
            Object[] params= {postCreation.getPostTitle(),postCreation.getPostCreatorId(),postCreation.getCreationResourcePath(),postCreation.getPostConent(),postCreation.getPostPriority()};
            runner.update(sql,params);

        }
        catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public List<PostCreation> listPostCreation() {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from postCreation";
            return (List<PostCreation>) runner.query(sql,new BeanListHandler<PostCreation>(PostCreation.class));
        }
        catch (SQLException ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PostCreation getPostCreation(String postCreationId) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from postCreation where postCreationId =?";
            return (PostCreation) runner.query(sql,postCreationId,new BeanHandler(PostCreation.class));
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}