package com.example.kafka

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(
    JsonSubTypes.Type(value = UserInfoDetail::class),
    JsonSubTypes.Type(OtherUserInfo::class)
)
interface UserInfo {
}

data class UserInfoDetail(
    val name: String,
    val phoneNumber: String,
    val doSomething: String
): UserInfo

data class OtherUserInfo(
    val address: String,
    val age: Int
): UserInfo