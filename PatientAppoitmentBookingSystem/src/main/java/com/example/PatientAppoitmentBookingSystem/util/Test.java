package com.example.PatientAppoitmentBookingSystem.util;

public class Test {
    public static void main(String args[]) {
        String str = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpc3MiOiJ0ZXN0LmluIiwiaWF0IjoxNzIwMDAzNTk2LCJleHAiOjE3MjAwMDQ3OTZ9.wqs3n8AwGe5IpkwHAx5ubBMzPRl1z1hk3TYXG5NwiZ0";
        String[] arr = str.split(" ");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }


    }
