package dao;

import jdk.internal.jimage.BasicImageReader;
import models.Foodtype;
import models.Restaurant;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Sql2oRestaurantDao {
    public Sql2oRestaurantDao(Sql2o sql2o) {
    }

    @Override
    public void  deleteById(int id){
        String sql = "DELETE from restaurants WHERE id = :id";
        String deleteJoin = "DELETE from restaurants_foodtypes WHERE restaurantid = :restaurantId";
        BasicImageReader sql2o;
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("restaurantId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Foodtype> getAllFoodtypesByRestaurant(int restaurantId){
        List<Foodtype> foodtypes = new ArrayList(); //empty list
        String joinQuery = "SELECT foodtypeid FROM restaurants_foodtypes WHERE restaurantid = :restaurantId";

        BasicImageReader sql2o;
        try (Connection con = sql2o.open()) {
            List<Integer> allFoodtypesIds = con.createQuery(joinQuery)
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Integer.class);
            for (Integer foodId : allFoodtypesIds){
                String foodtypeQuery = "SELECT * FROM foodtypes WHERE id = :foodtypeId";
                foodtypes.add(
                        con.createQuery(foodtypeQuery)
                                .addParameter("foodtypeId", foodId)
                                .executeAndFetchFirst(Foodtype.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

        return foodtypes;
    }

    public Restaurant findById(int restaurantId) {
        return null;
    }
}
