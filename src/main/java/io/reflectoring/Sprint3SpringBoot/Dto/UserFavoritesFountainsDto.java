package io.reflectoring.Sprint3SpringBoot.Dto;

import java.util.List;

public class UserFavoritesFountainsDto {
    private List<Integer> favoriteFountainIds;

    public List<Integer> getFavoriteFountainIds() {
        return favoriteFountainIds;
    }

    public void setFavoriteFountainIds(List<Integer> favoriteFountainIds) {
        this.favoriteFountainIds = favoriteFountainIds;
    }
}
