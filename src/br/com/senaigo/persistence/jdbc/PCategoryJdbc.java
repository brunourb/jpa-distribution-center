/*
 * Copyright (c) 2016.
 * Exemplo prático das aulas de ORM/Android
 */

package br.com.senaigo.persistence.jdbc;

import br.com.senaigo.entities.Category;
import br.com.senaigo.exception.PersistenceException;
import br.com.senaigo.interfaces.GenericDao;
import br.com.senaigo.util.ConnectionFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno on 26/02/16.
 */
public class PCategoryJdbc implements GenericDao<Category> {

    private String query;
    private ResultSet rs = null;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;


    public final static Logger log = Logger.getLogger(PCategoryJdbc.class);

    public PCategoryJdbc(){
        connection = ConnectionFactory.getConnection();
    }

    /**
     * {@link http://www.mkyong.com/jdbc/jdbc-preparestatement-example-insert-a-record/}
     * {@link http://docs.oracle.com/javase/6/docs/api/java/sql/Statement.html#executeUpdate}
     * {@link http://stackoverflow.com/questions/4246646/mysql-java-get-id-of-the-last-inserted-value-jdbc}
     * {@link http://www.mysqltutorial.org/mysql-jdbc-blob}
     * {@link http://www.java2s.com/Code/Java/Database-SQL-JDBC/InsertpicturetoMySQL.htm}
     * {@link http://www.profadautomendes.com.br/blog/armazenando-arquivos-no-mysql/}
     * @param category
     */
    @Override
    public void create(Category category) throws PersistenceException {
        try {

            query = "INSERT INTO categories (categoryName,description,picture) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			/*
			 * http://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
			 * PreparedStatement: (Extends Statement.)
			 * Used for precompiling SQL statements that might contain input parameters.
			 * See Using Prepared Statements for more information.
			 */
            int i = 0;
            preparedStatement.setString(++i, category.getCategoryName());
            preparedStatement.setString(++i, category.getDescription());
            preparedStatement.setBinaryStream(++i, FileUtils.openInputStream((category.getPicture())));

            //Insert category into databse
            int inserted = preparedStatement.executeUpdate();

            if(inserted==0){
                throw new PersistenceException("Category not created.");
            }

            //Set id inserted on object Category
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
                category.setCategoryId(rs.getInt(1));

        } catch (Exception e) {
            String message ="Error create category. " + e.getMessage();
            log.error(message);
            throw new PersistenceException(message);
        }
    }

    @Override
    public Category read(Category entity) throws PersistenceException {
        try {

            boolean update = false;

            query = "SELECT * FROM categories WHERE categoryId = ? ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getCategoryId());

            rs = preparedStatement.executeQuery();

            Category category = null;

            while (rs.next()){
                category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setDescription(rs.getString(("description")));
                category.setPicture(Category.stream2file(rs.getBinaryStream("picture")));
            }

            return category;

        } catch(Exception e){
            String message ="Error read category. " + e.getMessage();
            log.error(message);
            throw new PersistenceException(message);
        }
    }

    @Override
    public Category update(Category category)  throws PersistenceException {
        try {
            query = "UPDATE categories SET categoryName = ?, description = ?, picture = ? WHERE categoryId = ?";

            preparedStatement = connection.prepareStatement(query);

			/*
			 * http://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
			 * PreparedStatement: (Extends Statement.)
			 * Used for precompiling SQL statements that might contain input parameters.
			 * See Using Prepared Statements for more information.
			 */
            int i = 0;
            preparedStatement.setString(++i, category.getCategoryName());
            preparedStatement.setString(++i, category.getDescription());
            preparedStatement.setBinaryStream(++i, FileUtils.openInputStream((category.getPicture())));

            //update product into databse
            int updated = preparedStatement.executeUpdate();

            if(updated==0){
                throw new PersistenceException("Category not created.");
            }

        } catch (Exception e) {
            String message ="Error create category. " + e.getMessage();
            log.error(message);
            throw new PersistenceException(message);
        }
        return category;
    }

    @Override
    public void delete(Category category) throws PersistenceException {
        try {

            query = "delete from categories where categoryId = ?";

            preparedStatement = connection.prepareStatement(query);

			/*
			 * http://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
			 * PreparedStatement: (Extends Statement.)
			 * Used for precompiling SQL statements that might contain input parameters.
			 * See Using Prepared Statements for more information.
			 */
            preparedStatement.setInt(1, category.getCategoryId());

            //delete category into databse
            int deleted = preparedStatement.executeUpdate();

            if(deleted==0){
                throw new PersistenceException("Category not deleted.");
            }

        } catch (Exception e) {
            String message = "Error deleted category. " + e.getMessage();
            log.error(message);
            throw new PersistenceException(message);
        }

    }

    @Override
    public void create(List<Category> entities) throws PersistenceException {
        for(Category category : entities){
            create(category);
       }
    }

    @Override
    public List<Category> read(Map<String, Object> params) throws PersistenceException {
        try {
            query = "SELECT * FROM categories";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            List categories = new ArrayList<Category>();

            while (rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setDescription(rs.getString("description"));
                //category.setPicture(rs.getBytes("picture"));

                categories.add(category); //add category to LIST categories
            }

            return categories;

        } catch (SQLException e) {
            throw new PersistenceException("Error read list of category");
        }
    }

    public boolean exists(Integer categoryId) throws br.com.senaigo.exception.PersistenceException {
        Category c = new Category();
        c.setCategoryId(categoryId);
        Category p = read(c);
        return p!=null;
    }

    @Override
    public List<Category> update(List<Category> entities) throws PersistenceException {
        List<Category> list = new ArrayList<Category>();
        for(Category category : entities){
            list.add(update(category));
        }
        return list;
    }

    @Override
    public void delete(List<Category> entities) throws PersistenceException {
        for(Category category : entities){
            delete(category);
        }
    }

    @Override
    public boolean isValid(Map<String, Object> params) throws PersistenceException {
        return false;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
