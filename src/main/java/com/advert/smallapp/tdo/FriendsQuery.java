package com.advert.smallapp.tdo;

import lombok.Data;

@Data
public class FriendsQuery extends BaseQuery{
    private Long userId;
    private Long likeUserId;
}
