package com.san.dao.Impl;

import com.san.dao.UseRecordDao;
import com.san.model.Resource;
import com.san.model.UseRecord;
import com.san.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UseRecordDaoImpl implements UseRecordDao {
    /*
    * 列出所有的使用记录
    * */
    public List<UseRecord> listUseRecord() {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from useRecord";
            return (List<UseRecord>) runner.query(sql,new BeanListHandler<UseRecord>(UseRecord.class));
        }
        catch (SQLException ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return null;
        }
    }
        /*
        * 列出某一用户的所有积分使用记录
        * */
    public List<UseRecord> listUseRecord(int userId) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from useRecord where userId=?";
            return (List<UseRecord>) runner.query(sql,new BeanListHandler<UseRecord>(UseRecord.class),userId);
        }
        catch (SQLException ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return null;
        }
    }
        /*
        * 增加积分使用记录
        * */
    public void insertUseRecord(UseRecord useRecord) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="insert into useRecord(recordId,userId,useType,useNumber) values(?,?,?,?)";
            Object[] params= {useRecord.getRecordId(),useRecord.getUserId(),useRecord.getUseType(),useRecord.getUseNumber()};
            runner.update(sql,params);
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

    }

    public UseRecord getUseRecord(int RecordId) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select * from useRecord where RecordId=?";
            return (UseRecord) runner.query(sql,new BeanHandler(UseRecord.class),RecordId);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public int getTotalRecord() {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select count(*) from useRecord ";
            long totalrecord=Long.valueOf(String.valueOf(runner.query(sql, new ScalarHandler())));
            return (int) totalrecord;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    public int getRecordByUser(int userId) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select count(*) from useRecord where userId=?";
            long totalrecord=Long.valueOf(String.valueOf(runner.query(sql, new ScalarHandler(),userId)));
            return (int) totalrecord;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public int getRecordByUseType(int userId, String useType) {
        try{
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="select count(*) from useRecord where userId=? and useType=?";
            Object[] params={userId,useType};
            long totalrecord=Long.valueOf(String.valueOf(runner.query(sql, new ScalarHandler(),params)));;
            return (int) totalrecord;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    /*
    * 列出某一用户的资料上传下载记录
    * */
    public List<UseRecord> listResourceUseRecord(int userId) {
        try {
            QueryRunner runner=new QueryRunner(C3p0Util.getDataSource());
            String sql="SELECT * FROM useRecord where userId=? and useType like '下载%' or useType like '上传%'";
            return (List<UseRecord>) runner.query(sql,new BeanListHandler<UseRecord>(UseRecord.class),userId);
        }
        catch (SQLException ex) {
            // TODO: handle exception
            ex.printStackTrace();
            return null;
        }
    }

}
