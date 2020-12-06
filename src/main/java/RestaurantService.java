import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();


    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        //ADDED CODE TO FIND RESTAURANT BY NAME
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName))
                return restaurant;
        }
        throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null)
            throw new restaurantNotFoundException(restaurantName);//doesn't get full test coverage due to contradicting test case to throw exception if restaurant is not found
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
