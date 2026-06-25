package algorithms;

import model.Restaurant;

public class QuickSortRestaurants {

    public void quickSort(
            Restaurant[] restaurants,
            int low,
            int high) {

        if(low < high) {

            int pi =
                    partition(
                            restaurants,
                            low,
                            high);

            quickSort(
                    restaurants,
                    low,
                    pi - 1);

            quickSort(
                    restaurants,
                    pi + 1,
                    high);
        }
    }

    private int partition(
            Restaurant[] restaurants,
            int low,
            int high) {

        double pivot =
                restaurants[high]
                        .getRating();

        int i =
                low - 1;

        for(int j = low;
            j < high;
            j++) {

            if(restaurants[j]
                    .getRating()
                    > pivot) {

                i++;

                Restaurant temp =
                        restaurants[i];

                restaurants[i] =
                        restaurants[j];

                restaurants[j] =
                        temp;
            }
        }

        Restaurant temp =
                restaurants[i + 1];

        restaurants[i + 1] =
                restaurants[high];

        restaurants[high] =
                temp;

        return i + 1;
    }
}